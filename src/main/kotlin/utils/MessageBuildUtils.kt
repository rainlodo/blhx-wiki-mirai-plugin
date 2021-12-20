package org.iris.wiki.utils


import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.data.*
import java.net.URLEncoder


object MessageBuildUtils {

    suspend fun build(sender: Member, data: Data?, commandList: List<String>) : Message {
        if (data == null) {
            return PlainText(MESSAGE_PARSE_ERROR + SEARCH_URL + URLEncoder.encode(commandList[1]))
        }
        println(data)

        return data.toMessage(sender)

    }


    private suspend fun buildBoatSkillMessage(sender : Member, data: SkillListData) : Message {
        val builder = MessageChainBuilder()
        for (skill in data.list) {
            val src = ImageUtil.getImage(skill.pic)
            val imageId: String = src.uploadAsImage(sender.group).imageId
            builder.add(Image(imageId))
            builder.add("${skill.name}:${skill.detail}")
        }
        return builder.build()
    }


}