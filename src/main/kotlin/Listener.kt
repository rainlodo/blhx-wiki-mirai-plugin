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
                    if (commandList[0] in CommandString.wiki) {
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

            if (message.contentToString() == "123") {
                sender.sendMessage(Path(CommonConfig.ttf).toAbsolutePath().toString())
            }

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
                    .toByteArray().toExternalResource()
            val imageId: String = src.uploadAsImage(group).imageId
            group.sendMessage(Image(imageId))
        } else {

            if (commandList[1] in ALIAS_MAP) {
                commandList[1] = ALIAS_MAP[commandList[1]].toString()
            }
            val result = ParserUtils.parse(HttpUtils.get(SEARCH_URL + commandList[1]), commandList.toList())

            val message = MessageBuildUtils.build(group, result, commandList.toList())
            group.sendMessage(message)
            if (commandList[1] == "美因茨" && commandList[2] == "皮肤") {
                val src = ImageUtil.getImage(JOKER_URL).toByteArray().toExternalResource()
                val imageId: String = src.uploadAsImage(group).imageId
                src.close()
                group.sendMessage(Image(imageId))
            }
        }
    }


    // 不爬
    suspend fun noPa(text: String, group: Group, sender: Member) {
        if (text.length == 4) {
            val format = HanyuPinyinOutputFormat()
            format.caseType = HanyuPinyinCaseType.LOWERCASE
            var res = ""
            text.forEach {
                if (codeType(it) == 1)
                    res += PinyinHelper.toHanyuPinyinStringArray(it, format)[0][0]
                else
                    res += it
            }

            if (res == "ylsp") {
                group.sendMessage(PlainText("不爬，") + At(sender) + PlainText("爬"))
            }
        }
    }

    fun codeType(ch: Char): Int {
        return if (ch in '\u4e00'..'\u9fa5') 1 //中文字符
        else if (ch in '\u0030'..'\u0039') 2//数字字符
        else if ((ch in '\u0041'..'\u005A') or (ch in '\u0061'..'\u007A')) 3//英文字符
        else 0
    }
}