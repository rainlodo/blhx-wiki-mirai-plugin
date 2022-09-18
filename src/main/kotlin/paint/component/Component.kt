package org.iris.wiki.paint.component

import java.awt.Color
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO
import kotlin.io.path.Path

/**
 * 基本空组件
 *
 * @param width 组件宽度，为0时自适应宽度
 * @param height 组件高度，为0时自适应高度
 * @param paddingTop 顶部间距
 * @param paddingBottom 底部间距
 * @param paddingLeft 左部间距
 * @param paddingRight 右部间距
 */
open class Component(
    var width: Int = 0,
    var height: Int = 0,
    var paddingTop: Int = 0,
    var paddingBottom: Int = 0,
    var paddingLeft: Int = 0,
    var paddingRight: Int = 0
) {

    lateinit var renderingHints : RenderingHints
    lateinit var bi : BufferedImage
    lateinit var g2 : Graphics2D

    open fun init() : Component {
        renderingHints  = RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        bi = BufferedImage(this.getComponentWidth(), this.getComponentHeight(), BufferedImage.TYPE_INT_ARGB)
        g2 = bi.createGraphics()
        renderingHints[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
        renderingHints[RenderingHints.KEY_ANTIALIASING ] = RenderingHints.VALUE_ANTIALIAS_ON
        g2.setRenderingHints(renderingHints)
        return this
    }

    /**
     * 绘图
     */
    open fun draw() : BufferedImage? {

        g2.dispose()

        // 添加随机噪点，避免tx的md5检测  （wiki 夕张）
        (0..5).forEach { _ ->
            val x = (0 until bi.width).random()
            val y = (0 until bi.height).random()
            bi.setRGB(x, y, 0xffffff)
        }
        return bi
    }

    /**
     * 设置水平边间距
     */
    fun setPaddingHorizontal(size: Int) {
        paddingLeft = size
        paddingRight = size
    }

    /**
     * 设置垂直边间距
     */
    fun setPaddingVertical(size: Int) {
        paddingTop = size
        paddingBottom = size
    }

    /**
     * 获取组件宽度
     */
    open fun getComponentWidth() : Int {
        return if (width == 0) paddingLeft + paddingRight else width
    }

    /**
     * 获取组件高度
     */
    open fun getComponentHeight() : Int {
        return if (height == 0) paddingTop + paddingBottom else height
    }

    /**
     * 设置纯色背景
     */
    open fun background(color: Color, alpha: Int =255) {
        g2.color = color
        g2.fillRect(0, 0, getComponentWidth(), getComponentHeight())
    }

    /**
     * 设置图片背景
     */
    open fun background(image: BufferedImage) {
        g2.drawImage(image, 0, 0, getComponentWidth(), getComponentHeight(), null)
    }

    /**
     * 设置图片背景
     */
    open fun background(path: String) {
        val image : BufferedImage
        if (path.startsWith("http")) {
            image = ImageIO.read(URL(path))
        }
        else {
            image = ImageIO.read(Path(path).toFile())
        }
        background(image)
    }

    /**
     * 设置边框
     */
    open fun border(color: Color, size: Int) {
        g2.color = color
        g2.fillRect(0, 0, size, getComponentHeight())
        g2.fillRect(getComponentWidth() - size, 0, size, getComponentHeight())
        g2.fillRect(0, 0, getComponentWidth(), size)
        g2.fillRect(0, getComponentHeight() - size, getComponentWidth(), size)
    }
}