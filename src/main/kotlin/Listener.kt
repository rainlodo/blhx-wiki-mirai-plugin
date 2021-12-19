package org.iris.wiki

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.console.util.CoroutineScopeUtils.childScope
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.NewFriendRequestEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.MiraiExperimentalApi

import org.iris.wiki.config.CommandString
import org.iris.wiki.utils.*
import java.util.*

@OptIn(ConsoleExperimentalApi::class)
internal object Listener : CoroutineScope by Wiki.childScope("Listener") {

    @OptIn(MiraiExperimentalApi::class)
    fun subscribe() {
        globalEventChannel().subscribeAlways<GroupMessageEvent> {

            val commandList = message.contentToString()
                .lowercase(Locale.getDefault())
                .split(Regex("[ ]+"))
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
            if (commandList[0] in CommandString.wiki) {
                when (commandList.size) {
                    2 -> wiki(commandList + COMMON, group)
                    3 -> wiki(commandList, group)
                    else -> group.sendMessage(MESSAGE_ERROR)
                }
            }

        }

        globalEventChannel().subscribeAlways<FriendMessageEvent> {

            if (message.contentToString() == "123") {
                sender.sendMessage("123")
            }

        }

        globalEventChannel().subscribeAlways<NewFriendRequestEvent>{
            //自动同意好友申请
//            accept()
        }
        globalEventChannel().subscribeAlways<BotInvitedJoinGroupRequestEvent>{
            //自动同意加群申请
//            accept()
        }
    }

    fun stop() {
        coroutineContext.cancelChildren()
    }


    suspend fun wiki(commandList: Array<String>, group: Group) {
        if (commandList[1] in arrayOf("舰船一图榜", "一图榜", "pve一图榜")) {
            val src = ImageUtil.getImage("https://patchwiki.biligame.com/images/blhx/8/84/oyb0mzeadmus8vscl7is4veyr9ywyyy.png").toByteArray().toExternalResource()
            val imageId: String = src.uploadAsImage(group).imageId
            group.sendMessage(Image(imageId))
        }
        else {
            val http = HttpUtils()
            if (commandList[1] in ALIAS_MAP) {
                commandList[1] = ALIAS_MAP[commandList[1]].toString()
            }
            val result = ParserUtils.parse(http.get(SEARCH_URL + commandList[1]), commandList[2])

            val message = MessageBuildUtils.build(group, result, commandList.toList())
            group.sendMessage(message)
            if (commandList[1] == "美因茨" && commandList[2] == "皮肤") {
                val src = ImageUtil.getImage(JOKER_URL).toByteArray().toExternalResource()
                val imageId: String = src.uploadAsImage(group).imageId
                group.sendMessage(Image(imageId))
            }
        }
    }
}