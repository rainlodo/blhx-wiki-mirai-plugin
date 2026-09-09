package org.iris.wiki.action

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withTimeoutOrNull
import net.mamoe.mirai.console.command.CommandSender.Companion.toCommandSender
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.*
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.Wiki
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.config.WikiConfig
import org.iris.wiki.utils.ImageUtil
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO
import kotlin.collections.HashMap

/**
 * 问题
 */
class Question(
    val max_time: Long = 3_000L,
    val size : Int = 4
) {
    var pic = ""
    var choices: HashMap<String, String> = hashMapOf()
    var res = ' '


    fun questionShip(): Question {

        val fileTree:FileTreeWalk = File(CommonConfig.ship_path).walk()
        val images = fileTree.maxDepth(1)//遍历目录层级为1，即无需检查子目录
            .filter { it.isFile } //只挑选出文件,不处理文件夹
            .filter { it.extension == "png" } //选择扩展名为“png”的处理
            .toList()
        var index = 0
        while (choices.size < size) {
            val image = images.random().name
            if (!choices.values.contains(image)) {
                choices['A'.plus(index).toString()] = image
                index++
            }
        }


        res = ('A'..'A'.plus(size-1)).random()
        pic = CommonConfig.ship_path + "/" + choices[res.toString()]

        return this
    }

    suspend fun toMessage(group: Group) : MessageChain {
        val builder = MessageChainBuilder()
        val image = ImageUtil.getImagePiece(pic, 200, 200)
        val out = ByteArrayOutputStream()
        ImageIO.write(image, "png", out)
        val src = out.toByteArray().toExternalResource()
        val imageId = src.uploadAsImage(group).imageId
        builder.add(Image(imageId))

        choices.forEach {
            builder.add("${it.key}. ${it.value.substring(0, it.value.length - 4)}")
            if (it.key != 'A'.plus(size-1).toString()) {
                builder.add("\n")
            }
        }

        src.close()
        return builder.build()
    }

}

/**
 * 问题监听
 */
internal object QuestionListener {

    val channel = GlobalEventChannel.parentScope(Wiki)

    private data class QuestionSession(
        val answerChannel: Channel<GroupMessageEvent> = Channel(Channel.UNLIMITED),
        var validAnswers: Set<String> = emptySet()
    )

    private val sessions = hashMapOf<Long, QuestionSession>()

    private val WRONG_MESSAGE = listOf(
        " 回答错误哦~",
        " 差一点就对了(｀・ω・´)",
        " 不对哦(｡･ω･｡)",
        " 没有答对哦(=´ω｀=)"
    )

    private val TRUE_MESSAGE = listOf(
        " 回答正确φ(>ω<*)"
    )

    fun subscribe() {
        channel.subscribeAlways<GroupMessageEvent> {
            val content = message.contentToString().trim()
            val session = sessions[group.id]

            if (session != null && content.uppercase() in session.validAnswers) {
                session.answerChannel.trySend(this)
            }

            message.forEach {
                // 猜舰娘
                if (it.contentToString() in listOf("猜老婆", "猜舰娘")) {

                    if (WikiConfig.gauss_ship_ban_list.contains(group.id.toString())) {
                        group.sendMessage("本群未开启猜老婆功能喵")
                        return@subscribeAlways
                    }


                    if (sessions.containsKey(group.id)) {
                        group.sendMessage("上一题还没回答正确哦~")
                        return@subscribeAlways
                    }

                    val question = Question(30_000, 6).questionShip()
                    val sessionState = QuestionSession(
                        validAnswers = question.choices.keys.map { choice -> choice.uppercase() }.toSet()
                    )
                    sessions[group.id] = sessionState

                    try {
                        group.sendMessage(question.toMessage(group))
                        while (true) {
                            val reply = withTimeoutOrNull(question.max_time) {
                                sessionState.answerChannel.receive()
                            }
                            if (reply == null) {
                                group.sendMessage("回答超时~，正确答案是${question.res}哒！")
                                return@subscribeAlways
                            }
                            val answer = reply.message.content.trim().uppercase()
                            reply.toCommandSender().sendMessage(
                                buildMessageChain {
                                    append(At(reply.sender))
                                    if (answer == question.res.toString()) {
                                        append(TRUE_MESSAGE.random())
                                    } else {
                                        append(WRONG_MESSAGE.random())
                                    }
                                }
                            )
                            if (answer == question.res.toString()) {
                                return@subscribeAlways
                            }
                        }
                    } finally {
                        sessions.remove(group.id)?.answerChannel?.close()
                    }
                }
            }


        }
    }

}
