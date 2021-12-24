package org.iris.wiki.paint.component

import org.iris.wiki.utils.DrawUtils
import org.iris.wiki.paint.PaintUtils
import org.iris.wiki.utils.ImageUtil
import java.awt.Color
import java.awt.image.BufferedImage

class DrawResultComponent(
    val rareList : ArrayList<DrawUtils.Rarity>,
    val nameList : ArrayList<String>,
    val picList : ArrayList<String>
) : Component() {

    private val rarity_color_map = mapOf<DrawUtils.Rarity, Color>(
        DrawUtils.Rarity.UR to Color.RED,
        DrawUtils.Rarity.SSR to Color(235, 208, 127),
        DrawUtils.Rarity.SR to Color(131, 7, 255),
        DrawUtils.Rarity.R to Color(27, 183, 235),
        DrawUtils.Rarity.N to Color(219, 220, 223)
    )

    override fun init() : Component {

        width = 840
        height = 380

        return super.init()
    }

    override fun draw(): BufferedImage? {

        background("${PaintUtils.PATH_SHIP_ICON}/draw_bg.png")
        var x = 50
        var y = 50
        val imageWidth = 90
        val imageHeight = 90
        for (i in rareList.indices) {
            val image = ImageUtil.getImage(picList[i])
            val text = TextComponent(nameList[i], 20F, rarity_color_map[rareList[i]]!!)
            text.init()
            text.background(Color(0, 0, 0, 127))
            g2.color = rarity_color_map[rareList[i]]!!
            g2.fillRect(x-3, y-3, imageWidth+6, imageHeight+6)
            g2.drawImage(image, x, y, imageWidth, imageHeight, null)
            g2.drawImage(text.draw(), x + (imageWidth - text.getComponentWidth()) / 2,
                y + imageHeight + 5, null)
            x += 130
            if (x > 800) {
                x = 50 + 130
                y = 150 + 50
            }
        }
        return super.draw()
    }
}