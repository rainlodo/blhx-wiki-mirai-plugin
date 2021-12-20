package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Message
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Serializable
data class BoatAttrData(
    @SerialName("名称")
    var name: String = "",
    @SerialName("类型")
    var type: String = "",
    @SerialName("稀有度")
    var level: String = "",
    @SerialName("阵营")
    var camp: String = "",
    @SerialName("建造时间")
    var time: String? = null,
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


    @SerialName("获得科技点")
    var tech_gain: String = "",
    @SerialName("满级科技点")
    var tech_full: String = "",

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
) : Data() {

    override fun parse(doc: Document, commandList: List<String>) : Data {

        val tableGeneral = doc.select("table[class='wikitable sv-general']")
        var trList = tableGeneral[0].select("tr")

        // 第一行为舰船名称
        name = trList[0].text()

        // 第二行第5列中含有舰船类型
        type = trList[1].select("td")[4].text()

        level = trList[2].select("span[id='PNrarity']")[0].text()

        camp = trList[2].select("td")[3].text()

        time = trList[3].select("td")[1].text()

        normal = trList[4].select("td")[1].text()

        active = trList[5].select("td")[1].text()

        if (trList[6].select("td")[0].text().equals("其他途径")) {
            other = trList[6].select("td")[1].text()
        }
        canUpgrade = doc.select("span[id='改造详情']").isNotEmpty()

        pic = doc
            .select("div[class='tab_con active']")[0]
            .select("img")[0]
            .attr("src")

        val tableAttr = doc.select("table[class='wikitable sv-performance']")
        trList = tableAttr[0].child(0).children()
        val tdList = arrayListOf<Element>()
        for (i in 3..8) {
            tdList.addAll(trList[i].children())
        }
        for (i in 0 until tdList.size) {
            if (tdList[i].children().isNotEmpty()) {
                when (tdList[i].text()) {
                    "耐久" -> naijiu = tdList[i+1].text()
                    "装甲" -> zhuangjia = tdList[i+1].text()
                    "装填" -> zhuangtian = tdList[i+1].text()
                    "炮击" -> paoji = tdList[i+1].text()
                    "雷击" -> leiji = tdList[i+1].text()
                    "机动" -> jidong = tdList[i+1].text()
                    "防空" -> fangkong = tdList[i+1].text()
                    "航空" -> hangkong = tdList[i+1].text()
                    "命中" -> mingzhong = tdList[i+1].text()
                    "反潜" -> fanqian = tdList[i+1].text()
                    "幸运" -> xingyun = tdList[i+1].text()
                    "消耗" -> xiaohao = tdList[i+1].text()
                    "航速" -> hangsu = tdList[i+1].text()
                    "氧气" -> yangqi = tdList[i+1].text()
                    "弹药量" -> danyao = tdList[i+1].text()
                }
            }
        }
        return super.parse(doc, commandList)
    }

    override suspend fun toMessage(sender: Member): Message {
        return super.toMessage(sender)
    }
    
}
