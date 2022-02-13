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
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.MiraiExperimentalApi
import org.iris.wiki.config.*
import org.iris.wiki.utils.HttpUtils
import org.iris.wiki.utils.MessageBuildUtils
import org.iris.wiki.utils.ParserUtils
import java.util.*

@OptIn(ConsoleExperimentalApi::class)
internal object Listener : CoroutineScope by Wiki.childScope("Listener") {

    val ALIAS_MAP : HashMap<String, String> = hashMapOf()

    init {
        val mapList = listOf(
            AliasConfig.ALIAS_DD_MAP,
            AliasConfig.ALIAS_CL_MAP,
            AliasConfig.ALIAS_CA_MAP,
            AliasConfig.ALIAS_CV_MAP,
            AliasConfig.ALIAS_BB_MAP,
            AliasConfig.ALIAS_OTHER_MAP,
            AliasConfig.ALIAS_JP_MAP,
            AliasConfig.ALIAS_DD_GUN_MAP,
            AliasConfig.ALIAS_C_GUN_MAP,
            AliasConfig.ALIAS_BB_GUN_MAP,
            AliasConfig.ALIAS_AIR_GUN_MAP,
            AliasConfig.ALIAS_PLANE_MAP,
            AliasConfig.ALIAS_TORPEDO_MAP,
            AliasConfig.ALIAS_DEVICE_MAP
        )
        mapList.forEach {
            ALIAS_MAP.putAll(it)
        }
    }

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
                    if (ParserUtils.wordToPinyin(commandList[0]) in CommandConfig.wiki) {
                        when (commandList.size) {
                            2 -> wiki(commandList, sender)
                            3 -> wiki(commandList, sender)
                            else -> group.sendMessage(MESSAGE_ERROR)
                        }
                    }

                    noPa(it.contentToString().lowercase(), group, sender)
                    repeat(it.contentToString().lowercase(), group, sender)
                }
            }
        }

        globalEventChannel().subscribeAlways<NudgeEvent> {

            // 戳了戳小加加
            if (this.target == bot && this.subject is Group) {
                wiki(arrayOf("wiki", "小加加", "摸摸"), this.from as Member)
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


    suspend fun wiki(commandList: Array<String>, sender: Member) {


        if (commandList[1] in ALIAS_MAP) {
            commandList[1] = ALIAS_MAP[commandList[1]].toString()
        }


        var result = Checker.check(commandList, sender)
        if (result == null) {
            result = ParserUtils.parse(HttpUtils.get(SEARCH_URL + commandList[1]), commandList.toList())
        }


        val message = MessageBuildUtils.build(sender, result, commandList.toList())

        sender.group.sendMessage(message)

    }


    // 不爬
    suspend fun noPa(text: String, group: Group, sender: Member) {
        if (text.length < 30) {
            val res = ParserUtils.wordToPinyin(text.replace(" ", ""))
            val index = res.indexOf("yls")
            if (index >= 0) {
                if (text.indexOf("爬") > index + 2 || text.indexOf("爪巴") > index + 2 ||
                    text.indexOf("pa") > index + 2) {
                    if (sender.id in QQ_DIC) {
                        group.sendMessage(PlainText("不爬，@${QQ_DIC[sender.id]}爬"))
                    }
                    else {
                        group.sendMessage(PlainText("不爬，").plus(At(sender)).plus(PlainText("爬")))
                    }
                }
            }
        }
    }

    // 复读tql
    suspend fun repeat(text: String, group: Group, sender: Member) {
        if (group.id == 1046477299L && sender.id == 2270816244L) {
            if (text.startsWith(" ")) {
                group.sendMessage(text)
            }
        }
    }
}