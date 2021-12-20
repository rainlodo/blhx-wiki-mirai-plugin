package org.iris.wiki.paint.component

import org.iris.wiki.paint.PaintUtils
import org.iris.wiki.paint.PaintUtils.getStrHeight
import org.iris.wiki.paint.PaintUtils.getStrWidth

import java.awt.Color
import java.awt.RenderingHints
import java.awt.image.BufferedImage

/**
 * 文本组件
 *
 * @param text 文本内容
 * @param size 字体大小
 * @param color 文本颜色
 * @param width 文本栏宽度
 * @param padding 换行的间距
 */
class TextComponent(
    var text: String,
    var size: Float,
    var color: Color = Color(255, 255, 255),
    width:Int = 0,
    var padding: Int = 0
) : Component(width) {

    private val g = BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB).createGraphics()
    private var lineHeight = 0
    private var lineCount = 0
    var font = PaintUtils.font

    override fun init() {
        g.font = this.font.deriveFont(size)
        super.init()
    }


    override fun draw(): BufferedImage? {

        if (text == "") {
            return null
        }
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP)
        g2.font = this.font.deriveFont(size)
        g2.color = color
        var x = paddingLeft
        var y = paddingTop + (lineHeight / 1.4).toInt()
        val dis = width - paddingLeft - paddingRight
        text.forEach {
            if (x > dis) {
                x = paddingLeft
                y += lineHeight + padding
            }
            if (it == '\n') {
                x = paddingLeft
                y += lineHeight + padding
            }
            else {
                g2.drawString(it.toString(), x, y)
                x += g.getStrWidth(it.toString())
            }
        }

        return super.draw()
    }

    override fun getComponentWidth(): Int {
        if (width == 0) {
            var maxWidth = 0
            var temp = 0
            text.split("\n").forEach {
                temp = g.getStrWidth(it)
                if (maxWidth < temp) maxWidth = temp
                lineCount++
            }

            width = maxWidth + paddingLeft + paddingRight
        }
        else if (lineCount == 0) {
            val dis = width - paddingLeft - paddingRight
            text.split("\n").forEach {
                val w = g.getStrWidth(it)
                lineCount += w / dis + if (w % dis == 0) 0 else 1
            }
        }
        return width
    }

    override fun getComponentHeight(): Int {
        if (height == 0) {

            lineHeight = g.getStrHeight(text)
            height = lineCount * lineHeight + (lineCount - 1) * padding + paddingTop + paddingBottom
        }
        return height
    }

}