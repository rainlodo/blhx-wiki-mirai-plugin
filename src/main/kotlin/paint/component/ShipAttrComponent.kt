package org.iris.wiki.paint.component

import org.iris.wiki.data.ShipAttrData
import org.iris.wiki.paint.PaintUtils
import org.iris.wiki.utils.ImageUtil
import java.awt.Color
import java.awt.SystemColor.text
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

    override fun init() : Component{
        width = 1280
        height = 720


        data.skill.forEach {
            val skill = SkillComponent(it, 16F, 570)
            skill.init()
            boxHeight += skill.getComponentHeight()
            skillList.add(skill)
        }


        return super.init()
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



        var pic : BufferedImage



        // 立绘
//        pic = ImageIO.read(Path("${PaintUtils.PATH_SHIP_FOLDER}/22.png").toFile())
        pic = ImageUtil.getImage(data.pic)
        g2.drawImage(pic, 50, 0, 525, 788, null)

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

        // 舰娘名称框绘制
        pic = ImageIO.read(Path("${PaintUtils.PATH_SHIP_ICON}/title.png").toFile())
        g2.drawImage(pic, 150, 5, null)
        pic = ImageIO.read(Path("${PaintUtils.PATH_SHIP_ICON}/${data.type}.png").toFile())
        g2.drawImage(pic, 194 - 30, 30, 60, 60, null)

        // 舰娘名字和代号
        val nameComponent = TextComponent(data.name, 20F)
        nameComponent.init()
        g2.drawImage(nameComponent.draw(), 340 - nameComponent.getComponentWidth() / 2, 40, null)

        val nikenameComponent = TextComponent(data.code, 12F)
        nikenameComponent.init()
        g2.drawImage(nikenameComponent.draw(), 340 - nikenameComponent.getComponentWidth() / 2, 71, null)

        drawBox()
        return super.draw()
    }

    private fun drawBox() {

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


        // 计算出处栏的高度
        val from = arrayListOf<Component>()
        if (data.normal != "") {
            val textList = data.normal.split("、")
            val max = if (4 > textList.size) textList.size else 4
            var text = textList[0]
            for (i in (1 until max)) {
                text += "," + textList[i]
            }
            if (max < textList.size) text += "等"
            from.add(TextComponent(text, 20F).init())
        }
        if (data.active != "") {
            from.add(TextComponent(data.active.split(" ").last(), 20F).init())
        }
        if (data.other != "") {
            data.other.replace(" ", "、").split("、").forEach{
                if (it.contains("兑换") || it.contains("科研") ||
                    it.contains("奖励") || it.contains("META")) {
                    from.add(TextComponent(it, 20F).init())
                }
            }
        }
        var h = 0
        from.forEach {
            h += it.getComponentHeight()
        }

        // 建造时间 出处
        if (h + boxY > 690) {
            boxX = 50
            boxY = 550
        }

        var label = TextComponent("建造时间", 20F).init()
        val labelWidth = 100
        g2.color = colorAttr
        g2.fillRect(boxX, boxY, boxWidth, label.getComponentHeight())
        g2.drawImage(label.draw(), boxX + (labelWidth - label.getComponentWidth()) / 2, boxY, null)
        g2.color = Color.WHITE
        g2.drawRect(boxX, boxY, labelWidth, label.getComponentHeight())
        g2.drawRect(boxX + labelWidth, boxY, boxWidth - labelWidth, label.getComponentHeight())
        val time = TextComponent(data.time, 20F).init()
        g2.drawImage(time.draw(), boxX + (boxWidth + labelWidth - time.getComponentWidth()) / 2, boxY, null)

        boxY += label.getComponentHeight()


        g2.color = colorAttr
        g2.fillRect(boxX, boxY, boxWidth, h)
        g2.color = Color.WHITE
        g2.drawRect(boxX, boxY, labelWidth, h)
        g2.drawRect(boxX + labelWidth, boxY, boxWidth - labelWidth, h)
        if (h != 0) {
            label = TextComponent("其他途径", 20F).init()
            g2.drawImage(
                label.draw(), boxX + (labelWidth - label.getComponentWidth()) / 2,
                (h - label.getComponentHeight()) / 2 + boxY, null
            )
            from.forEach {
                g2.drawImage(it.draw(), boxX + (boxWidth + labelWidth - it.getComponentWidth()) / 2, boxY, null)
                boxY += it.getComponentHeight()
            }
        }

    }

}