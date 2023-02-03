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
data class EquipEfficiencyData(
    @SerialName("槽位")
    var index: String = "",
    @SerialName("装备类型")
    var name: String = "",
    @SerialName("效率")
    var efficiency: String = "",
    @SerialName("武器数")
    var count: String = "",
    @SerialName("预装填数")
    var prefill_count: String = "",
)

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
    @SerialName("相关活动")
    var active: String = "",
    @SerialName("建造时间")
    var time: String = "",
    @SerialName("pic")
    var pic: String = "",
    @SerialName("改造")
    var canUpgrade: Boolean= false,

    @SerialName("普通掉落点")
    var normal_from: String = "",
    @SerialName("活动掉落点")
    var active_from: String = "",
    @SerialName("档案掉落点")
    var file_from: String = "",
    @SerialName("其他来源")
    var other_from: String = "",


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
    var skill : ArrayList<SkillData> = arrayListOf(),

    @SerialName("装备详情")
    var equip_detail : ArrayList<EquipEfficiencyData> = arrayListOf()
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
                label.contains("普通掉落点") -> normal_from = trList[i].select("td")[1].text()
                label.contains("活动 掉落点") -> active_from = trList[i].select("td")[1].text()
                label.contains("档案掉落点") -> file_from = trList[i].select("td")[1].text()
                label.contains("相关活动") -> active = trList[i].select("td")[1].text()
                label.contains("其他途径") -> other_from = trList[i].select("td")[1].text()
            }
        }
        time = time.replace("活动", "${active}活动")
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

        // 小柴郡wiki上面没有称号，需要特殊判断
        if (name.equals("HMS Little Cheshire")) {
            name = "小柴郡"
            code = "HMS Little Cheshire"
        }

        val picList = doc
            .select("div[class='Contentbox2']")[0]
            .select("div")
        pic = try {
            if (canUpgrade) {
                picList[picList.size - 2].select("img")[0].attr("src")
            } else {
                picList[0].select("img")[0].attr("src")
            }
        }
        catch (e:java.lang.Exception) {
            ""
        }

        val tableAttr = doc.select("table[class='wikitable sv-performance']")
        trList = tableAttr[1].child(0).children()
        var tdList = arrayListOf<Element>()
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
        var table = doc.select("table[class='wikitable sv-skill']")
        trList = table.select("tr")

        for (i in (if (canUpgrade) 2 else 1) until trList.size) {
            try {
                if (trList[i].attr("style") != "display:none" || (canUpgrade && trList[i].className() == "tjmode1")) {
                    val tdList = trList[i].select("td")
                    var detail = tdList[1].text()
                    if (tdList[1].select("div[class='resp-tab-content']").size >= 2) {
                        detail = tdList[1].select("div[class='resp-tab-content']")[0].text()
                    }
                    var skill_name = tdList[0].text()
                    if (detail.contains("（天运拟合")) {
                        skill_name += "+"
                        detail = detail.split("+：").last()
                    }
                    skill.add(
                        SkillData(
                            skill_name,
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

        // 解析装备详情
        table = doc.select("table[class='wikitable sv-equipment']")
        trList = table.select("tr")
        equip_detail.add(EquipEfficiencyData("槽位", "装备类型", "效率", "武器数", "预装填数"))
        for (i in 2..4) {
            try {
                tdList = trList[i + if (canUpgrade) 1 else 0].select("td")
                val equipEfficiencyData = EquipEfficiencyData()
                equipEfficiencyData.index = tdList[0].text()
                equipEfficiencyData.name = tdList[1].select("span[class=\"tjmode1\"]").text()
                equipEfficiencyData.efficiency = tdList[2].select("span[class=\"tjmode1\"]").text()
                equipEfficiencyData.count = tdList[3].select("span[class=\"tjmode1\"]").text()
                equipEfficiencyData.prefill_count = tdList[4].select("span[class=\"tjmode1\"]").text()
                equip_detail.add(equipEfficiencyData)
            }
            catch (e : Exception) {
                println(trList[i].html())
            }
        }

        return super.parse(doc, commandList)
    }

    override suspend fun toMessage(sender: Member): Message {

        val path = "${CommonConfig.ship_output_path}/${name}.png"

        // 检测输出文件夹是否存在
        for (path in listOf(CommonConfig.output_path, CommonConfig.ship_output_path)) {
            val folder = File(path)
            if (!folder.exists() && !folder.isDirectory) {
                folder.mkdir()
            }
        }

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
