package org.iris.wiki.paint.component


import org.iris.wiki.data.EquipAttrData
import org.iris.wiki.paint.PaintUtils
import java.awt.Color
import java.awt.image.BufferedImage
import java.net.URL
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

    override fun init() {
        width = 576
        height = 5000
        super.init()
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
        var pic = ImageIO.read(Path("${PaintUtils.PATH_EQUIP_ICON}level_${data.level}.png").toFile())
        g2.drawImage(pic, (width-pic.width) / 2, y, null)
        y += pic.height + 5

        if (data.pic.startsWith("http")) {
            pic = ImageIO.read(URL(data.pic))
        }
        else {
            pic = ImageIO.read(Path(data.pic).toFile())
        }
        g2.drawImage(pic, (width-pic.width) / 2, y - 180, null)

        // tno
        pic = ImageIO.read(Path("${PaintUtils.PATH_EQUIP_ICON}T${data.tno}.png").toFile())
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


        g2.drawImage(ImageIO.read(Path("${PaintUtils.PATH_EQUIP_ICON}use.png").toFile()),
                    tab, y, null)
        y += 8

        // use
        var count = 0
        for (i in 0 until PaintUtils.MAP_EQUIP_USE.size) {
            if (data.use.contains(PaintUtils.MAP_EQUIP_USE[i])) {
                g2.drawImage(
                    ImageIO.read(Path("${PaintUtils.PATH_EQUIP_ICON}use_${i + 1}.png").toFile()),
                    tab + 26 + count * 104, y, null
                )
            }

            count++
            if (count == 5) {
                y += 48
                count = 0
            }
        }

        return super.draw()?.getSubimage(0, 0, width, y + 48 + tab)
    }


}