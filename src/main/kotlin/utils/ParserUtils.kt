package org.iris.wiki.utils

import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import org.iris.wiki.config.CommandString
import org.iris.wiki.data.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document



object ParserUtils {

    fun parse(data: String, commandList: List<String>) : Data? {
        val doc = Jsoup.parse(data)

        if (doc.select("h1[id='firstHeading']").text() == "搜索结果") {
            return SearchData().parse(doc, commandList)
        }

        val linkTitle = doc.select("a[title='首页']")
        val type = linkTitle.next().attr("title")
        return when(type) {
            "舰娘图鉴" -> parseShip(doc, commandList)
            in arrayOf("鱼雷", "防空炮", "舰炮", "设备", "舰载机") -> parseEquip(doc, commandList)
            "装备分析" -> {
                return if (linkTitle.next().next().text().equals("装备一图榜")) {
                    parseEquipTop(doc)
                } else {
                    null
                }
            }
            else -> null
        }
    }

    // 舰船页面解析
    private fun parseShip(doc: Document, commandList: List<String>) : Data {
        return when (commandList[2]) {
            COMMON -> ShipData().parse(doc, commandList)
            in CommandString.test -> ShipAttrData().parse(doc, commandList)
            in CommandString.attribute -> ShipData().parse(doc, commandList)
//            BOAT_UPDATE -> parseShipUpadta(doc)
            in CommandString.dress -> ImagesData().parse(doc, commandList)
            in CommandString.voice_map -> AudioData().parse(doc, commandList)
            else -> TextData(MESSAGE_ERROR)
        }

    }



    // 解析舰船改造需求
    private fun parseShipUpadta(doc: Document) : String {
        val span = doc.select("span[id='改造详情']")
        if (span.isEmpty()) {
            return BOAT_NO_UPDATA
        }
        val tables = span[0].parent().nextElementSibling().select("table")
        return BOAT_UPDATE + tables[1].select("tr").last().text()
    }



    private fun parseEquip(doc: Document, commandList: List<String>) : Data? {
        return when (commandList[2]) {
            in CommandString.from -> EquipData().parse(doc, commandList)
            in CommandString.attribute -> EquipAttrData().parse(doc, commandList)
            else -> TextData(MESSAGE_ERROR)
        }
    }


    // 装备一图榜解析
    private fun parseEquipTop(doc: Document): ImagesData {
        return ImagesData(arrayListOf("https://patchwiki.biligame.com/images/blhx/thumb/a/ae/gm88lkv52ctg4hrs4shcxq049vx7w75.jpg/1170px-%E8%A3%85%E5%A4%87%E4%B8%80%E5%9B%BE%E6%A6%9C.jpg.png"))
    }

    //
    fun parseShipTop(doc: Document) : ImagesData {
        return ImagesData(arrayListOf("装备一图榜"))
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