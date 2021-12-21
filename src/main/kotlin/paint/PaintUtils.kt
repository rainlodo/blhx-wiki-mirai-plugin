package org.iris.wiki.paint

import org.iris.wiki.config.CommonConfig
import java.awt.*
import kotlin.io.path.Path

/**
 *
 */
object PaintUtils {

    val font: Font = Font.createFont(Font.TRUETYPE_FONT, Path(CommonConfig.ttf).toFile())


    private const val PATH_BASE = "data/image"
    const val PATH_EQUIP_ICON = "$PATH_BASE/icon/equip/"
    const val PATH_SHIP_ICON = "$PATH_BASE/icon/ship/"
    const val PATH_RARITY_ICON = "$PATH_BASE/icon/rarity"
    const val PATH_SHIP_FOLDER = "D:/document/out/ship"
    const val PATH_CAMP_ICON = "$PATH_BASE/icon/camp"

    val MAP_EQUIP_USE = listOf(
        "驱逐",	"轻巡", 	"重巡", 	"超巡", "战巡",
        "战列",	"轻航",	"航母",	"航战", "潜母",
        "重炮",	"潜艇",	"维修",	"运输"
    )

    val MAP_SHIP_RARITY = mapOf<String, Int>(
        "海上传奇" to 5,
        "超稀有" to 4,
        "精锐" to 3,
        "稀有" to 2,
        "普通" to 1,
        "最高方案" to 24,
        "决战方案" to 25
    )



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