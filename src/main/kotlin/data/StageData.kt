package org.iris.wiki.data

import kotlinx.serialization.SerialName
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.paint.component.EquipAttrComponent
import org.iris.wiki.paint.component.StageComponent
import org.iris.wiki.utils.ParserUtils.isDisplayNone
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

class StageData(
    var name: String = "",
    var pic: String = "",
    var timesMin: String = "", // 旗舰刷新战斗次数
    var costLimit: String = "该关卡没有油耗上限喵",
    var level: String = "",
    var exp: String = "",
    var boss: String = "",
    var bossLevel: String = "",
    var bossExp: String = "",
    var dropTable: DropTable = DropTable()
) : Data() {

    override fun parse(doc: Document, commandList: List<String>): Data {
        val trList = doc.select("table[class='wikitable']")[0].select("tr")
        name = trList[0].text().split("：")[0]


        trList.forEach {
            if (!it.isDisplayNone()) {
                val label = it.select("th").text()
                when {
                    label.contains("敌舰等级") -> {
                        val text = it.select("td").text().split(" ：")
                        level = text[0]
                        var i = 1
                        it.select("td").select("img").forEach {
                            exp += it.attr("alt").split(".")[0]
                            exp += ":${text[i++]}"
                        }
                    }
                    label.contains("BOSS位置") -> {
                        // 第一行Boss位置    中间Boss属性  倒数第二行boss等级 最后一行boss经验
                        val textList = it.select("td").html().split("<br>")
                        (1 until textList.size-2).forEach {i ->
                            boss += textList[i] + "|||"
                        }
                        bossLevel = textList[textList.size - 2]
                        bossExp = textList.last().split("：").last()
                    }
                    label.contains("油耗上限") -> {
                        costLimit = it.select("td").text()
                    }
                    label.contains("旗舰刷新") -> {
                        timesMin = it.select("td").text()
                    }

                }
            }
        }

        pic = doc.select("table[class='wikitable']")[1].select("img")[0].attr("src")

        dropTable.parse(doc)

        return this
    }

    override suspend fun toMessage(sender: Member): Message {
        val path = "${CommonConfig.stage_output_path}/${name}.png"

        // 检测输出文件夹是否存在
        for (path in listOf(CommonConfig.output_path, CommonConfig.stage_output_path)) {
            val folder = File(path)
            if (!folder.exists() && !folder.isDirectory) {
                folder.mkdir()
            }
        }


        val file = File(path)
        if (!file.exists()) {
            val stageComponent = StageComponent(this)
            stageComponent.init()
            val image = stageComponent.draw()
            ImageIO.write(image, "png", file)
        }
        val src = file.toExternalResource()
        val imageId: String = src.uploadAsImage(sender.group).imageId
        src.close()
        return Image(imageId)
    }
}

class DropTable(
    var dropLineList: ArrayList<DropLine> = arrayListOf<DropLine>()
) {
    fun parse(doc: Document): DropTable {
        val trList = doc.select("table[class='table-DropList']")[0].select("tr")
        trList.forEach {
            dropLineList.add(DropLine().parse(it))
        }
        return this
    }
}

class DropLine(
    var label: String = "",
    var dropItemList: ArrayList<DropItem> = arrayListOf<DropItem>()
) {
    fun parse(node: Element): DropLine {

        label = node.select("th").text()
        node.select("td")[0].select("span[class='xtb-image']").forEach {
            dropItemList.add(DropItem().parse(it))
        }
        return this
    }
}

class DropItem(
    var pic: String = "",
    var name: String = "",
    var color: String = "#ffffff"
) {

    fun parse(node: Element): DropItem {
        pic = node.select("img").attr("src")
        name = if (node.select("span[class='nowrap']").isEmpty()) {
            // 装备掉落
            node.text()
        } else {
            // 舰娘掉落
            try {
                val name1 = node.select("a")[0].attr("title")
                val name2 = node.select("span[class='AF']")[0].text()
                color = node.select("span[class='nowrap']")[0].attr("style").split("color:").last()
                if (name1 == name2) {
                    name1
                } else {
                    "$name2($name1)"
                }
            }
            catch (e:Exception) {
                println(node.html())
                " "
            }

        }
        return this
    }
}