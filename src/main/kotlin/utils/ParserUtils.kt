package org.iris.wiki.utils

import net.mamoe.mirai.message.data.Audio
import org.iris.wiki.config.CommandString
import org.iris.wiki.data.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


object ParserUtils {

    fun parse(data: String, commandList: List<String>) : Any? {
        val doc = Jsoup.parse(data)

        if (doc.select("h1[id='firstHeading']").text() == "搜索结果") {
            return parseSearch(doc)
        }

        val linkTitle = doc.select("a[title='首页']")
        val type = linkTitle.next().attr("title")
        return when(type) {
            "舰娘图鉴" -> parseBoat(doc, commandList)
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
    private fun parseBoat(doc: Document, commandList: List<String>) : Any {
        return when (commandList[2]) {
            COMMON -> parseBoatCommon(doc)
            in CommandString.attribute -> parseBoatCommon(doc)
//            BOAT_SKILL -> parseBoatSkill(doc)
            BOAT_UPDATE -> parseBoatUpadta(doc)
            BOAT_DRESS -> {
                val result = parseBoatDress(doc)
                if (result.images.isEmpty()) {
                    return ImagesData(arrayListOf(NO_DRESS_URL))
                }
                return result
            }
            in CommandString.voice_map -> {
                val result = parseBoatVoice(doc, commandList)
                if (result == null) {
                    return "该舰娘没有此类别语音哦~"
                }
                else {
                    return result
                }
            }
            else -> MESSAGE_ERROR
        }

    }

    // 解析舰船基本信息
    private fun parseBoatCommon(doc: Document) : BoatData {
        val boat = BoatData()

        val tableGeneral = doc.select("table[class='wikitable sv-general']") //wikitable sv-category
        val trList = tableGeneral[0].select("tr")

        // 第一行为舰船名称
        boat.name = trList[0].text()

        // 第二行第5列中含有舰船类型
        boat.type = trList[1].select("td")[4].text()

        boat.level = trList[2].select("span[id='PNrarity']")[0].text()

        boat.camp = trList[2].select("td")[3].text()

        boat.time = trList[3].select("td")[1].text()

        boat.normal = trList[4].select("td")[1].text()

        boat.active = trList[5].select("td")[1].text()

        if (trList[6].select("td")[0].text().equals("其他途径")) {
            boat.other = trList[6].select("td")[1].text()
        }


        boat.pic = doc
            .select("div[class='tab_con active']")[0]
            .select("img")[0]
            .attr("src")

        return boat
    }


    // 解析舰船技能
    private fun parseBoatSkill(doc: Document) : SkillListData {
        val skillList = arrayListOf<SkillData>()
        val table = doc.select("table[class='wikitable sv-skill']")
        val trList = table.select("tr")
        for (i in 1 until trList.size) {
            if (trList[i].attr("style") != "display:none") {
                val tdList = trList[i].select("td")
                skillList.add(
                    SkillData(
                        tdList[0].text(),
                        tdList[0].select("img").attr("src"),
                        tdList[1].text()
                    )
                )
            }
        }
        return SkillListData(skillList)
    }

    // 解析舰船皮肤
    private fun parseBoatDress(doc: Document) : ImagesData {
        val images = arrayListOf<String>()

        val table = doc.select("div[class='Contentbox2']")
        val imgs = table.select("img")
        for (i in 1 until imgs.size) {
            images.add(imgs[i].attr("src"))
        }
        return ImagesData(images)
    }

    // 解析舰船改造需求
    private fun parseBoatUpadta(doc: Document) : String {
        val span = doc.select("span[id='改造详情']")
        if (span.isEmpty()) {
            return BOAT_NO_UPDATA
        }
        val tables = span[0].parent().nextElementSibling().select("table")
        return BOAT_UPDATE + tables[1].select("tr").last().text()
    }

    // 舰船语音
    private fun parseBoatVoice(doc: Document, commandList: List<String>) : AudioData? {
//        val trList = doc.select("tr[data-key=\"${CommandString.voice_map[commandList[2]]}\"")
//        if (trList.isEmpty()) return null
//        val linkList = trList.select("a")
//        if (linkList.isEmpty()) return null

        val table = doc.select("table[class='table-ShipWordsTable']")
        val trList = table.select("tr")
        val linkList = arrayListOf<Element>()
        trList.forEach{ it ->
            if (it.attr("data-key") == CommandString.voice_map[commandList[2]]) {
                it.select("a").forEach { iit->
                    linkList.add(iit)
                }
            }
        }
        if (linkList.isEmpty()) {
            return null
        }
        return AudioData(linkList[(0 until linkList.size).random()].attr("href"))
    }


    private fun parseEquip(doc: Document, commandList: List<String>) : Any {
        return when (commandList[2]) {
            in CommandString.from -> parseEquipCommon(doc)
            in CommandString.attribute -> parseEquipAttr(doc)
            else -> MESSAGE_ERROR
        }
    }

    private fun parseEquipCommon(doc: Document) : EquipData {

        val equip = EquipData()

        equip.camp = doc.select("li[class='active']").last().text()

        val ul = doc.select("ul[class='equip']")[0]
        val liList = ul.children()
        equip.name = liList[0].text()
        equip.type = liList[1].text()
        equip.pic = liList[1].select("img")[0].attr("src")

        val table = doc.select("table[class='table table-bordered']")[0]
        val tdList = table.select("td")
        equip.from = tdList[0].text()
        equip.piece = tdList[2].text()

        val route : ArrayList<String> = arrayListOf()
        val routePic : ArrayList<String> = arrayListOf()
        for (img in tdList[1].select("img")) {
            route.add(img.attr("alt").dropLast(4))
            routePic.add(img.attr("src"))
        }
        equip.route = route
        equip.routePic = routePic

        return equip
    }

    // 装备通用属性
    private fun parseEquipAttr(doc: Document) : EquipAttrData {

        val levelMap = mapOf<String, Int>(
            "text-align:center;font-size:1.2em;background:#dbdcdf" to 1,
            "text-align:center;font-size:1.2em;background:#1bb7eb" to 2,
            "text-align:center;font-size:1.2em;background:#ae90ef" to 3,
            "text-align:center;font-size:1.2em;background:#f9f593" to 4,
            "text-align:center;font-size:1.2em;background:linear-gradient(135deg,#59AE6A,#48AE96,#60D9EC,#65A5D5,#9491E0,#C382A4)" to 5
        )

        val equip = EquipAttrData()


        val ul = doc.select("ul[class='equip']")[0]

        val liList = ul.children()
        equip.name = liList[0].text()
        equip.type = liList[1].select("div")[0].text()
        equip.tag = liList[1].select("div")[1].text()
        equip.tno = liList[1].select("b").last().text()[1].digitToInt()
        equip.pic = liList[1].select("img")[0].attr("src")

        equip.level = levelMap.get(liList[0].attr("style"))!!
        for (i in 2 until liList.size-1) {
            var tab = 0
            val node = liList[i]
            equip.attr += getAttrText(node, 0)
        }
        equip.attr += "适用舰种"
        equip.use = liList.last().select("td[class='appShipType']").text()
        return equip
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

    // 装备一图榜解析
    private fun parseEquipTop(doc: Document): ImagesData {
        return ImagesData(arrayListOf("https://patchwiki.biligame.com/images/blhx/thumb/a/ae/gm88lkv52ctg4hrs4shcxq049vx7w75.jpg/1170px-%E8%A3%85%E5%A4%87%E4%B8%80%E5%9B%BE%E6%A6%9C.jpg.png"))
    }

    //
    fun parseBoatTop(doc: Document) : ImagesData {
        return ImagesData(arrayListOf("装备一图榜"))
    }

    private fun parseSearch(doc: Document) : SearchData {
        val result : ArrayList<String> = arrayListOf()

        val divList = doc.select("div[class='searchresults']")[0].select("div[class='mw-search-result-heading']")
        for (div in divList) {
            result.add(div.child(0).attr("title"))
        }

        return SearchData(result)
    }
}