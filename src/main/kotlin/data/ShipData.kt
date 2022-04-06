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
data class ShipData(
    @SerialName("名称")
    var name: String = "",
    @SerialName("类型")
    var type: String = "",
    @SerialName("稀有度")
    var level: String = "",
    @SerialName("阵营")
    var camp: String = "",
    @SerialName("相关活动")
    var active: String = "",
    @SerialName("建造时间")
    var time: String? = null,

    @SerialName("普通掉落点")
    var normal_from: String = "",
    @SerialName("活动掉落点")
    var active_from: String = "",
    @SerialName("其他来源")
    var other_from: String = "",


    @SerialName("获得科技点")
    var tech_gain: String = "",
    @SerialName("满级科技点")
    var tech_full: String = "",

    @SerialName("pic")
    var pic: String = "",
) : Data() {

    override fun parse(doc: Document, commandList: List<String>) : Data {


        val tableGeneral = doc.select("table[class='wikitable sv-general']") //wikitable sv-category
        val trList = tableGeneral[0].select("tr")

        // 第一行为舰船名称
        name = trList[0].text()

        // 第二行第5列中含有舰船类型
        type = trList[1].select("td")[4].text()

        level = trList[2].select("span[id='PNrarity']")[0].text()

        camp = trList[2].select("td")[3].text()

        for (i in 3 until trList.size) {
            val label = trList[i].select("td")[0].text()
            when {
                label.contains("建造时间") -> time = trList[i].select("td")[1].text()
                label.contains("普通掉落点") -> normal_from = trList[i].select("td")[1].text().replace(" ", "\n  ")
                label.contains("活动/档案") -> active_from = trList[i].select("td")[1].text().replace(" ", "\n  ")
                label.contains("相关活动") -> active = trList[i].select("td")[1].text()
                label.contains("其他途径") -> other_from = trList[i].select("td")[1].text().replace(" ", "\n  ")
            }
        }
        time = time?.replace("活动", "${active}活动")

        pic = doc
            .select("div[class='tab_con active']")[0]
            .select("img")[0]
            .attr("src")

        return super.parse(doc, commandList)
    }


    override suspend fun toMessage(sender: Member): Message {
        val src = ImageUtil.getImageAsExResource(pic)
        val imageId: String = src.uploadAsImage(sender.group).imageId
        val builder = MessageChainBuilder()
        builder.add(name + "\n")
        builder.add(level + camp + type + "\n")
        builder.add(Image(imageId))
        builder.add("建造时间：${time}\n")
        var from = "  "
        if (normal_from != "") {
            from += "$normal_from\n  "
        }
        if (active_from != "") {
            from += "$active_from\n  "
        }
        if (other_from != "") {
            from += other_from
        }
        if (from == "") {
            from = "暂无获得途径"
        }
        builder.add("获得途径：$from")
        return builder.build()
    }
}