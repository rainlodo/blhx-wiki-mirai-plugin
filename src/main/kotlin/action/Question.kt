package org.iris.wiki.action

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import net.mamoe.mirai.console.command.CommandSender.Companion.toCommandSender
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.console.util.CoroutineScopeUtils.childScope
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.EventPriority
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.syncFromEventOrNull
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.MiraiExperimentalApi
import org.iris.wiki.Wiki
import org.iris.wiki.config.CommonConfig
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
@OptIn(ConsoleExperimentalApi::class)
internal object QuestionListener : CoroutineScope by Wiki.childScope("QuestionListener") {

    private const val STATE_SLEEP = 0
    private const val STATE_RUNNING = 1

    var state = hashMapOf<Long, Int>()
    val mutex = Mutex()

    private val WRONG_MESSAGE = listOf(
        " 回答错误哦~",
        " 差一点就对了(｀・ω・´)",
        " 不对哦(｡･ω･｡)",
        " 没有答对哦(=´ω｀=)"
    )

    private val TRUE_MESSAGE = listOf(
        " 回答正确φ(>ω<*)"
    )

    private suspend inline fun <reified P : MessageEvent> P.nextAnswerOrNull(
        timeoutMillis: Long,
        priority: EventPriority = EventPriority.MONITOR,
        noinline filter: suspend P.(P) -> Boolean = { true }
    ): P? {
        require(timeoutMillis > 0) { "timeoutMillis must be > 0" }
        return syncFromEventOrNull<P, P>(timeoutMillis, priority) {
            takeIf { subject == this@nextAnswerOrNull.subject && filter(it, it) }
        }
    }

    @OptIn(MiraiExperimentalApi::class)
    fun subscribe() {
        globalEventChannel().subscribeAlways<GroupMessageEvent> {

            message.forEach {
                // 猜舰娘
                if (it.contentToString() in listOf("猜老婆", "猜舰娘")) {
                    if (!state.keys.contains(group.id)) {
                        state[group.id] = STATE_SLEEP
                    }
                    if (state[group.id] == STATE_RUNNING) {
                        group.sendMessage("上一题还没回答正确哦~")
                        return@subscribeAlways
                    }

                    state[group.id] = STATE_RUNNING
                    val start = System.currentTimeMillis()
                    val question = Question(30_000, 6).questionShip()
                    group.sendMessage(question.toMessage(group))
                    while (state[group.id] == STATE_RUNNING) {
                        val (reply, _) = mutex.withLock {
                            nextAnswerOrNull(question.max_time) { next ->
                                next.message.content.trim().uppercase() in question.choices.keys.toString()
                            } to System.currentTimeMillis() - start
                        }
                        if (reply == null) {
                            group.sendMessage("回答超时~，正确答案是${question.res}哒！")
                            state[group.id] = STATE_SLEEP
                            return@subscribeAlways
                        }
                        val answer = reply.message.content.trim().uppercase()
                        reply.toCommandSender().sendMessage(
                            buildMessageChain {
                                append(At(reply.sender))
                                if (answer == question.res.toString()) {
                                    append(TRUE_MESSAGE.random())
                                    state[reply.group.id] = STATE_SLEEP
                                } else {
                                    append(WRONG_MESSAGE.random())
                                }
                            }
                        )
                    }
                }
            }


        }
    }

    fun stop() {
        coroutineContext.cancelChildren()
    }
}