package org.iris.wiki.paint.component

import org.iris.wiki.data.ShipAttrData
import org.iris.wiki.paint.PaintUtils
import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import kotlin.io.path.Path

class ShipAttrComponent(
    val data: ShipAttrData
) : Component() {

    val skillList : ArrayList<SkillComponent> = arrayListOf()
    var boxWidth = 570
    var boxHeight = 190
    var boxY = 50
    var boxX = 680

    override fun init() {
        width = 1280
        height = 720


        data.skill.forEach {
            val skill = SkillComponent(it, 16F, 570)
            skill.init()
            boxHeight += skill.getComponentHeight()
            skillList.add(skill)
        }


        super.init()
    }



    override fun draw(): BufferedImage? {




        // 修正阵营和稀有度信息
        var rarity = PaintUtils.MAP_SHIP_RARITY[data.level]!!
        if (data.camp == "") {
            data.camp = "余烬"
        }
        if (data.camp == "余烬") {
            rarity += 10
        }
        if (data.canUpgrade) {
            rarity += 1
        }

        background("${PaintUtils.PATH_RARITY_ICON}/bg_${rarity}.png")
        drawCommon()


        var pic : BufferedImage

        // 阵营图标绘制
        var file = Path("${PaintUtils.PATH_CAMP_ICON}/${data.camp}.png").toFile()
        if (file.exists()) {
            pic = ImageIO.read(file)
        }
        else {
            pic = ImageIO.read(Path("${PaintUtils.PATH_CAMP_ICON}/其他联动.png").toFile())
            ImageIO.write(pic, "png", file)
        }
        g2.drawImage(pic, 0, 0, 160, 160, null)


        pic = ImageIO.read(Path("${PaintUtils.PATH_SHIP_FOLDER}/22.png").toFile())
        val scale = pic.height / (height - 50).toFloat()
        val w = (pic.width / scale).toInt()
        g2.drawImage(pic, 330 - w / 2, 100, w, height - 50, null)



        return super.draw()
    }

    private fun drawCommon() {

        val colorIcon = Color(0,0, 0, 127)
        val colorAttr = Color(50,50, 50, 127)
        val colorBg = Color(100, 100, 100, 50)
        g2.color = colorBg
        g2.fillRect(boxX, boxY, boxWidth, boxHeight)
        g2.color = Color.WHITE
        g2.drawRect(boxX, boxY, boxWidth, boxHeight)
        boxY += 10

        val list = listOf(
            data.naijiu,
            data.zhuangjia + "装甲",
            data.zhuangtian,
            data.paoji,
            data.leiji,
            data.jidong,
            data.fangkong,
            data.hangkong,
            data.mingzhong,
            data.fanqian,
            data.yangqi,
            data.danyao,
            data.hangsu,
            data.xingyun,
            data.xiaohao
        )
        val strs = listOf(
            "耐久", "装甲", "装填", "炮击", "雷击",
            "机动", "防空", "航空", "命中", "反潜",
            "氧气", "弹药量","航速","幸运", "消耗"
        )

        var x = boxX + 10
        var count = 0
        var pic : BufferedImage
        for (i in 0..14) {
            if (list[i].isNotEmpty()) {
                g2.color = colorIcon
                g2.fillRect(x, boxY, 30, 30)
                pic = ImageIO.read(Path("${PaintUtils.PATH_SHIP_ICON}/attr_${i+1}.png").toFile())
                g2.drawImage(pic, x, boxY, 30, 30, null)

                x += 30
                g2.color = colorAttr
                g2.fillRect(x, boxY, 147, 30)

                val component = AttributeComponent(strs[i], list[i].split("→").last(), 20F, 147)
                component.setPaddingHorizontal(2)
                component.paddingTop = 2
                component.init()
                g2.drawImage(component.draw(), x, boxY, null)

                x += 147 + 10

                count++
                if (count == 3) {
                    count = 0
                    x = boxX + 10
                    boxY += 30 + 5
                }
            }
        }

        boxY += 40
        x = boxX
        skillList.forEach {
            g2.drawImage(it.draw(), x, boxY, null)
            boxY += it.getComponentHeight()
        }

    }

}