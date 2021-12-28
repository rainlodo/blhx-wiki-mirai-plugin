package org.iris.wiki.data

import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.paint.PaintUtils
import org.iris.wiki.paint.component.ShipEquipComponent
import org.iris.wiki.utils.ImageUtil
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.awt.Color

/**
 * 舰娘装备推荐
 */
class ShipEquipData(
    val equipLists : ArrayList<ArrayList<EquipLabelData>> = arrayListOf(
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        arrayListOf()
    )
) : Data() {

    class EquipLabelData(
        var text : String = " ",
        var pics : ArrayList<String> = arrayListOf(),
        var bgColor: Color = Color.WHITE,
        var fontColor: Color = Color.BLACK
    ) {

        fun pharse(doc: Element) : EquipLabelData {
            doc.children().forEach {
                if (it.text() != "") {
                    text = it.text()
                    var style = it.attr("style")
                    if (style.contains("color:red")) {
                        fontColor = Color.RED
                    }
                    val index = style.indexOf("#")
                    if (index > 0 && index + 7 <= style.length) {
                        style = style.substring(index, index + 7)
                        bgColor = PaintUtils.hex2Color(style)
                    }
                }
                else {
                    val images = it.select("img")
                    images.forEach{
                        pics.add(it.attr("src").replace("30px", "60px"))
                    }
                }
            }

            return this
        }

    }

    override fun parse(doc: Document, commandList: List<String>): Data {

        val tables = doc.select("div[class='row zb-table']")[0].children()
        for (i in tables.indices) {
            val trs = tables[i].select("tbody")[0].children()
            for (j in trs.indices) {
                equipLists[i].add(EquipLabelData().pharse(trs[j]))
            }
        }
        return this
    }


    override suspend fun toMessage(sender: Member): Message {


        val shipEquipComponent = ShipEquipComponent(this)
        shipEquipComponent.init()
        val image = shipEquipComponent.draw()

        val src = ImageUtil.imageToBytes(image!!, "png").toByteArray().toExternalResource()
        val imageId: String = src.uploadAsImage(sender.group).imageId
        src.close()

        return Image(imageId)
    }
}