package org.iris.wiki.paint.component

import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.StageData
import org.iris.wiki.paint.PaintUtils
import org.iris.wiki.utils.ImageUtil
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.max

class StageComponent(
    val data: StageData
) : Component() {

    val startLabel = 20
    val startValue = 170
    val endValue = 710
    val startY = 20
    var y = 20
    val colorIcon = Color(0,0, 0, 127)
    val colorAttr = Color(50,50, 50, 127)
    val colorBg = Color(100, 100, 100, 50)
    val margin = 5

    init {
        width = 1280
        height = 2000
    }

    override fun init(): Component {
        return super.init()
    }

    override fun draw(): BufferedImage? {



        drawLine(endValue)
        drawCenter(data.name, startLabel, y, 50, endValue-startLabel, 40f)
        y += 50
        drawLine(endValue)

        drawLabelValue("旗舰刷新", data.timesMin)
        drawLabelValue("敌舰等级", data.level)
        drawLabelValue("敌舰经验", data.exp)


        var bossH = margin
        data.boss.split("|||").forEach {
            if (it.isNotEmpty()) {
                val valueImage = TextComponent(it, 24F, width = endValue-startValue).init().draw()
                g2.color = colorBg
                g2.fillRect(startLabel, y+bossH-margin, endValue-startLabel, valueImage!!.height+margin*2)
                g2.drawImage(valueImage, startValue+2, y+bossH, null)
                bossH += valueImage!!.height + margin
            }
        }
        drawCenter("BOSS", startLabel, y, bossH, startValue-startLabel)
        y += bossH
        drawLine(endValue)

        drawLabelValue("BOSS等级", data.bossLevel)
        drawLabelValue("BOSS经验", data.bossExp)
        drawLabelValue("油耗上限", data.costLimit)

        y+=2
        g2.drawImage(TextComponent("*彩蛋掉落暂时不显示", 16f).init().draw(),
                    startLabel, y, null)
        y += 20


        val stageImage = ImageUtil.getImage(data.pic)
        val mapHeight = (stageImage.height.toFloat() / stageImage.width * (width-endValue-40)).toInt()
        g2.drawImage(stageImage, endValue+20, startY, width-endValue-40,
            mapHeight, null)
        y = max(y, mapHeight+startY)

        y += 20

        // 掉落
        val endDrop = 1260
        val startDrop = 120
        drawLine(endDrop)
        data.dropTable.dropLineList.forEach {
            var h = 40 + margin * 2
            var x = startDrop
            y += margin
            drawRect(endDrop, h, colorBg)
            it.dropItemList.forEach {
                if (it.name.isNullOrEmpty()) {
                    return@forEach
                }
                val textImage = TextComponent(it.name, 20F, PaintUtils.hex2Color(it.color)).init().draw()
                val image = ImageUtil.getImage(it.pic)
                if (x + 44 + textImage!!.width > endDrop) {
                    y += 40 + margin
                    drawRect(endDrop, h, colorBg)
                    h += 40 + margin
                    x = startDrop
                }
                g2.drawImage(image, x, y, 40, 40, null)
                x += 42
                g2.drawImage(textImage, x, y + 10, null)
                x += textImage.width + 2
            }
            y += 40 +  margin
            drawCenter(it.label, startLabel, y-h, w = startDrop-startLabel, h = h)
            drawLine(endDrop)
        }
        val image = super.draw()?.getSubimage(0,0, width, y+20)

        val bg = BufferedImage(image!!.width, image.height, BufferedImage.TYPE_INT_RGB)
        bg.graphics.color = Color.DARK_GRAY
        bg.graphics.fillRect(0, 0, bg.width, bg.height)
        val file = File("${CommonConfig.root}/image/icon/others/stage_bg.png")
        if (file.exists()) {
            bg.graphics.drawImage(ImageIO.read(file), 0, 0, bg.width, bg.height, null)
        }
        bg.graphics.drawImage(image, 0, 0, null)
        return bg
    }

    private fun drawLabelValue(label: String, value: String) {
        val valueImage = TextComponent(value, 24F, width = endValue-startValue).init().draw()
        drawRect(endValue, valueImage!!.height+margin*2, colorBg)
        g2.drawImage(valueImage, startValue+2, y+margin, null)
        drawCenter(label, startLabel, y, valueImage!!.height+margin*2, startValue-startLabel)
        y += valueImage!!.height + margin*2
        drawLine(endValue)
    }

    private fun drawCenter(text: String, x: Int, y: Int, h: Int, w: Int, size:Float=24f) {
        val textComponent = TextComponent(text, size).init()
        g2.drawImage(textComponent.draw(), x + (w - textComponent.getComponentWidth()) / 2,
            y + 2 + (h - textComponent.getComponentHeight()) / 2, null)
    }

    private fun drawLine(end:Int, color: Color=Color.WHITE) {
        g2.color = color
        g2.drawLine(startLabel, y, end,  y)
        y += 1
    }

    private fun drawRect(end:Int, h: Int ,color: Color) {
        g2.color = color
        g2.fillRect(startLabel, y, end-startLabel, h)
    }
}
