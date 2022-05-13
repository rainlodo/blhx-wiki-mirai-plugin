package org.iris.wiki

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.console.util.CoroutineScopeUtils.childScope
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.MiraiExperimentalApi
import org.iris.wiki.config.*
import org.iris.wiki.config.AliasConfig.ALIAS_MAP
import org.iris.wiki.config.AliasConfig.ALIAS_USER_MAP
import org.iris.wiki.utils.HttpUtils
import org.iris.wiki.utils.MessageBuildUtils
import org.iris.wiki.utils.ParserUtils
import java.util.*

@OptIn(ConsoleExperimentalApi::class)
internal object Listener : CoroutineScope by Wiki.childScope("Listener") {

    // 添加用户专有词典
    init {
        ALIAS_MAP.putAll(ALIAS_USER_MAP)
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
                    if (commandList.isNotEmpty() && commandList[0] in CommandConfig.wiki) {
                        when (commandList.size) {
                            1 -> group.sendMessage(MESSAGE_HELP)
                            2 -> wiki(commandList, sender)
                            3 -> wiki(commandList, sender)
                            else -> group.sendMessage(MESSAGE_ERROR)
                        }
                    }

                }
            }
        }

        globalEventChannel().subscribeAlways<NudgeEvent> {

            // 戳了戳小加加
            if (this.target == bot && this.subject is Group) {
                wiki(arrayOf("wiki", "小加加", "摸摸"), this.from as Member)
            }
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

}