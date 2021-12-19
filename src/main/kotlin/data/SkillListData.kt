package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.command.ConsoleCommandSender.bot
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder

@Serializable
data class SkillListData (
    @SerialName("skillList")
    var list : List<SkillData>
)