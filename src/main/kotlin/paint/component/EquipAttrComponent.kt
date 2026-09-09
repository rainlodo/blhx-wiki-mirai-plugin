package org.iris.wiki.paint.component


import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.EquipAttrData
import org.iris.wiki.paint.PaintUtils
import org.iris.wiki.utils.EquipIconUtils
import org.iris.wiki.utils.ImageUtil
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.awt.image.RescaleOp
import java.io.File
import javax.imageio.ImageIO
import kotlin.io.path.Path


class EquipAttrComponent(
    val data: EquipAttrData
) : Component() {

    lateinit var titleComponent : TextComponent
    lateinit var typeComponent : TextComponent
    lateinit var tagComponent : TextComponent
    var componentList : ArrayList<Component> = arrayListOf()
//    lateinit var useComponent : TextComponent

    val backgroundColor = Color(100, 100, 100,200)
    val attrBgColor = Color(0, 0, 0,127)
    private val useBoxWidth = 52
    private val useBoxHeight = 40

    override fun init() : Component {
        width = 576
        height = 5000
        return super.init()
    }

    override fun draw(): BufferedImage? {

        titleComponent = TextComponent(data.name, 30F)
        tagComponent = TextComponent(data.tag, 20F)
        typeComponent = TextComponent(data.type, 20F)

        background(backgroundColor)

        var x = paddingLeft
        var y = paddingTop
        val tab = 15

        titleComponent.setPaddingVertical(3)
        titleComponent.init()

        // name
        g2.color = attrBgColor
        g2.fillRect(0,0, width, titleComponent.getComponentHeight())
        g2.drawImage(titleComponent.draw(), (width-titleComponent.getComponentWidth()) / 2, paddingTop, null)
        y += titleComponent.getComponentHeight()

        // pic
        var pic = ImageIO.read(Path("${PaintUtils.PATH_EQUIP_ICON}/level_${data.level}.png").toFile())
        g2.drawImage(pic, (width-pic.width) / 2, y, null)
        y += pic.height + 5

        data.pic = EquipIconUtils.resolveEquipIcon(data.name, data.tno, data.pic)
        pic = ImageUtil.getImage(data.pic)
        g2.drawImage(pic, (width-128) / 2, y - 180, 128, 128,null)

        // tno
        pic = ImageIO.read(Path("${PaintUtils.PATH_EQUIP_ICON}/T${data.tno}.png").toFile())
        g2.drawImage(pic, 512, y - 201, 32, 45, null)

        // type
        if (data.type in listOf("设备", "货物", "唯一设备"))
            g2.color = Color.BLUE
        else
            g2.color = Color.RED
        g2.fillRect(20, y-80, 100, 30)
        typeComponent.setPaddingVertical(3)
        typeComponent.init()
        g2.drawImage(typeComponent.draw(), 20 + (100 - typeComponent.getComponentWidth()) / 2, y - 80, null)


        // tag
        g2.color = Color(100, 100, 100, 200)
        g2.fillRect(20, y-40, 100, 30)
        tagComponent.init()
        g2.drawImage(tagComponent.draw(), 20 + (100 - tagComponent.getComponentWidth()) / 2, y - 38, null)


        // attr
        val attrList = data.attr.split('\n')

        for (it in attrList) {

            var strs = it.split(":")
            if (strs.size == 1) {
               strs = listOf(strs[0], " ")
            }

            if (it.startsWith("\t描述")) {
                val skill = TextComponent(strs[1], 24F, Color.GREEN, width - 2 * tab)
                skill.setPaddingHorizontal(20)
                skill.setPaddingVertical(3)
                skill.init()

                skill.background(attrBgColor)
                g2.drawImage(skill.draw(), tab, y, null)
                y += skill.getComponentHeight()
            }
            else {
                var count = 1
                var str = strs[0]
                while (str.startsWith("\t")) {
                    str = str.substring(1, str.length)
                    count++
                }
                val label = AttributeComponent(str, strs[1], 24F, width - (count + 1) * tab)

                label.setPaddingHorizontal(15)
                label.setPaddingVertical(3)
                label.init()

                label.background(attrBgColor)
                g2.drawImage(label.draw(), tab * count, y, null)
                y += label.getComponentHeight()
            }

            y += 4
        }


//        g2.drawImage(ImageIO.read(Path("${PaintUtils.PATH_EQUIP_ICON}/use.png").toFile()),
//                    tab, y, null)
        g2.color = attrBgColor
        g2.fillRect(tab, y, width - 2 * tab, 152)
        y += 8

        // use
        var count = 0
        for (i in 0 until PaintUtils.MAP_EQUIP_USE.size) {
            val iconX = tab + 26 + count * 104
            val iconY = y
            val useIcon = readEquipUiImage("use_${i + 1}.png")
            if (data.use.contains(PaintUtils.MAP_EQUIP_USE[i])) {
                if (useIcon != null) {
                    g2.drawImage(useIcon, iconX, iconY, null)
                } else {
                    drawUseFallback(iconX, iconY, PaintUtils.MAP_EQUIP_USE[i], false)
                }
                if (data.use[PaintUtils.MAP_EQUIP_USE[i]] == 1) {
                    drawSlotTag(iconX - 6, y - 6, "主", Color(214, 65, 65))
                } else if (data.use[PaintUtils.MAP_EQUIP_USE[i]] == 2) {
                    drawSlotTag(iconX - 6, y - 6, "副", Color(68, 114, 196))
                }
            } else {
                if (useIcon != null) {
                    g2.drawImage(
                        useIcon,
                        RescaleOp(FloatArray(4).apply {
                            this[0] = 1f
                            this[1] = 1f
                            this[2] = 1f
                            this[3] = 0.5f
                        }, FloatArray(4), null),
                        iconX, iconY
                    )
                } else {
                    drawUseFallback(iconX, iconY, PaintUtils.MAP_EQUIP_USE[i], true)
                }
            }

            count++
            if (count == 5) {
                y += 48
                count = 0
            }
        }

        return super.draw()?.getSubimage(0, 0, width, y + tab)
    }

    private fun readEquipUiImage(name: String): BufferedImage? {
        val file = Path("${PaintUtils.PATH_EQUIP_ICON}/$name").toFile()
        if (!file.exists()) {
            return null
        }
        return runCatching { ImageIO.read(file) }.getOrNull()
    }

    private fun drawSlotTag(x: Int, y: Int, text: String, color: Color) {
        val image = readEquipUiImage(if (text == "主") "main.png" else "sub.png")
        if (image != null) {
            g2.drawImage(image, x, y, 18, 14, null)
            return
        }

        val oldColor = g2.color
        val oldFont = g2.font
        g2.color = color
        g2.fillRoundRect(x, y, 18, 14, 4, 4)
        g2.color = Color.WHITE
        g2.font = PaintUtils.font.deriveFont(Font.BOLD, 10f)
        g2.drawString(text, x + 4, y + 11)
        g2.color = oldColor
        g2.font = oldFont
    }

    private fun drawUseFallback(x: Int, y: Int, text: String, disabled: Boolean) {
        val oldColor = g2.color
        val oldFont = g2.font
        val bg = if (disabled) Color(220, 220, 220, 120) else Color(245, 245, 245)
        val border = if (disabled) Color(180, 180, 180) else Color(120, 120, 120)
        val fontColor = if (disabled) Color(140, 140, 140) else Color.BLACK

        g2.color = bg
        g2.fillRoundRect(x, y, useBoxWidth, useBoxHeight, 8, 8)
        g2.color = border
        g2.drawRoundRect(x, y, useBoxWidth, useBoxHeight, 8, 8)
        g2.color = fontColor
        g2.font = PaintUtils.font.deriveFont(14f)

        val lines = when {
            text.length <= 2 -> listOf(text)
            text.length <= 4 -> listOf(text.substring(0, 2), text.substring(2))
            else -> listOf(text.substring(0, 2), text.substring(2, 4))
        }

        lines.forEachIndexed { index, line ->
            val bounds = g2.fontMetrics.getStringBounds(line, g2)
            val tx = x + (useBoxWidth - bounds.width.toInt()) / 2
            val ty = y + 15 + index * 14
            g2.drawString(line, tx, ty)
        }

        g2.color = oldColor
        g2.font = oldFont
    }


}
