package org.iris.wiki.utils

import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.data.*
import java.net.URLEncoder

object MessageBuildUtils {

    suspend fun build(group: Group, data: Any?, url: String) : Message {
        println(data)
        if (data is String) {
            return PlainText(data)
        }
        else if (data is BoatData) {
            return buildBoatMessage(group, data)
        }
        else if (data is SkillListData) {
            return buildBoatSkillMessage(group, data)
        }
        else if (data is ImagesData) {
            return buildImagesMessage(group, data)
        }
        else if (data is EquipData) {
            return buildEquipMessage(group, data)
        }
        else if (data is EquipAttrData) {
            return buildEquipAttrMessage(group, data)
        }
        else if (data is SearchData) {
            return buildSearchMessage(group, data, url)
        }
        return PlainText(MESSAGE_PARSE_ERROR + SEARCH_URL + URLEncoder.encode(url))
    }

    private suspend fun buildBoatMessage(group: Group, data: BoatData) : Message {
        val src = ImageUtil.getImage(data.pic).toByteArray().toExternalResource()
        val imageId: String = src.uploadAsImage(group).imageId
        val builder = MessageChainBuilder()
        builder.add(data.name + "\n")
        builder.add(data.level + data.camp + data.type + "\n")
        builder.add(Image(imageId))
        builder.add("建造时间：${data.time}\n")
        var from = ""
        if (data.normal != "") {
            from += data.normal + "\n"
        }
        if (data.active != "") {
            from += data.active + "\n"
        }
        if (data.other != "") {
            from += data.other
        }
        if (from == "") {
            from = "暂无获得途径"
        }
        builder.add("获得途径：$from")
        return builder.build()
    }

    private suspend fun buildBoatSkillMessage(group: Group, data: SkillListData) : Message {
        val builder = MessageChainBuilder()
        for (skill in data.list) {
            val src = ImageUtil.getImage(skill.pic).toByteArray().toExternalResource()
            val imageId: String = src.uploadAsImage(group).imageId
            builder.add(Image(imageId))
            builder.add("${skill.name}:${skill.detail}")
        }
        return builder.build()
    }

    private suspend fun buildImagesMessage(group: Group, data: ImagesData) : Message {
        val builder = MessageChainBuilder()
        for (url in data.images) {
            val src = ImageUtil.getImage(url).toByteArray().toExternalResource()
            val imageId: String = src.uploadAsImage(group).imageId
            builder.add(Image(imageId))
        }

        return builder.build()
    }

    private suspend fun buildEquipMessage(group: Group, data: EquipData) : Message {
        val builder = MessageChainBuilder()
        builder.add(data.name)
        val src = ImageUtil.getImage(data.pic).toByteArray().toExternalResource()
        val imageId: String = src.uploadAsImage(group).imageId
        builder.add(Image(imageId))

        builder.add(data.camp)
        builder.add(data.type + "\n")
        builder.add("整装获取:\n${data.from.ifEmpty { "无" }}\n")
        builder.add("设计图获取:\n${data.piece.ifEmpty { "无" }}\n")

        if (data.route.isNotEmpty()) {
            builder.add("研发路线：\n")
            for (i in 0 until data.route.size) {
                builder.add("${data.route[i]}${if (i == data.route.size-1) "" else "->"}")
                val src = ImageUtil.getImage(data.routePic[i]).toByteArray().toExternalResource()
                val imageId: String = src.uploadAsImage(group).imageId
                builder.add(Image(imageId))
            }
        }
        return builder.build()
    }

    private suspend fun buildEquipAttrMessage(group: Group, data: EquipAttrData) : Message {
        val builder = MessageChainBuilder()
        builder.add(data.name)
        val src = ImageUtil.getImage(data.pic).toByteArray().toExternalResource()
        val imageId: String = src.uploadAsImage(group).imageId
        builder.add(Image(imageId))

        builder.add(data.camp)
        builder.add(data.type + "\n")
        builder.add(data.attr)

        return builder.build()
    }

    private suspend fun buildSearchMessage(group: Group, data: SearchData, url: String) : Message {
        val builder = MessageChainBuilder()
        val result : ArrayList<String> = arrayListOf()
        for (res in data.result) {
            if (res.contains(url)) {
                result.add(res)
            }
        }


        if (result.isEmpty()) {
            builder.add(MESSAGE_NO_RESULT)
        }
        else {
            builder.add(MESSAGE_SEARCH)
            for (res in result) {
                builder.add("\n$res")
            }
        }

        return builder.build()
    }
}