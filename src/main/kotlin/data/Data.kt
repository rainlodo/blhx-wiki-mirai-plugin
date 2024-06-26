package org.iris.wiki.data

import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.PlainText
import org.iris.wiki.config.MESSAGE_HELP
import org.jsoup.nodes.Document

open class Data {

    lateinit var commandList: List<String>

    /**
     * 是否@发送消息的用户
     */
    var at: Boolean = false

    /**
     * 额外的消息数据
     */
    var extra_msg = MessageChainBuilder()

    /**
     * 从网页中爬取所需的信息
     *
     * @param dac 网页
     * @param commandList 指令参数
     */
    open fun parse(doc: Document, commandList: List<String>) : Data{
        this.commandList = commandList
        return this
    }

    /**
     * 生成对应的信息
     *
     * @param sender 使用指令的群成员
     */
    open suspend fun toMessage(sender: Member) : Message {
        return PlainText(MESSAGE_HELP)
    }


    fun activateAt() : Data {
        this.at = true
        return this
    }
}