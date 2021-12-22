package org.iris.wiki.data

import org.iris.wiki.config.CommonConfig
import org.iris.wiki.paint.component.DrawResultComponent
import org.iris.wiki.utils.DrawUtils
import org.iris.wiki.utils.DrawUtils.ship_contain_map
import org.iris.wiki.utils.DrawUtils.ship_icon_map
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.imageio.ImageIO

class DrawData {


    fun draw(type: DrawUtils.DrawType) : Data {
        val rareList = arrayListOf<DrawUtils.Rarity>()
        val nameList = arrayListOf<String>()
        val picList = arrayListOf<String>()
        var feiqiu = true
        for (i in 0..9) {
            val rarity = DrawUtils.getRarity()
            rareList.add(rarity)
            if (rarity !in listOf(DrawUtils.Rarity.R, DrawUtils.Rarity.N)) {
                feiqiu = false
            }
            val name = ship_contain_map[Pair(type, rarity)]!!.random()
            nameList.add(name)
            picList.add(ship_icon_map[name]!!)
        }

        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss")
        var path = "${CommonConfig.ship_output_path}/draw/${LocalDateTime.now().format(format)}.png"
        val component = DrawResultComponent(rareList, nameList, picList)
        component.init()
        ImageIO.write(component.draw(), "png", File(path))
        val list = arrayListOf(path)
        if (feiqiu) list.add("${CommonConfig.emoji_path}/feiqiu.jpg")
        return ImagesData(list).activateAt()
    }
}