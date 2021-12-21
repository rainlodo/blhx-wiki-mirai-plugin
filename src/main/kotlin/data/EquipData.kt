package org.iris.wiki.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.utils.ImageUtil
import org.jsoup.nodes.Document

@Serializable
data class EquipData(
    @SerialName("name")
    var name: String= "",
    @SerialName("type")
    var type: String= "",
    @SerialName("camp")
    var camp: String= "",
    @SerialName("pic")
    var pic: String= "",
    @SerialName("from")
    var from: String= "",
    @SerialName("route")
    var route: ArrayList<String> = arrayListOf(),
    @SerialName("route_pic")
    var routePic: ArrayList<String> = arrayListOf(),
    @SerialName("piece")
    var piece: String= ""
) : Data() {

    override fun parse(doc: Document, commandList: List<String>) : Data {

        camp = doc.select("li[class='active']").last().text()

        val ul = doc.select("ul[class='equip']")[0]
        val liList = ul.children()
        name = liList[0].text()
        type = liList[1].text()
        pic = liList[1].select("img")[0].attr("src")

        val table = doc.select("table[class='table table-bordered']")[0]
        val tdList = table.select("td")
        from = tdList[0].text()
        piece = tdList[2].text()

        for (img in tdList[1].select("img")) {
            route.add(img.attr("alt").dropLast(4))
            routePic.add(img.attr("src"))
        }
        return super.parse(doc, commandList)
    }


    override suspend fun toMessage(sender: Member): Message {
        val builder = MessageChainBuilder()
        builder.add(name)
        val src = ImageUtil.getImageAsExResource(pic)
        val imageId: String = src.uploadAsImage(sender.group).imageId
        builder.add(Image(imageId))

        builder.add(camp)
        builder.add(type + "\n")
        builder.add("整装获取:\n${from.ifEmpty { "无" }}\n")
        builder.add("设计图获取:\n${piece.ifEmpty { "无" }}\n")

        if (route.isNotEmpty()) {
            builder.add("研发路线：\n")
            for (i in 0 until route.size) {
                builder.add("${route[i]}${if (i == route.size-1) "" else "->"}")
                val src = ImageUtil.getImageAsExResource(routePic[i])
                val imageId: String = src.uploadAsImage(sender.group).imageId
                builder.add(Image(imageId))
            }
        }
        return builder.build()
    }

}
