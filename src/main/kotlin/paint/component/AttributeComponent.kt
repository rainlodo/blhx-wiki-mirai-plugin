package org.iris.wiki.paint.component

import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.lang.Integer.max

/**
 * 属性组件
 *
 * 在组件中标签靠左，值靠右
 */
open class AttributeComponent(
    var label: String,
    var value : String,
    var size : Float,
    width : Int,
    var labelColor : Color = Color.WHITE,
    var valueColor : Color = Color.YELLOW
): Component(width) {

    var labelComponent: TextComponent = TextComponent(label, size, labelColor)
    var valueComponent: TextComponent = TextComponent(value, size, valueColor)

    override fun init() : Component {

        labelComponent.init()
        if (value != "")
            valueComponent.init()
        return super.init()
    }

    open override fun draw(): BufferedImage? {

        if (label=="") { return null }

        g2.drawImage(labelComponent.draw(), paddingLeft, paddingTop,null)
        if (value != "") {
            g2.drawImage(valueComponent.draw(), width - paddingRight - valueComponent.getComponentWidth(), paddingTop,null)
        }
        return super.draw()
    }

    override fun getComponentHeight(): Int {

        if (value != "") {
            height = max(labelComponent.getComponentHeight(), valueComponent.getComponentHeight()) +
                    paddingTop + paddingBottom
        }
        else {
            height = labelComponent.getComponentHeight() + paddingTop + paddingBottom
        }
        return height
    }
}