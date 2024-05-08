package org.iris.wiki

import org.iris.wiki.config.*
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import java.io.ByteArrayOutputStream


suspend fun techPointTable(commandList: Array<String>, sender: Member){
    if (commandList[1] == "筛选" && commandList[2] in shipClassList && commandList[3] in techClassList){
        val imageBytes = drawTechPointTable(commandList[2], commandList[3], commandList[4].toInt())?.toExternalResource()
        val builder = MessageChainBuilder()
        if (imageBytes != null){
            val img = sender.uploadImage(imageBytes)
            builder.add(At(sender))
            builder.add(img)
            val msg = builder.build()
            sender.group.sendMessage(msg)
        }
        else{
            sender.group.sendMessage("out of Index")
        }
    }
}

private fun drawTechPointTable(shipClass: String, techClass: String, pageNumber: Int): ByteArray? {
    val width = 1200
    val height = 600

    if ((pageNumber - 1) * 10 > shipTechPointsMap[shipClass]?.get(techClass)?.size!!) return null

    var tableData: MutableList<MutableList<String>>? = null
    tableData = try {
        shipTechPointsMap[shipClass]?.get(techClass)?.subList(
            (pageNumber - 1) * 10, pageNumber * 10)
    } catch (e: Exception){
        shipTechPointsMap[shipClass]?.get(techClass)?.subList(
            (pageNumber - 1) * 10, shipTechPointsMap[shipClass]?.get(techClass)?.size!!)
    }

//    println(tableData)
    val cellWidth = width / (tableData?.get(0)?.size!!)
    val cellHeight = height / tableData.size

    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val g2d = image.createGraphics()

    g2d.color = Color.WHITE
    g2d.fillRect(0, 0, width, height)
    g2d.color = Color.BLACK
    g2d.font = Font("宋体", Font.BOLD, 20)

    val countryColors = mapOf(
        "海上传奇" to Color.decode("#ee494c"),
        "决战方案" to Color.decode("#c06"),
        "超稀有" to Color.decode("#c06"),
        "精锐" to Color.decode("#8000ff"),
        "稀有" to Color.BLUE,
        "普通" to Color.BLACK,
    )

    // 绘制表格边框
    g2d.stroke = BasicStroke(2F)
    for (i in 0 until tableData.size + 1) {
        g2d.drawLine(0, i * cellHeight, width, i * cellHeight)
    }
    for (j in 0 until tableData[0].size) {
        g2d.drawLine(j * cellWidth, 0, j * cellWidth, height)
    }

    for (i in tableData.indices) {
        for (j in tableData[i].indices) {
            val text = tableData[i][j]
            val fm: FontMetrics = g2d.fontMetrics
            val textWidth: Int = fm.stringWidth(text)
            val textHeight: Int = fm.height

            val x = j * cellWidth + (cellWidth - textWidth) / 2
            val y = (i + 1) * cellHeight - (cellHeight - textHeight) / 2

            if (j == 0){
                val textColor = countryColors[tableData[i][2]] ?: Color.BLACK
                g2d.color = textColor
            }

            g2d.drawString(text, x, y)
            g2d.color = Color.BLACK
        }
    }

    g2d.dispose()

    val outputStream = ByteArrayOutputStream()
    ImageIO.write(image, "png", outputStream)
    val bytes: ByteArray = outputStream.toByteArray()
    return bytes
}