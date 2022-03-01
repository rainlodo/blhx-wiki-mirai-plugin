package org.iris.wiki.utils

import org.iris.wiki.config.*
import org.iris.wiki.data.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File


object ParserUtils {

    fun parse(data: String, commandList: List<String>) : Data? {
        val doc = Jsoup.parse(data)
        try {
            if (doc.select("h1[id='firstHeading']").text() == "搜索结果") {
                val list = search(commandList)
                if (list.isNotEmpty()) return searchResult2Data(list, commandList)

                return SearchData().parse(doc, commandList)
            }

            val linkTitle = doc.select("a[title='首页']")
            val type = linkTitle.next().attr("title")
            // 根据副标题或者heading来判断当前页面类型 QAQ
            return when (type) {
                "舰娘图鉴" -> parseShip(doc, commandList)
                in arrayOf("鱼雷", "防空炮", "舰炮", "设备", "舰载机", "导弹") -> parseEquip(doc, commandList)
                else -> {
                    when{
                        doc.select("h1[id='firstHeading']").text().contains(Regex("[榜表]")) -> parseTable(doc, commandList)
                        doc.select("h1[id='firstHeading']").text().equals("建造时间") -> parseBuildTime(doc, commandList)
                        else -> null
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
            in CommandConfig.dressLarge -> parseDressLarge(doc, commandList)
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
    private fun parseTable(doc: Document, commandList: List<String>): ImagesData? {
        when (commandList[1]) {
            "装备一图榜" -> return parseEquipTop(doc, commandList)
            "PVE用舰船综合性能强度榜" -> return parseShipTop(doc, commandList)
            else -> {
                val imagesData = ImagesData()
                doc.select("span[id='${commandList[1]}']")[0].parent().nextElementSibling().select("img").forEach {
                    val url = it.attr("src").split(Regex("/[\\d]*px"))[0].replace("/thumb", "")
                    imagesData.images.add(url)
                }
                if (imagesData.images.isEmpty()) {
                    return null
                }
                return imagesData
            }
        }
    }

    // 建造时间表解析
    private fun parseBuildTime(doc: Document, commandList: List<String>): Data? {
        when {
            commandList.size == 2 -> {
                return try {
                    TextData(doc.select("div[class='panel panel-info']")[0].text().replace(Regex("[ 、]"), "\n"))
                } catch (e: Exception) {
                    TextData("现在还没有活动池喵")
                }
            }
            commandList[2].matches(Regex("(\\d[:：])?(\\d)?\\d[:：]\\d\\d")) -> {
                var time = "0:00:00"
                when {
                    commandList[2].matches(Regex("\\d[:：]\\d\\d")) -> time = "0:0" + commandList[2].replace("：", ":")
                    commandList[2].matches(Regex("\\d\\d[:：]\\d\\d")) -> time = "0:" + commandList[2].replace("：", ":")
                    else -> time = commandList[2].replace("：", ":")
                }

                val trList = doc.select("table[class='wikitable sortable']")[0].child(0).children()
                trList.removeAt(0)

                val timeList = arrayListOf<String>()
                trList.select("th").forEach {
                    timeList.add(it.text())
                }

                // 输入时间不是正确的建造时间
                if (!timeList.contains(time)) {
                    timeList.add(time)
                    var index = 0
                    timeList.sorted().forEachIndexed { i, it ->
                        if (it == time) {
                            index = i
                            return@forEachIndexed
                        }
                    }
                    val min = if (index - 2 < 0) 0 else index - 2
                    val max = if (index + 1 > timeList.size - 2) timeList.size - 2 else index + 1
                    var msg = "未找到此建造时间的舰娘，可以查询下面的相近的建造时间喵"
                    for (i in min .. max) {
                        msg += "\n${timeList[i]}"
                    }
                    return TextData(msg)
                }

                // 是建造时间
                var index = 0
                timeList.sorted().forEachIndexed { i, it ->
                    if (it == time) {
                        index = i
                        return@forEachIndexed
                    }
                }

                return TextData(trList[index].child(1).text()
                    .replace("限 重 ", "[限定重型池]")
                    .replace("限 轻 ", "[限定轻型池]")
                    .replace("限 特 ", "[限定特型池]")
                    .replace("限 ", "[限定]")
                    .replace("常 重 ", "[常驻重型池]")
                    .replace("常 轻 ", "[常驻轻型池]")
                    .replace("常 特 ", "[常驻特型池]")
                    .replace("联 ", "[联动]")
                    .replace(" ", "\n")
                )
            }
            else -> {
                return TextData("格式错误，时间请使用 时:分分:秒秒 的格式喵")
            }
        }
    }

    // 皮肤大图
    private fun parseDressLarge(doc: Document, commandList: List<String>): Data {
        if (!File(CommonConfig.ship_skin_path).exists()) {
            return TextData("还没有下载皮肤文件喵")
        }

        val data = ImagesData()

        println(commandList[1])
        val fileTree:FileTreeWalk = File(CommonConfig.ship_skin_path).walk()
        fileTree.maxDepth(1)//遍历目录层级为1，即无需检查子目录
            .filter { it.isFile } //只挑选出文件,不处理文件夹
            .filter { it.name.startsWith("${commandList[1].uppercase()}_") }
            .forEach {
                println(it.path)
                data.images.add(it.path)
            }

        if (data.images.isEmpty()) {
            data.images.add("${CommonConfig.emoji_path}/meiyinci.jpg")
            if (commandList[1] == "美因茨") {
                data.images.add("${CommonConfig.emoji_path}/joker_is_me.jpeg")
            }
        }
        return data
    }

    // 根据keyword模糊查询
    fun search(commandList: List<String>) : List<String> {
        val keyWord = commandList[1]

        // 查询整个keyword
        var filter = "(.*)$keyWord(.*)"
        var list = NAME_LIST.filter{
            it.matches(Regex(filter))
        }
        if (list.isNotEmpty()) return list

        // 将中英文数字分开查询
        var type = 0
        filter = ""
        keyWord.forEach {
            if (codeType(it) != type) {
                filter += "(.*)"
                type = codeType(it)
            }
            filter += it
        }
        filter += "(.*)"
        list = NAME_LIST.filter{
            it.matches(Regex(filter))
        }
        if (list.isNotEmpty()) return list

        // 全部分解
        filter = "(.*)"
        keyWord.forEach {
            filter += "$it(.*)"
        }
        list = NAME_LIST.filter{
            it.matches(Regex(filter))
        }
        return list
    }

    fun searchResult2Data(list: List<String>, commandList: List<String>) : Data? {
        return when (list.size) {
            0 -> null
            1 -> parse(HttpUtils.get(SEARCH_URL + list[0]), commandList)
            else -> {
                var msg = "还找到了以下相近词条喵"
                val max = if (list.size > 6) 6 else list.size
                for (i in 1 until max) {
                    msg += "\n${list[i]}"
                }
                if (list.size > 6) msg += "\n......"
                val data = parse(HttpUtils.get(SEARCH_URL + list[0]), commandList)
                data?.extra_msg?.add(msg)
                data
            }
        }
    }


    fun codeType(ch: Char): Int {
        return if (ch in '\u4e00'..'\u9fa5') 1 //中文字符
        else if (ch in '\u0030'..'\u0039') 2//数字字符
        else if ((ch in '\u0041'..'\u005A') or (ch in '\u0061'..'\u007A')) 3//英文字符
        else 0
    }
}