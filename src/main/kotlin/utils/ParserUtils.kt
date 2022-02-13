package org.iris.wiki.utils

import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import org.iris.wiki.config.BOAT_NO_UPDATA
import org.iris.wiki.config.BOAT_UPDATE
import org.iris.wiki.config.CommandConfig
import org.iris.wiki.config.MESSAGE_ERROR
import org.iris.wiki.data.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


object ParserUtils {

    fun parse(data: String, commandList: List<String>) : Data? {
        val doc = Jsoup.parse(data)
        try {
            if (doc.select("h1[id='firstHeading']").text() == "搜索结果") {
                return SearchData().parse(doc, commandList)
            }

            val linkTitle = doc.select("a[title='首页']")
            val type = linkTitle.next().attr("title")
            // 根据副标题来判断当前页面类型 QAQ
            return when (type) {
                "舰娘图鉴" -> parseShip(doc, commandList)
                in arrayOf("鱼雷", "防空炮", "舰炮", "设备", "舰载机", "导弹") -> parseEquip(doc, commandList)
//                "装备分析" -> {
//                    return if (linkTitle.next().next().text().equals("装备一图榜")) {
//                        parseEquipTop(doc, commandList)
//                    } else {
//                        null
//                    }
//                }
                else -> {
                    if (doc.select("h1[id='firstHeading']").text().contains(Regex("[榜表]"))) {
                        pasreTable(doc, commandList)
                    } else {
                        null
                    }
                }
            }
        }
        catch (e: Exception) {
            return TextData("解析发生错误\n$e")
        }

    }

    // 舰船页面解析
    private fun parseShip(doc: Document, commandList: List<String>) : Data {
        if (commandList.size == 2) {
            return ShipAttrData().parse(doc, commandList)
        }
        return when (commandList[2]) {
            in CommandConfig.attribute -> ShipAttrData().parse(doc, commandList)
            in CommandConfig.from -> ShipData().parse(doc, commandList)
//            BOAT_UPDATE -> parseShipUpadta(doc)
            in CommandConfig.dress -> ImagesData().parse(doc, commandList)
            in CommandConfig.voice_map -> AudioData().parse(doc, commandList)
            in CommandConfig.tech -> parseShipTech(doc, commandList)
            in CommandConfig.evaluate -> parseShipEvaluate(doc)
            in CommandConfig.equip -> ShipEquipData().parse(doc, commandList)
            else -> TextData(MESSAGE_ERROR)
        }

    }

    // 解析舰船改造需求
    private fun parseShipUpdate(doc: Document) : String {
        val span = doc.select("span[id='改造详情']")
        if (span.isEmpty()) {
            return BOAT_NO_UPDATA
        }
        val tables = span[0].parent().nextElementSibling().select("table")
        return BOAT_UPDATE + tables[1].select("tr").last().text()
    }

    // 解析舰船提供的科技点
    private fun parseShipTech(doc: Document, commandList: List<String>) : Data {
        val table = doc.select("table[class='wikitable sv-category']")[0]
        val trList = table.child(0).children()

        val point = trList[2].select("td")[1].text()
        if (point == "+") {
            return TextData("该舰娘不提供科技点哦~")
        }
        var text = commandList[1] + "\n"
        text += "获取时科技点$point\n"
        text += "满星时科技点${trList[3].select("td")[1].text()}\n"
        text += "120级时科技点${trList[4].select("td")[1].text()}\n"
        text += "属性加成:\n"
        text += "获取时 ${trList[2].select("td").last().text()}\n"
        text += "120级时 ${trList[4].select("td").last().text()}"
        return TextData(text)
    }

    // 舰娘评价
    private fun parseShipEvaluate(doc: Document) : Data {
        val table = doc.select("table[class='wikitable sv-remark']")[0]
        val trList = table.child(0).children()
        var text = trList[1].html()
            .replace("<br>", "\n")
            .replace(Regex("<[^>]+>"), "")
        if (text == "") {
            text = "还没有人对该舰娘评价哦~"
        }
        return TextData(text).activateAt()
    }

    // 装备页面解析
    private fun parseEquip(doc: Document, commandList: List<String>) : Data? {
        if (commandList.size == 2) {
            return EquipAttrData().parse(doc, commandList)
        }
        return when (commandList[2]) {
            in CommandConfig.from -> EquipData().parse(doc, commandList)
            in CommandConfig.attribute -> EquipAttrData().parse(doc, commandList)
            else -> TextData(MESSAGE_ERROR)
        }
    }


    // 装备一图榜解析
    private fun parseEquipTop(doc: Document, commandList: List<String>): ImagesData {
        val url = doc.select("img[alt='装备一图榜.jpg']").attr("src")
            .split(Regex("/[\\d]*px"))[0].replace("/thumb", "")
        return ImagesData(arrayListOf(url))
    }

    // PVE舰娘一图榜解析
    private fun parseShipTop(doc: Document, commandList: List<String>): ImagesData {
        val url = doc.select("div[id='mc_collapse-1']").select("img")[0].attr("src")
        return ImagesData(arrayListOf(url))
    }

    // 榜单解析
    private fun pasreTable(doc: Document, commandList: List<String>): ImagesData {
        when (commandList[1]) {
            "装备一图榜" -> return parseEquipTop(doc, commandList)
            "PVE用舰船综合性能强度榜" -> return parseShipTop(doc, commandList)
            else -> {
                val imagesData = ImagesData()
                doc.select("span[id='${commandList[1]}']")[0].parent().nextElementSibling().select("img").forEach {
                    val url = it.attr("src").split(Regex("/[\\d]*px"))[0].replace("/thumb", "")
                    println(url)
                    imagesData.images.add(url)
                }
                return imagesData
            }
        }


    }

    fun wordToPinyin(text : String) : String {
        val format = HanyuPinyinOutputFormat()
        format.caseType = HanyuPinyinCaseType.LOWERCASE
        var res = ""
        text.forEach {
            if (codeType(it) == 1)
                res += PinyinHelper.toHanyuPinyinStringArray(it, format)[0][0]
            else
                res += it
        }
        return res
    }

    fun codeType(ch: Char): Int {
        return if (ch in '\u4e00'..'\u9fa5') 1 //中文字符
        else if (ch in '\u0030'..'\u0039') 2//数字字符
        else if ((ch in '\u0041'..'\u005A') or (ch in '\u0061'..'\u007A')) 3//英文字符
        else 0
    }
}