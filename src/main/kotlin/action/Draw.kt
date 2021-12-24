package org.iris.wiki.action

import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.BufferImagesData
import org.iris.wiki.data.Data
import org.iris.wiki.paint.component.DrawResultComponent
import org.iris.wiki.utils.DrawUtils
import org.iris.wiki.utils.DrawUtils.ship_contain_map
import org.iris.wiki.utils.ImageUtil

/**
 * 抽卡
 */
class Draw {

    // 普池
    fun draw(type: DrawUtils.DrawType) : Data {
        val rareList = arrayListOf<DrawUtils.Rarity>()
        val nameList = arrayListOf<String>()
        val picList = arrayListOf<String>()
        var feiqiu = true // 是否蓝天白云
        for (i in 0..9) {
            val rarity = DrawUtils.getRarity()
            rareList.add(rarity)
            if (rarity !in listOf(DrawUtils.Rarity.R, DrawUtils.Rarity.N)) {
                feiqiu = false
            }
            val name = ship_contain_map[Pair(type, rarity)]!!.random()
            nameList.add(name)
            picList.add("${CommonConfig.head_path}/${name}.png")
        }


        val component = DrawResultComponent(rareList, nameList, picList)
        component.init()
        val list = arrayListOf(component.draw()!!)
        if (feiqiu) list.add(ImageUtil.getImage("${CommonConfig.emoji_path}/feiqiu.jpg"))
        return BufferImagesData(list).activateAt()
    }

    // 活动池
    fun drawActive(pool: DrawUtils.ActivePool): Data {
        val map = hashMapOf<Int, String>()
        var feiqiu = true
        var p = 0
        pool.ssr.forEach {
            p += it.probability
            map[p] = it.name
        }
        map[70] = "其他SSR"
        p = 70
        pool.sr.forEach {
            p += it.probability
            map[p] = it.name
        }
        map[190] = "其他SR"
        map[490] = "其他N"
        p = 490
        pool.r.forEach {
            p += it.probability
            map[p] = it.name
        }
        map[1000] = "其他R"

        val rareList = arrayListOf<DrawUtils.Rarity>()
        val nameList = arrayListOf<String>()
        val picList = arrayListOf<String>()

        for (i in 0..9) {
            val rand = (0 until 1000).random()
            if (pool.ur.isNotEmpty() && rand >= 1000 - 12) {
                rareList.add(DrawUtils.Rarity.UR)
                nameList.add(pool.ur[0].name)
                picList.add("${CommonConfig.head_path}/${nameList[i]}.png")
                feiqiu = false
            }
            else {
                var k = 1000
                map.keys.forEach {
                    if (it in (rand + 1) until k) {
                        k = it
                    }
                }
                when (rand) {
                    in 0..69 -> {
                        rareList.add(DrawUtils.Rarity.SSR)
                        feiqiu = false
                    }
                    in 70..189 -> {
                        rareList.add(DrawUtils.Rarity.SR)
                        feiqiu = false
                    }
                    in 190..489 -> rareList.add(DrawUtils.Rarity.N)
                    else -> rareList.add(DrawUtils.Rarity.R)
                }
                nameList.add(map[k]!!)
                picList.add("${CommonConfig.head_path}/${nameList[i]}.png")
            }

        }

        val component = DrawResultComponent(rareList, nameList, picList)
        component.init()
        val list = arrayListOf(component.draw()!!)
        if (feiqiu) list.add(ImageUtil.getImage("${CommonConfig.emoji_path}/feiqiu.jpg"))
        return BufferImagesData(list).activateAt()
    }
}