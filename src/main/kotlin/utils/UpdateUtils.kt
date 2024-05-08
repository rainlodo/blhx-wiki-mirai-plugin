package org.iris.wiki.utils

import org.iris.wiki.Wiki
import org.iris.wiki.config.*
import org.jsoup.Jsoup
import java.io.File
import javax.imageio.ImageIO

// 更新各种数据
object UpdateUtils {
    fun updateAll() {
        updateShipNameList()
        updateEquipList()
        updateNormalPool()
        updateShipTechPoints()
    }

    // 更新舰娘名称列表
    fun updateShipNameList() : Int {
        val data = HttpUtils.get("https://wiki.biligame.com/blhx/%E8%88%B0%E5%A8%98%E5%9B%BE%E9%89%B4")

        val doc = Jsoup.parse(data)

        val nameList = arrayListOf<String>()

        doc.select("div[id=\"CardSelectTr\"]")[0].select("span[class=\"jntj-4\"]").select("a").forEach {
           nameList.add(it.attr("title").replace(" ", "_").lowercase())
        }

        Wiki.logger.info("update ship name list, total count:${nameList.size}")
        if (nameList.isNotEmpty()) {
            SHIP_LIST = nameList
            NAME_LIST = SHIP_LIST + EQUIP_LIST + OTHER_LIST
        }
        return nameList.size
    }

    // 更新装备名称列表
    fun updateEquipList() : Int {
        val data = HttpUtils.get("https://wiki.biligame.com/blhx/%E8%A3%85%E5%A4%87")

        val doc = Jsoup.parse(data)
        val equipList = arrayListOf<String>()
        doc.select("div[class='panel-body tab-content']")[0].select("a").forEach {
            equipList.add(it.attr("title").replace(" ", "_").lowercase())
        }
        Wiki.logger.info("update equip name list, total count:${equipList.size}")
        if (equipList.isNotEmpty()) {
            EQUIP_LIST = equipList
            NAME_LIST = SHIP_LIST + EQUIP_LIST + OTHER_LIST
        }
        return equipList.size
    }

    // 更新常驻池
    fun updateNormalPool() {
        val data = HttpUtils.get("https://wiki.biligame.com/blhx/%E5%BB%BA%E9%80%A0%E6%A8%A1%E6%8B%9F%E5%99%A8")

        val doc = Jsoup.parse(data)
        val shipContainMap = hashMapOf<Pair<DrawUtils.DrawType, DrawUtils.Rarity>, List<String>>()
        val rarityList = listOf(DrawUtils.Rarity.UR, DrawUtils.Rarity.SSR, DrawUtils.Rarity.SR, DrawUtils.Rarity.R, DrawUtils.Rarity.N)
        val poolList = listOf(DrawUtils.DrawType.Light, DrawUtils.DrawType.Heavy, DrawUtils.DrawType.Special)
        doc.select("div.Root").forEachIndexed { poolIndex, pool ->
            pool.select("td.BuildingList").forEachIndexed { rarityIndex, rarity ->
                shipContainMap[Pair(poolList[poolIndex], rarityList[rarityIndex])] = ArrayList<String>().apply {
                    rarity.select("span.nowrap").forEach {
                        add(it.attr("title"))
                    }
                }
                Wiki.logger.info("update pool[${poolList[poolIndex]}, ${rarityList[rarityIndex]}]: ${shipContainMap[Pair(poolList[poolIndex], rarityList[rarityIndex])]}")
            }
        }
        DrawUtils.ship_contain_map = shipContainMap
    }

    /**
     * 更新头像
     */
    fun updateShipIcon() {
        val data = HttpUtils.get("https://wiki.biligame.com/blhx/%E8%88%B0%E5%A8%98%E5%9B%BE%E9%89%B4")

        val doc = Jsoup.parse(data)

        doc.select("div[id=\"CardSelectTr\"]")[0].select("img").forEach {
            val url = it.attr("src").replace("/thumb", "").split("/60px")[0]
            val name = it.attr("alt").replace("头像.jpg", "")
            if (!name.startsWith("舰娘头像")) {
                val file = File("${CommonConfig.head_path}/${name}.png")
                if (!file.exists()) {
                    val image = ImageUtil.getImage(url)
                    ImageIO.write(image, "png", file)
                    Wiki.logger.info("已更新头像：$name")
                }
            }
        }
    }

    // 更新科技点数据
    fun updateShipTechPoints(){
        val data = HttpUtils.get("https://wiki.biligame.com/blhx/%E8%88%B0%E9%98%9F%E7%A7%91%E6%8A%80")
        val doc = Jsoup.parse(data)
        val filterTableRows = doc.select("table")[13].select("tr")
        val shipRows = doc.select("#CardSelectTr > tbody > tr")
        shipClassList = filterTableRows[0].select("td > li").map { it.text() }
        techClassList = filterTableRows[8].select("td > li").map { it.text() }

        shipClassList.forEachIndexed{ index, shipClass ->
            if (index > 1){
                val t = mutableMapOf<String?, MutableList<MutableList<String>>>()
                for (shipTechClass in techClassList){
                    val t1 = mutableListOf<MutableList<String>>()
                    t[shipTechClass] = t1
                }
                shipTechPointsMap[shipClass] = t
            }
        }

        shipRows.forEachIndexed{ index, row ->
            if (index > 1) {
                // 获取各个属性值
                val shipClass = row.attr("data-param1").split(",")[0]
                val rarity = row.attr("data-param2")
                val obtainTechClass = row.attr("data-param9")
                val up120TechClass = row.attr("data-param10")

                // 获取每个 td 元素
                val tds = row.select("td")
                val shipName = tds[1].select("a > span")[0].text()
                val nationality = tds[2].text()
                val obtainTech = tds[8].text()
                val up120Tech = tds[9].text()

                val shipAttrList = listOf(shipName, nationality, rarity, obtainTech, up120Tech)
                shipTechPointsMap[shipClass]?.get(obtainTechClass)?.add(shipAttrList.toMutableList())
                shipTechPointsMap[shipClass]?.get(up120TechClass)?.add(shipAttrList.toMutableList())
            }
        }
    }

}