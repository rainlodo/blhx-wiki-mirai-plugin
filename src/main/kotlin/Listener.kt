package org.iris.wiki

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.console.util.CoroutineScopeUtils.childScope
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.MiraiExperimentalApi
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import okio.utf8Size

import org.iris.wiki.config.CommandString
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.ImagesData
import org.iris.wiki.utils.*
import java.util.*
import kotlin.io.path.Path

@OptIn(ConsoleExperimentalApi::class)
internal object Listener : CoroutineScope by Wiki.childScope("Listener") {

    @OptIn(MiraiExperimentalApi::class)
    fun subscribe() {
        globalEventChannel().subscribeAlways<GroupMessageEvent> {

            message.forEach {
                if (it is PlainText) {
                    val commandList = it.contentToString()
                        .lowercase(Locale.getDefault())
                        .split(Regex("[ ]+"))
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    if (ParserUtils.wordToPinyin(commandList[0]) in CommandString.wiki) {
                        when (commandList.size) {
                            2 -> wiki(commandList + CommandString.attribute[0], group)
                            3 -> wiki(commandList, group)
                            else -> group.sendMessage(MESSAGE_ERROR)
                        }
                    }

                    noPa(it.contentToString().lowercase(), group, sender)
                }
            }
        }

        globalEventChannel().subscribeAlways<NudgeEvent> {

            // 戳了戳小加加
            if (this.target == bot && this.subject is Group) {
                wiki(arrayOf("wiki", "小加加", "摸摸"), this.subject as Group)
            }
        }

        globalEventChannel().subscribeAlways<FriendMessageEvent> {


        }

        globalEventChannel().subscribeAlways<NewFriendRequestEvent> {
            //自动同意好友申请
//            accept()
        }
        globalEventChannel().subscribeAlways<BotInvitedJoinGroupRequestEvent> {
            //自动同意加群申请
//            accept()
        }
    }

    fun stop() {
        coroutineContext.cancelChildren()
    }


    suspend fun wiki(commandList: Array<String>, group: Group) {
        if (commandList[1] in arrayOf("舰船一图榜", "一图榜", "pve一图榜")) {
            val src =
                ImageUtil.getImage("https://patchwiki.biligame.com/images/blhx/8/84/oyb0mzeadmus8vscl7is4veyr9ywyyy.png")
            val imageId: String = src.uploadAsImage(group).imageId
            src.close()
            group.sendMessage(Image(imageId))
        } else {

            if (commandList[1] in ALIAS_MAP) {
                commandList[1] = ALIAS_MAP[commandList[1]].toString()
            }


            var result = ParserUtils.parse(HttpUtils.get(SEARCH_URL + commandList[1]), commandList.toList())
            // 禁止搜索yls
            if (ParserUtils.wordToPinyin(commandList[1]) == "yls") {
                result = ImagesData(listOf("data/image/emoji/wiki_iris.jpg"))
            }

            val message = MessageBuildUtils.build(group, result, commandList.toList())

            group.sendMessage(message)
            if (commandList[1] == "美因茨" && commandList[2] == "皮肤") {
                val src = ImageUtil.getImage(JOKER_URL)
                val imageId: String = src.uploadAsImage(group).imageId
                src.close()
                group.sendMessage(Image(imageId))
            }
        }
    }


    // 不爬
    suspend fun noPa(text: String, group: Group, sender: Member) {
        if (text.length < 30) {
            val res = ParserUtils.wordToPinyin(text)
            val index = res.indexOf("yls")
            if (text.indexOf("爬") > index + 2 || text.indexOf("爪巴") > index + 2 ) {
                group.sendMessage(PlainText("不爬，") + At(sender) + PlainText("爬"))
            }
        }
    }
}