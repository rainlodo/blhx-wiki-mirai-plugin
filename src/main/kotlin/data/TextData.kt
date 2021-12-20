package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.PlainText

@Serializable
data class TextData(
    @SerialName("text")
    var text : String
) : Data() {


    override suspend fun toMessage(sender: Member): Message {
        return PlainText(text)
    }
}
