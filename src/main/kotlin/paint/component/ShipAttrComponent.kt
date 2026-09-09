package org.iris.wiki.paint.component


import org.iris.wiki.config.CommonConfig
import org.iris.wiki.config.WikiConfig
import org.iris.wiki.data.ShipAttrData
import org.iris.wiki.paint.PaintUtils
import org.iris.wiki.utils.ImageUtil
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.xml.parsers.SAXParserFactory
import kotlin.io.path.Path

class ShipAttrComponent(
    val data: ShipAttrData
) : Component() {

    val skillList : ArrayList<SkillComponent> = arrayListOf()
    var boxWidth = 570
    var boxHeight = 190
    var boxY = 20
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
        if (data.camp == "" || data.camp == "META-???") {
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
        drawShip()

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
                    if (i != 14) {
                        // 不是最后一个
                        boxY += 30 + 5
                    }
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
        if (data.normal_from != "") {
            val textList = data.normal_from.split("、")
            val max = if (4 > textList.size) textList.size else 4
            var text = textList[0]
            for (i in (1 until max)) {
                text += "," + textList[i]
            }
            if (max < textList.size) text += "等"
            from.add(TextComponent(text, 20F).init())
        }
        if (data.active_from != "" && data.file_from == "") {
            from.add(TextComponent(data.active_from.split(" ").last(), 20F).init())
        }
        if (data.file_from != "") {
            from.add(TextComponent(data.file_from.split(" ").last(), 20F).init())
        }
        if (data.other_from != "") {
            data.other_from.replace(" ", "、").split("、").forEach{
                if (it.contains("兑换") || it.contains("科研") ||
                    it.contains("奖励") || it.contains("META") ||
                    it.contains("彩蛋") || it.contains("开发船坞")) {
                    from.add(TextComponent(it, 20F).init())
                }
            }
        }
        var fromHeight = 0
        from.forEach {
            fromHeight += it.getComponentHeight()
        }

        var label = TextComponent("建造时间", 20F).init()
        val labelWidth = 100
        val timeHeight = label.getComponentHeight()

        // 建造时间 出处
        if (fromHeight + timeHeight + boxY > 710) {
            boxX = 50
            boxY = 710 - fromHeight - timeHeight
        }


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
        g2.fillRect(boxX, boxY, boxWidth, fromHeight)
        g2.color = Color.WHITE
        g2.drawRect(boxX, boxY, labelWidth, fromHeight)
        g2.drawRect(boxX + labelWidth, boxY, boxWidth - labelWidth, fromHeight)
        if (from.isNotEmpty()) {
            label = TextComponent("其他途径", 20F).init()
            g2.drawImage(
                label.draw(), boxX + (labelWidth - label.getComponentWidth()) / 2,
                (fromHeight - label.getComponentHeight()) / 2 + boxY, null
            )
            from.forEach {
                g2.drawImage(it.draw(), boxX + (boxWidth + labelWidth - it.getComponentWidth()) / 2, boxY, null)
                boxY += it.getComponentHeight()
            }
        }

        // 武器效率详情
        if (WikiConfig.ship_equip_efficiency_on) {
            // 确定在画布中的位置
            val lineHeight = 30
            val lineLength = arrayListOf(70, 270, 370, 470, 570)
            if (boxX == 50) {
                boxY -= fromHeight + timeHeight + lineHeight * 4
            }
            else if (boxY + lineHeight * 4 > 720) {
                boxX = 50
                boxY = 710 - lineHeight * 4
            }

            // 绘制背景及网格
            g2.color = colorAttr
            g2.fillRect(boxX, boxY, boxWidth, lineHeight * 4)
            g2.color = Color.WHITE
            g2.drawLine(boxX, boxY, boxX, boxY + 4 * lineHeight)
            for (i in 0..4) {
                g2.drawLine(boxX, boxY + lineHeight * i, boxX + boxWidth, boxY + lineHeight * i)
                g2.drawLine(boxX + lineLength[i], boxY, boxX + lineLength[i], boxY + 4 * lineHeight)
            }

            for (equip in data.equip_detail) {
                drawCenter(equip.index, boxX, boxY, lineHeight, lineLength[0])
                drawCenter(equip.name, boxX + lineLength[0], boxY, lineHeight, lineLength[1] - lineLength[0])
                drawCenter(equip.efficiency, boxX + lineLength[1], boxY, lineHeight, lineLength[2] - lineLength[1])
                drawCenter(equip.count, boxX + lineLength[2], boxY, lineHeight, lineLength[3] - lineLength[2])
                drawCenter(equip.prefill_count, boxX + lineLength[3], boxY, lineHeight, lineLength[4] - lineLength[3])
                boxY += lineHeight
            }
        }

    }

    private fun drawShip() {
        var name = data.name.split("（")[0]

        if (File("${CommonConfig.ship_path}/$name.png").exists() &&
            File("${CommonConfig.ship_label_path}/$name.xml").exists()
        ) {
            val documentFactory = SAXParserFactory.newInstance()
            val documentBuilder = documentFactory.newSAXParser()

            var xmin : Int = 0
            var ymin : Int = 0
            var xmax : Int = 0
            var ymax : Int = 0

            val handler = object : DefaultHandler() {
                var label: String = ""

                override fun startElement(uri: String?, localName: String?, qName: String?, atts: Attributes?) {
                    label = qName!!
                }

                override fun characters(ch: CharArray?, start: Int, length: Int) {
                    val value = String(ch!!, start, length)
                    if (value.replace("\n", "").replace("\t", "") != "") {
                        when(label) {
                            "xmin" -> xmin = value.toInt()
                            "ymin" -> ymin = value.toInt()
                            "xmax" -> xmax = value.toInt()
                            "ymax" -> ymax = value.toInt()
                        }
                    }
                }
            }

            documentBuilder.parse("${CommonConfig.ship_label_path}/$name.xml", handler)


            val scale = (720 - 87).toDouble() / (ymax - ymin)
            val xmid = ((xmin + xmax) / 2 * scale).toInt()
            ymin = (ymin * scale).toInt()

            val pic = ImageUtil.getImage("${CommonConfig.ship_path}/$name.png")
            g2.drawImage(pic, 350 - xmid, 87 - ymin,
                (pic.width * scale).toInt(), (pic.height * scale).toInt(), null)
        } else {
            val pic = ImageUtil.getImage(data.pic)
            g2.drawImage(pic, 50, 0, 525, 788, null)
        }
    }

    private fun drawCenter(text: String, x: Int, y: Int, h: Int, w: Int) {
        val textComponent = TextComponent(text, 20f).init()
        g2.drawImage(textComponent.draw(), x + (w - textComponent.getComponentWidth()) / 2,
        y + 2 + (h - textComponent.getComponentHeight()) / 2, null)
    }
}
