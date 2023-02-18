package org.iris.wiki

import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.MiraiExperimentalApi
import org.iris.wiki.config.*
import org.iris.wiki.config.AliasConfig.ALIAS_MAP
import org.iris.wiki.config.AliasConfig.ALIAS_USER_MAP
import org.iris.wiki.config.CommandConfig.ALL_COMMAND
import org.iris.wiki.config.CommandConfig.voice_map
import org.iris.wiki.data.ImagesData
import org.iris.wiki.data.SearchData
import org.iris.wiki.utils.HttpUtils
import org.iris.wiki.utils.MessageBuildUtils
import org.iris.wiki.utils.ParserUtils
import java.util.*

internal object Listener {

    // 添加用户专有词典
    init {
        ALIAS_MAP.putAll(ALIAS_USER_MAP)
        val COMMANDS_LIST = setOf(
                CommandConfig.attribute,
                CommandConfig.dress,
                CommandConfig.dressLarge,
                CommandConfig.picLarge,
                CommandConfig.from,
                CommandConfig.tech,
                CommandConfig.evaluate,
                CommandConfig.equip,
                CommandConfig.wedding
        )
        for (list in COMMANDS_LIST) {
            ALL_COMMAND.addAll(list)
        }
        ALL_COMMAND.addAll(voice_map.keys)
    }

    val channel = GlobalEventChannel.parentScope(Wiki)
    @OptIn(MiraiExperimentalApi::class)
    fun subscribe() {
        channel.subscribeAlways<GroupMessageEvent> {

            message.forEach {
                if (it is PlainText) {
                    var commandList = it.contentToString()
                        .lowercase(Locale.getDefault())
                        .split(Regex("[ ]+"))
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    if (commandList.isNotEmpty() && commandList[0] in CommandConfig.wiki) {
                        when (commandList.size) {
                            1 -> group.sendMessage(ImagesData(arrayListOf("${CommonConfig.emoji_path}/help.png")).toMessage(sender))
                            2 -> wiki(commandList, sender)
                            3 -> wiki(commandList, sender)
                            else -> group.sendMessage(MESSAGE_ERROR)
                        }
                    } else {
                        phraseCommand(it.contentToString(), sender)
                    }

                }
            }
        }

        channel.subscribeAlways<NudgeEvent> {

            // 戳了戳事件
            if (this.target == bot && this.subject is Group) {
                if (CommandConfig.touch_first_param != "无" && CommandConfig.touch_second_param != "无") {
                    wiki(arrayOf("wiki", CommandConfig.touch_first_param, CommandConfig.touch_second_param), this.from as Member)
                }
            }
        }

    }


    suspend fun phraseCommand(msg: String, sender: Member) {
        if (WikiConfig.command_parse_on) {
            var text = msg.lowercase(Locale.getDefault())
                .replace(Regex("[ ]+"), "")
            val commandList = arrayOf("", "", CommandConfig.attribute.first())
            for (wiki in CommandConfig.wiki) {
                if (text.startsWith(wiki)) {
                    text = text.removeRange(0, wiki.length)
                    commandList[0] = wiki
                    break
                }
            }

            if (commandList[0] in CommandConfig.wiki) {
                if (ALIAS_MAP.contains(text) || NAME_LIST.contains(text)) {
                    // 单词条
                    commandList[1] = text
                } else {
                    // 双词条
                    for (command in ALL_COMMAND) {
                        if (text.endsWith(command)) {
                            text = text.substring(0, text.length - command.length)
                            commandList[2] = command
                            break
                        } else if (text.startsWith(command)) {
                            text = text.removeRange(0, command.length)
                            commandList[2] = command
                            break
                        }
                    }
                    commandList[1] = text
                }
                wiki(commandList, sender, false)
            }
        }
    }

    suspend fun wiki(commandList: Array<String>, sender: Member, searchAgain: Boolean=true) {


        if (commandList[1] in ALIAS_MAP) {
            commandList[1] = ALIAS_MAP[commandList[1]].toString()
        }


        var result = Checker.check(commandList, sender)
        if (result == null) {
            result = ParserUtils.parse(HttpUtils.get(SEARCH_URL + commandList[1]), commandList.toList())
        }
        if (searchAgain && WikiConfig.command_parse_on && result is SearchData && result.result.isEmpty()) {
            phraseCommand(commandList.joinToString(""), sender)
            return
        }

        val message = MessageBuildUtils.build(sender, result, commandList.toList())

        sender.group.sendMessage(message)

    }

}