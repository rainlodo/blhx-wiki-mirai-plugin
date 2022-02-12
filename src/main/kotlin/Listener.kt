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
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.MiraiExperimentalApi
import org.iris.wiki.config.AliasConfig.ALIAS_MAP
import org.iris.wiki.config.CommandConfig
import org.iris.wiki.config.MESSAGE_ERROR
import org.iris.wiki.config.QQ_DIC
import org.iris.wiki.config.SEARCH_URL
import org.iris.wiki.data.ImagesData
import org.iris.wiki.utils.HttpUtils
import org.iris.wiki.utils.ImageUtil
import org.iris.wiki.utils.MessageBuildUtils
import org.iris.wiki.utils.ParserUtils
import org.jsoup.Jsoup
import java.util.*

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
        if (commandList[1] in arrayOf("舰船一图榜", "一图榜", "pve一图榜")) {

            val doc = Jsoup.parse(HttpUtils.get("${SEARCH_URL}PVE用舰船综合性能强度榜"))
            val url = doc.select("div[id='mc_collapse-1']").select("img")[0].attr("src")
            sender.group.sendMessage(ImagesData(arrayListOf(url)).toMessage(sender))

        } else {

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