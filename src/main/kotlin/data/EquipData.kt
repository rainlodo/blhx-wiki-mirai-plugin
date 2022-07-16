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
        val thList = table.select("th")

        for (i in thList.indices) {
            when (thList[i].text().replace("\n", "")) {
                "整装获取" -> from = tdList[i].text().replace(" ", "\n  ")
                "图纸获取" -> piece = tdList[i].text().replace(" ", "\n  ")
                "研发路线" -> {
                    val html = tdList[i].html()
                        .replace("&gt;", ">")
                        .replace(Regex("<a[^>]+>"), "")
                        .replace(Regex("<span[^>]+>"), "")
                        .replace(Regex("</[^>]+>"), "")
                    html.split(">").forEach{
                        if (it.isNotEmpty())
                            when(it[0]) {
                                '<' -> {
                                    val strs = it.split("\"")
                                    route.add(strs[1].substring(0, strs[1].length - 4))
                                    route.add(strs[3])
                                }
                                '-' -> route.add("↓↓↓↓↓↓↓\n")
                                else -> route.add("================\n")
                            }
                    }
                }
            }
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
        builder.add("整装获取:\n  ${from.ifEmpty { "无" }}\n")
        builder.add("设计图获取:\n  ${piece.ifEmpty { "无" }}\n")

        if (route.isNotEmpty()) {
            builder.add("研发路线：\n")
            route.forEach {
                if (it.startsWith("http")) {
                    val src = ImageUtil.getImageAsExResource(it)
                    val imageId: String = src.uploadAsImage(sender.group).imageId
                    builder.add(Image(imageId))
                    src.close()
                }
                else {
                    builder.add(it)
                }
            }
        }
        return builder.build()
    }

}
