package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.paint.component.EquipAttrComponent
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.File
import javax.imageio.ImageIO

@Serializable
data class EquipAttrData(
    @SerialName("name")
    var name: String= "",
    @SerialName("type")
    var type: String= "",
    @SerialName("tag")
    var tag: String= "",
    @SerialName("level")
    var level: Int = 0,
    @SerialName("tno")
    var tno: Int = -1,
    @SerialName("unicorn")
    var unicorn: Boolean = false,
    @SerialName("pic")
    var pic: String= "",
    @SerialName("attr")
    var attr: String= "",
    @SerialName("use")
    var use: String= ""
) : Data() {

    override fun parse(doc: Document, commandList: List<String>) : Data {

        val levelMap = mapOf<String, Int>(
            "text-align:center;font-size:1.2em;background:#dbdcdf" to 1,
            "text-align:center;font-size:1.2em;background:#1bb7eb" to 2,
            "text-align:center;font-size:1.2em;background:#ae90ef" to 3,
            "text-align:center;font-size:1.2em;background:#f9f593" to 4,
            "text-align:center;font-size:1.2em;background:linear-gradient(135deg,#59AE6A,#48AE96,#60D9EC,#65A5D5,#9491E0,#C382A4)" to 5
        )
        


        val ul = doc.select("ul[class='equip']")[0]

        val liList = ul.children()
        name = liList[0].text()
        type = liList[1].select("div")[0].text()
        tag = liList[1].select("div")[1].text()
        tno = liList[1].select("b").last().text()[1].digitToInt()
        pic = liList[1].select("img")[0].attr("src")

        level = levelMap.get(liList[0].attr("style"))!!
        for (i in 2 until liList.size-1) {
            var tab = 0
            val node = liList[i]
            attr += getAttrText(node, 0)
        }
        attr += "适用舰种"
        use = liList.last().select("td[class='appShipType']").text()

        return super.parse(doc, commandList)

    }

    private fun getAttrText(node: Element, tab: Int) : String {
        var text = ""
        return if (node.tagName() == "table") {
            getTabs(tab) + node.select("tr")[0].child(0).text() +
                ":" + node.select("tr")[0].child(1).text() + "\n"
        }
        else if (node.tagName() == "li") {
            getAttrText(node.child(0), tab)
        }
        else if (node.tagName() == "ul") {
            for (n in node.children()) {
                text += getAttrText(n, tab + 1)
            }
            text
        }
        else {
            for (n in node.children()) {
                text += getAttrText(n, tab)
            }
            text
        }
    }

    private fun getTabs(count: Int) : String {
        var result = ""
        for (i in 1..count) {
            result += "\t"
        }
        return result
    }


    override suspend fun toMessage(sender: Member): Message {
        val path = "${CommonConfig.equip_output_path}/${name}T${tno}.png"
        val file = File(path)
        if (!file.exists()) {
            val equipAttrComponent = EquipAttrComponent(this)
            equipAttrComponent.init()
            val image = equipAttrComponent.draw()
            ImageIO.write(image, "png", file)
        }
        val src = file.toExternalResource()
        val imageId: String = src.uploadAsImage(sender.group).imageId
        src.close()
        return Image(imageId)
    }
}
