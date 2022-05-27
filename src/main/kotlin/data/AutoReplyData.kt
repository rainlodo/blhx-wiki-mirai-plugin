package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.io.File

@Serializable
data class AutoReplyData(
    @SerialName("msg")
    var msg : String
) : Data() {


    override suspend fun toMessage(sender: Member): Message {
        val regex = Regex("\\{[^}]+}")
        val msgList = regex.findAll(msg).toList()
        val messageChainBuilder = MessageChainBuilder()
        for (m in msgList) {
            if (m.value.startsWith("{text")) {
                // 文字信息
                messageChainBuilder.add(m.value.substring(6, m.value.length - 1))
            }
            else {
                // 图片信息
                try {
                    val src = File(m.value.substring(7, m.value.length - 1)).toExternalResource()
                    val imageId: String = src.uploadAsImage(sender.group).imageId
                    messageChainBuilder.add(Image(imageId))
                }
                catch (e : Exception) {
                    messageChainBuilder.add("[图片文件丢失]")
                }
            }
        }
        return messageChainBuilder.build()
    }
}
