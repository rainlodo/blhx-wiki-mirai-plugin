package org.iris.wiki.paint

import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.EquipAttrData
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.io.path.Path


object PaintUtils {

    val PATH_EQUIP_ICON = "D:\\document\\out\\icon\\equip\\"

    val MAP_EQUIP_USE = listOf(
        "驱逐",	"轻巡", 	"重巡", 	"超巡", "战巡",
        "战列",	"轻航",	"航母",	"航战", "潜母",
        "重炮",	"潜艇",	"维修",	"运输"
    )

    private val renderingHints  = RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    val font = Font.createFont(Font.TRUETYPE_FONT, Path(CommonConfig.ttf).toFile())


    private const val imgWidth = 800
    private const val contentMargin = 10

    fun hex2Color(hex: String): Color {
        return if (hex.startsWith("#")) {
            Color(
                Integer.valueOf(hex.substring(1, 3), 16),
                Integer.valueOf(hex.substring(3, 5), 16),
                Integer.valueOf(hex.substring(5), 16)
            )
        } else {
            Color.BLACK
        }
    }

    fun build(strList: List<String>) : File {
        val list = arrayListOf<BufferedImage>()
        strList.forEach { buildTextMessage(it)?.let { it1 -> list.add(it1) } }
        return buildText(list)
    }

    fun buildText(biList: List<BufferedImage>) : File {

        var height = 20
        biList.forEach { height += it.height }

        val bi = BufferedImage(imgWidth, height, BufferedImage.TYPE_INT_ARGB)
        val g2 = bi.createGraphics()
        g2.setRenderingHints(renderingHints)

        background(g2, height, Color(255, 255, 255))

        var g2Y = 0
        biList.forEach {
            g2.drawImage(it, 0, g2Y, null)
            g2Y += it.height
        }
        g2.dispose()


        val file = Path("data/test.png").toFile()
        ImageIO.write(bi, "png", file)
        return file
    }

    private fun background(bgG2: Graphics2D, height: Int, color: Color) {
        bgG2.color = color
        bgG2.fillRect(0, 0, imgWidth, height)

        val margin = 10
        val rw = imgWidth - margin * 2
        val rh = height - margin * 2
        bgG2.color = Color(255,255,255, 200)
        bgG2.fillRoundRect(margin, margin, rw, rh, 20, 20)
        bgG2.color = Color(238, 238, 238)
        bgG2.drawRoundRect(margin - 1, margin - 1, rw + 1, rh + 1, 20, 20)

    }

    fun buildEquipAttrMessage(
        data: EquipAttrData
    ) : File {
        return File("")
    }

    fun buildTextMessage(
        text: String
    ) : BufferedImage? {
        if (text == "") {
            return null
        }

        val textBi = BufferedImage(800, 2000, BufferedImage.TYPE_INT_ARGB)
        val textG2 = textBi.createGraphics()
        textG2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP)
        textG2.color = Color.BLACK
        textG2.font = font.deriveFont(25f)

        var msgText = text
        var textX = 35
        var textY = 35
        for ((i, c) in msgText.withIndex()) {
            val cs = c.toString()
            if (textX > 740) {
                textX = 35
                textY += 35
            }
            if (cs.matches("[\u4e00-\u9fa5]".toRegex())) {
                textG2.drawString(cs, ++textX, textY)
                textX++
            } else textG2.drawString(cs, textX, textY)
            textX += textG2.getStrWidth(cs)

        }

        textG2.dispose()
        return textBi.getSubimage(0, 0, textBi.width, textY + 15)
    }

    fun Graphics2D.writeText(text: String, x: Int, y: Int, rowL: Int, rowCount: Int) : Int {
        var rowLength = 0
        var textX = x
        var textY = y
        var textRow = 1
        val text = text.replace("\n", " ")

        for (c in text) {
            val l = getStrWidth(c.toString())
            rowLength += l
            if (rowLength >= rowL) {
                if (textRow == rowCount) {
                    drawString("...", textX, textY)
                    break
                } else {
                    drawString(c.toString(), textX, textY)
                }
                rowLength = 0
                textX = x
                textY += font.size + 3
                textRow++
            } else {
                drawString(c.toString(), textX, textY)
                textX += l
            }
        }
        return textY
    }

    fun Graphics2D.getStrWidth(str: String, plus: Int = 0): Int {
        return font.getStringBounds(str, fontRenderContext).width.toInt() + plus
    }

    fun Graphics2D.getStrHeight(str: String): Int {
        return font.getStringBounds(str, fontRenderContext).height.toInt()
    }
}