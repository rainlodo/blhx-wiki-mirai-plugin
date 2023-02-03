package org.iris.wiki.utils

import org.iris.wiki.Wiki
import org.iris.wiki.config.EQUIP_LIST
import org.iris.wiki.config.NAME_LIST
import org.iris.wiki.config.OTHER_LIST
import org.iris.wiki.config.SHIP_LIST
import org.jsoup.Jsoup

// 更新各种数据
object UpdateUtils {

    fun updateAll() {
        updateShipNameList()
        updateEquipList()
        updateNormalPool()
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
        val ship_contain_map = hashMapOf<Pair<DrawUtils.DrawType, DrawUtils.Rarity>, List<String>>()
        val rarityList = listOf(DrawUtils.Rarity.SSR, DrawUtils.Rarity.SR, DrawUtils.Rarity.R, DrawUtils.Rarity.N)
        val poolList = listOf(DrawUtils.DrawType.Light, DrawUtils.DrawType.Heavy, DrawUtils.DrawType.Special)
        doc.select("div.Root").forEachIndexed { poolIndex, pool ->
            pool.select("td.BuildingList").forEachIndexed { rarityIndex, rarity ->
                ship_contain_map[Pair(poolList[poolIndex], rarityList[rarityIndex])] = ArrayList<String>().apply {
                    rarity.select("span.nowrap").forEach {
                        add(it.attr("title"))
                    }
                }
                Wiki.logger.info("update pool[${poolList[poolIndex]}, ${rarityList[rarityIndex]}]: ${ship_contain_map[Pair(poolList[poolIndex], rarityList[rarityIndex])]}")
            }
        }
        DrawUtils.ship_contain_map = ship_contain_map
    }
}