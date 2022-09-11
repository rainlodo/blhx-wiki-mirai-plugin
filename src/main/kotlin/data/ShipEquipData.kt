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
        arrayListOf(),
        arrayListOf()
    ),
    val specialEquipList: ArrayList<SpecialEquipData> = arrayListOf()
) : Data() {

    /// 基本配置列表 ====================================================================
    class EquipLabelData(
        var text : String = " ",
        var pics : ArrayList<String> = arrayListOf(),
        var bgColor: Color = Color.WHITE,
        var fontColor: Color = Color.BLACK
    ) {

        fun pharse(doc: Element) : EquipLabelData {
            val td = doc.children()[0]

            // 获取字体颜色及背景颜色
            var style = td.attr("style")
            if (style.contains("color:red")) {
                fontColor = Color.RED
            }

            val regex = Regex("background:(#[^;]+);(.*)")
            regex.matchEntire(style)?.let {
                bgColor = PaintUtils.hex2Color(it.groupValues[1])
            }

            text = td.text()
            if (td.text().isEmpty()) {
                // 是空项
                text = "   "
            }
            else {
                val images = td.select("img")
                images.forEach{ img ->
                    pics.add(img.attr("src").replace("30px", "60px"))
                }
            }
            return this
        }

    }

    /// 特殊配装===================================================================
    data class EquipIcon(
        val name : String,
        val url : String
    )

    class SpecialEquipData(
        val list : ArrayList<EquipIcon> = arrayListOf(),
        var msg : String = "",
        var recommender : String = ""
    ) {
        fun pharse(doc: Element) : SpecialEquipData {

            doc.select("img").forEach {
                list.add(EquipIcon(
                    it.attr("alt").split(".")[0],
                    it.attr("src")
                ))
            }

            val tdList = doc.select("td")
            msg = tdList.last().text()
            recommender = tdList[tdList.size - 2].text()

            return this
        }
    }

    override fun parse(doc: Document, commandList: List<String>): Data {

        val tables = doc.select("div[class='row']")[0].children()
        for (i in tables.indices) {
            val trs = tables[i].select("tbody")[0].children()
            for (j in trs.indices) {
                equipLists[i].add(EquipLabelData().pharse(trs[j]))
            }
        }

            val tabpanel = doc.select("div[class='row']")[0].parent()
        tabpanel.children().forEach{
            if (it.className().contains("pverecommend")) {
                specialEquipList.add(SpecialEquipData().pharse(it))
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

