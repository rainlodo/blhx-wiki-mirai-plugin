package org.iris.wiki.utils


import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.*
import org.iris.wiki.data.*
import java.net.URLEncoder


object MessageBuildUtils {

    suspend fun build(sender: Member, data: Data?, commandList: List<String>) : Message {

        // 只有解析失败会返回null，此时存在页面但不能解析
        if (data == null) {
            return PlainText(MESSAGE_PARSE_ERROR + SEARCH_URL + URLEncoder.encode(commandList[1]))
        }
        println(data)

        // 生成message
        return if (data.at) {
            At(sender) + data.toMessage(sender)
        } else {
            data.toMessage(sender)
        }
    }



}