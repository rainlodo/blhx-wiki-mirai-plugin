package org.iris.wiki.utils


import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.*
import org.iris.wiki.paint.component.EquipAttrComponent
import java.io.File
import java.net.URLEncoder
import javax.imageio.ImageIO


object MessageBuildUtils {

    suspend fun build(sender: Member, data: Data?, commandList: List<String>) : Message {
        if (data == null) {
            return PlainText(MESSAGE_PARSE_ERROR + SEARCH_URL + URLEncoder.encode(commandList[1]))
        }
        println(data)

        return data.toMessage(sender)

    }

    private suspend fun buildBoatMessage(sender : Member, data: BoatData) : Message {
        val src = ImageUtil.getImage(data.pic)
        val imageId: String = src.uploadAsImage(sender.group).imageId
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

    private suspend fun buildAudioMessage(sender : Member, data: AudioData) : Message {
        return data.toMessage(sender)
    }

    private suspend fun buildImagesMessage(sender : Member, data: ImagesData) : Message {
        val builder = MessageChainBuilder()
        for (url in data.images) {

            val src = ImageUtil.getImage(url)
            val imageId: String = src.uploadAsImage(sender.group).imageId
            builder.add(Image(imageId))
        }

        return builder.build()
    }

    private suspend fun buildEquipFromMessage(sender : Member, data: EquipData) : Message {
        val builder = MessageChainBuilder()
        builder.add(data.name)
        val src = ImageUtil.getImage(data.pic)
        val imageId: String = src.uploadAsImage(sender.group).imageId
        builder.add(Image(imageId))

        builder.add(data.camp)
        builder.add(data.type + "\n")
        builder.add("整装获取:\n${data.from.ifEmpty { "无" }}\n")
        builder.add("设计图获取:\n${data.piece.ifEmpty { "无" }}\n")

        if (data.route.isNotEmpty()) {
            builder.add("研发路线：\n")
            for (i in 0 until data.route.size) {
                builder.add("${data.route[i]}${if (i == data.route.size-1) "" else "->"}")
                val src = ImageUtil.getImage(data.routePic[i])
                val imageId: String = src.uploadAsImage(sender.group).imageId
                builder.add(Image(imageId))
            }
        }
        return builder.build()
    }

    private suspend fun buildEquipAttrMessage(sender : Member, data: EquipAttrData, commandList: List<String>) : Message {

        val path = "${CommonConfig.equip_attr_path}/${data.name}T${data.tno}.png"
        val file = File(path)
        if (!file.exists()) {
            val equipAttrComponent = EquipAttrComponent(data)
            equipAttrComponent.init()
            val image = equipAttrComponent.draw()
            ImageIO.write(image, "png", file)
        }
        val src = file.toExternalResource()
        val imageId: String = src.uploadAsImage(sender.group).imageId
        src.close()
        return Image(imageId)
    }

    private suspend fun buildSearchMessage(sender : Member, data: SearchData, url: String) : Message {
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