package org.iris.wiki.paint.component

import org.iris.wiki.Checker.draw
import org.iris.wiki.data.ShipEquipData
import org.iris.wiki.utils.ImageUtil
import java.awt.Color
import java.awt.image.BufferedImage

class ShipEquipComponent(
    val data : ShipEquipData
) : Component() {

    private val labelWidth = 400
    private val labelHeight = 50
    private val margin = 10

    override fun init(): Component {
        width = 5 * labelWidth + 6 * margin
        height = 1000

        return super.init()
    }

    override fun draw(): BufferedImage? {
        var boxX = margin
        var boxY = 40
        background(Color.WHITE)
        val title = TextComponent("萌新可以不看文字，直接按表中顺序，以此寻找有的装备，就可以随便用。另外，除设备外灰色背景代表下半段装备比上半部分低一档，每半段装备差距不大",
                                    20F, Color.BLACK).init()
        g2.drawImage(title.draw(), 10, 10, null)

        data.equipLists.forEach { list ->

            if (list.isEmpty()) return@forEach
            boxY = 40
            list.forEach {
                g2.color = it.bgColor
                g2.fillRect(boxX, boxY, labelWidth, labelHeight)
                val text = TextComponent(it.text, 20F, it.fontColor)
                text.paddingTop = 10
                text.init()
                if (boxY == 40) {
                    g2.drawImage(text.draw(), boxX + (labelWidth - text.getComponentWidth()) / 2,
                                boxY, null)
                }
                else {
                    g2.drawImage(text.draw(), boxX, boxY, null)
                    var x = boxX + labelWidth
                    for (pic in it.pics) {
                        val image = ImageUtil.getImage(pic)
                        g2.drawImage(image, x - 40, boxY + 5, 40, 40, null)
                        x -= 43
                    }
                }
                boxY += labelHeight
            }
            boxX += margin + labelWidth
        }

        val specialEquipLength = 100
        val equipIconLength = width - 300
        data.specialEquipList.forEach {
            boxY += 8
            boxX = specialEquipLength
            g2.color = Color.DARK_GRAY
            g2.drawLine(margin, boxY, width - margin, boxY)
            boxY += 2

            val tagTextComponent = TextComponent("特殊配装", 20F, Color.BLACK).init()
            g2.drawImage(tagTextComponent.draw(), margin, boxY + 10, null)
            g2.drawImage(TextComponent("推荐人：${it.recommender}", 20F, Color.BLACK).init().draw(), equipIconLength + 10, boxY + 10, null)

            var equipIconHeight = 40
            it.list.forEach { icon ->
                val iconTextComponent = TextComponent(icon.name, 20F, Color.BLACK).init()
                if (boxX + 46 + iconTextComponent.getComponentWidth() > equipIconLength) {
                    boxX = specialEquipLength
                    boxY += 42
                }
                g2.drawImage(ImageUtil.getImage(icon.url), boxX, boxY, 40, 40, null)
                boxX += 43
                g2.drawImage(iconTextComponent.draw(), boxX, boxY + (40 - iconTextComponent.getComponentHeight()) / 2, null)
                boxX += iconTextComponent.getComponentWidth() + 3
            }
            boxY += 42
            g2.drawImage(TextComponent("推荐理由", 20F, Color.BLACK).init().draw(), margin, boxY + 10, null)

            val textComponent = TextComponent(it.msg, 20F, Color.BLACK, width = equipIconLength + 200).init()
            g2.drawImage(textComponent.draw(), specialEquipLength, boxY, null)
            boxY += textComponent.getComponentHeight()
        }

        return super.draw()!!.getSubimage(0, 0, width, boxY + 10)
    }

}