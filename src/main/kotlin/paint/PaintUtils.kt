package org.iris.wiki.paint

import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.EquipAttrData
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.io.path.Path


object PaintUtils {

    val PATH_EQUIP_ICON = "E:\\Data\\out\\icon\\equip\\"

    val MAP_EQUIP_USE = listOf(
        "驱逐",	"轻巡", 	"重巡", 	"超巡", "战巡",
        "战列",	"轻航",	"航母",	"航战", "潜母",
        "重炮",	"潜艇",	"维修",	"运输"
    )

    val font = Font.createFont(Font.TRUETYPE_FONT, Path(CommonConfig.ttf).toFile())


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

    fun Graphics2D.getStrWidth(str: String, plus: Int = 0): Int {
        return font.getStringBounds(str, fontRenderContext).width.toInt() + plus
    }

    fun Graphics2D.getStrHeight(str: String): Int {
        return font.getStringBounds(str, fontRenderContext).height.toInt()
    }
}