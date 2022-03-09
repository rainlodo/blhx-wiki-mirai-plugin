package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.paint.component.ShipAttrComponent
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.File
import javax.imageio.ImageIO

@Serializable
data class ShipAttrData(
    @SerialName("名称")
    var name: String = "",
    @SerialName("代号")
    var code: String = "",
    @SerialName("类型")
    var type: String = "",
    @SerialName("稀有度")
    var level: String = "",
    @SerialName("阵营")
    var camp: String = "",
    @SerialName("建造时间")
    var time: String = "",
    @SerialName("pic")
    var pic: String = "",
    @SerialName("改造")
    var canUpgrade: Boolean= false,

    @SerialName("普通掉落点")
    var normal: String = "",
    @SerialName("活动掉落点")
    var active: String = "",
    @SerialName("其他来源")
    var other: String = "",


    @SerialName("耐久")
    var naijiu : String = "",
    @SerialName("装甲")
    var zhuangjia : String = "",
    @SerialName("装填")
    var zhuangtian : String = "",
    @SerialName("炮击")
    var paoji : String = "",
    @SerialName("雷击")
    var leiji : String = "",
    @SerialName("机动")
    var jidong : String = "",
    @SerialName("防空")
    var fangkong : String = "",
    @SerialName("航空")
    var hangkong : String = "",
    @SerialName("命中")
    var mingzhong : String = "",
    @SerialName("反潜")
    var fanqian : String = "",
    @SerialName("幸运")
    var xingyun : String = "",
    @SerialName("航速")
    var hangsu : String = "",
    @SerialName("氧气")
    var yangqi : String = "",
    @SerialName("弹药")
    var danyao : String = "",
    @SerialName("消耗")
    var xiaohao : String = "",

    @SerialName("技能")
    var skill : ArrayList<SkillData> = arrayListOf()
) : Data() {


    private fun getText(td: Element) : String {
        var res = ""
        if (td.select("span[class='tjmode1']").isNotEmpty()) {
            res = td.select("span[class='tjmode1']").text().split("→").last()
            if (res.startsWith("{") || res.isEmpty()) {
                res = td.select("span[class='tjmode0']").text().split("→").last()
            }
        }
        else {
            res = td.text().split("→").last()
        }
        return res
    }


    override fun parse(doc: Document, commandList: List<String>) : Data {

        val tableGeneral = doc.select("table[class='wikitable sv-general']")
        var trList = tableGeneral[0].select("tr")

        // 第一行为舰船名称
        val nameList = trList[0].select("b")

        // 第二行第5列中含有舰船类型
        type = trList[1].select("td")[4].text()

        level = trList[2].select("span[id='PNrarity']")[0].text()

        camp = trList[2].select("td")[3].text()

        for (i in 3 until trList.size) {
            val label = trList[i].select("td")[0].text()
            when {
                label.contains("建造时间") -> time = trList[i].select("td")[1].text()
                label.contains("普通掉落点") -> normal = trList[i].select("td")[1].text()
                label.contains("活动/档案") -> active = trList[i].select("td")[1].text()
                label.contains("其他途径") -> other = trList[i].select("td")[1].text()
            }
        }
        canUpgrade = doc.select("span[id='改造详情']").isNotEmpty()
        if (canUpgrade) {
            type = doc.select("span[id='改造详情']")[0].parent()
                .nextElementSibling()
                .child(0)
                .text()
                .split(" ")
                .last()
        }
        var index = 0
        // 当舰娘为以下阵营时(不是μ船)，名字前会有对应的量级，应该去掉
        if (camp in listOf("白鹰", "白鹰", "皇家", "重樱",
                "铁血", "东煌", "哔哩哔哩", "撒丁帝国", "北方联合",
                "自由鸢尾", "维希教廷") && !nameList[0].text().contains("μ")) {
            index++
        }
        name += nameList[index].text()
        if (canUpgrade) name += ".改"
        index++
        if (index < nameList.size - 2) {
            name += nameList[index].text().replace("·META", "")
            index++
        }
        code = nameList[index].text()

        val picList = doc
            .select("div[class='Contentbox2']")[0]
            .select("div")
        pic = if (canUpgrade) {
            picList[picList.size - 2].select("img")[0].attr("src")
        } else {
            picList[0].select("img")[0].attr("src")
        }

        val tableAttr = doc.select("table[class='wikitable sv-performance']")
        trList = tableAttr[1].child(0).children()
        val tdList = arrayListOf<Element>()
        for (i in 1..7) {
            tdList.addAll(trList[i].children())
        }
        for (i in 0 until tdList.size) {
            if (tdList[i].children().isNotEmpty()) {
                when (tdList[i].text()) {
                    "耐久" -> naijiu = getText(tdList[i+1])
                    "装甲" -> zhuangjia = getText(tdList[i+1])
                    "装填" -> zhuangtian = getText(tdList[i+1])
                    "炮击" -> paoji = getText(tdList[i+1])
                    "雷击" -> leiji = getText(tdList[i+1])
                    "机动" -> jidong = getText(tdList[i+1])
                    "防空" -> fangkong = getText(tdList[i+1])
                    "航空" -> hangkong = getText(tdList[i+1])
                    "命中" -> mingzhong = getText(tdList[i+1])
                    "反潜" -> fanqian = getText(tdList[i+1])
                    "幸运" -> xingyun = getText(tdList[i+1])
                    "消耗" -> xiaohao = getText(tdList[i+1])
                    "航速" -> hangsu = getText(tdList[i+1])
                    "氧气" -> yangqi = getText(tdList[i+1])
                    "弹药量" -> danyao = getText(tdList[i+1])
                }
            }
        }
        val table = doc.select("table[class='wikitable sv-skill']")
        trList = table.select("tr")

        for (i in (if (canUpgrade) 2 else 1) until trList.size) {
            try {
                if (trList[i].attr("style") != "display:none" || (canUpgrade && trList[i].className() == "tjmode1")) {
                    val tdList = trList[i].select("td")
                    var detail = tdList[1].text()
                    if (tdList[1].select("div[class='resp-tab-content']").size >= 2) {
                        detail = tdList[1].select("div[class='resp-tab-content']")[0].text()
                    }
                    skill.add(
                        SkillData(
                            tdList[0].text(),
                            tdList[0].select("img").attr("src"),
                            detail
                        )
                    )
                }
            }
            catch (e : Exception) {
//                println(trList[i].html())
            }
        }

        return super.parse(doc, commandList)
    }

    override suspend fun toMessage(sender: Member): Message {

        val path = "${CommonConfig.ship_output_path}/${name}.png"
        val file = File(path)
        if (!file.exists()) {
            val shipAttrComponent = ShipAttrComponent(this)
            shipAttrComponent.init()
            val image = shipAttrComponent.draw()
            ImageIO.write(image, "png", file)
        }
        val src = file.toExternalResource()
        val imageId: String = src.uploadAsImage(sender.group).imageId
        src.close()

        return Image(imageId)
    }
    
}
