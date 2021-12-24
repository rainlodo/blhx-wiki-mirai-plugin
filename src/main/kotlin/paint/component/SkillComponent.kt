package org.iris.wiki.paint.component

import org.iris.wiki.data.SkillData
import org.iris.wiki.utils.ImageUtil
import java.awt.Color
import java.awt.image.BufferedImage

class SkillComponent(
    val data: SkillData,
    val size: Float,
    width: Int,
    val iconWidth: Int = 80,
    val colorSkill: Color = Color.YELLOW,
    val colorDetail: Color = Color.WHITE,
    val colorBg : Color = Color(50,50, 50, 127)
) : Component(width) {

    lateinit var skillText: TextComponent
    lateinit var detailText: TextComponent


    override fun init() : Component {
        setPaddingHorizontal(3)
        setPaddingVertical(3)

        val w = width - iconWidth - paddingLeft - paddingRight
        skillText = TextComponent(data.name, size, colorSkill, w)
        detailText = TextComponent(data.name + " " + data.detail, size, colorDetail, w)
        skillText.setPaddingHorizontal(10)
        detailText.setPaddingHorizontal(10)
        skillText.init()
        detailText.init()

        return super.init()
    }

    override fun draw(): BufferedImage? {

        background(colorBg)
        g2.drawImage(ImageUtil.getImage(data.pic), paddingLeft, (height - iconWidth) / 2, iconWidth, iconWidth, null)
        border(Color.WHITE, 1)

        g2.drawImage(detailText.draw(), paddingLeft + iconWidth, (height - detailText.getComponentHeight()) / 2, null)
        g2.drawImage(skillText.draw(), paddingLeft + iconWidth, (height - detailText.getComponentHeight()) / 2, null)

        return super.draw()
    }

    override fun getComponentHeight(): Int {
        if (height == 0) {
            height = paddingTop + paddingBottom
            height += detailText.getComponentHeight()
        }
        if (height < iconWidth) {
            height = iconWidth
        }
        return height
    }

}