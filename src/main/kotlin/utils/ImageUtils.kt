package org.iris.wiki.utils

import net.mamoe.mirai.utils.ExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.iris.wiki.config.WikiConfig

import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Image
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.*
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.imageio.ImageIO
import javax.net.ssl.*
import kotlin.io.path.Path


class ImageUtil {
    companion object {
        private val client = OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS).readTimeout(60000,
            TimeUnit.MILLISECONDS)
        private val headers = Headers.Builder()
            .add(
                "user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36 Edg/84.0.522.59"
            )
            .add("Referer", "https://www.pixiv.net")
        private var isChange:Boolean = false


        /**
         * 将图片链接读取到内存转换成ByteArrayOutputStream，方便操作
         */
        fun getImageAsExResource(imageUri: String): ExternalResource {

            return if (imageUri.startsWith("http")) {
                imageToBytes(randomNoise(ImageIO.read(URL(imageUri))), "png").toByteArray().toExternalResource()
            } else {
                imageToBytes(randomNoise(ImageIO.read(File(imageUri))), "png").toByteArray().toExternalResource()
            }
        }

        fun randomNoise(image: BufferedImage) : BufferedImage {
            if (WikiConfig.image_noise_on) {
                (0..5).forEach { _ ->
                    val x = (0 until image.width).random()
                    val y = (0 until image.height).random()
                    image.setRGB(x, y, 0xffffff)
                }
            }
            return image
        }

        /**
         * 从图片中随机截取一部分
         */
        fun getImagePiece(imagePath: String, w : Int, h : Int): BufferedImage {
            val image = ImageIO.read(File(imagePath))
            while (true) {
                var transparentCount = 0;
                val piece = image.getSubimage(
                    (0 until image.width-w).random(), (0 until image.height-h).random(),
                 w, h)
                for (i in 0 until w) {
                    for (j in 0 until h) {
                        val pixel = piece.getRGB(i, j);
                        if (pixel shr 24 == 0) {
                            transparentCount += 1
                        }
                    }
                }

                if (transparentCount < w * h / 2) {
                    return piece
                }
            }
        }

        fun getImage(imageUri: String) : BufferedImage {
            if (imageUri.startsWith("http")) {
                return ImageIO.read(URL(imageUri))
            }
            else {
                return ImageIO.read(Path(imageUri).toFile())
            }
        }

        /**
         * 将图片链接读取到内存转换成ByteArrayOutputStream，方便操作
         */
        fun getVideo(videoUri: String): InputStream? {

//            val request: Request = Request.Builder().url(imageUri.replace("i.pximg.net","i.pixiv.cat")).get().build()
            val request: Request = Request.Builder().url(videoUri).get().build()
            return client.build().newCall(request).execute().body?.byteStream()
        }


        /**
         * 旋转角度
         * @param src 源图片
         * @param angel 角度
         * @return 目标图片
         */
        fun  rotate(src: Image, angel:Int): ByteArrayOutputStream
        {
            val srcWidth: Int = src.getWidth(null);
            val srcHeight : Int = src.getHeight (null);
            val rectDes : Rectangle? = CalcRotatedSize ( Rectangle ( Dimension (
                srcWidth, srcHeight)), angel)


            var res: BufferedImage? = null
            res = BufferedImage (rectDes!!.width, rectDes.height,BufferedImage.TYPE_INT_RGB);
            val g2: Graphics2D = res.createGraphics ();
            // transform(这里先平移、再旋转比较方便处理；绘图时会采用这些变化，绘图默认从画布的左上顶点开始绘画，源图片的左上顶点与画布左上顶点对齐，然后开始绘画，修改坐标原点后，绘画对应的画布起始点改变，起到平移的效果；然后旋转图片即可)

            //平移（原理修改坐标系原点，绘图起点变了，起到了平移的效果，如果作用于旋转，则为旋转中心点）
            g2.translate((rectDes.width - srcWidth) / 2, (rectDes.height - srcHeight) / 2);


            //旋转（原理transalte(dx,dy)->rotate(radians)->transalte(-dx,-dy);修改坐标系原点后，旋转90度，然后再还原坐标系原点为(0,0),但是整个坐标系已经旋转了相应的度数 ）
            g2.rotate(Math.toRadians(angel.toDouble()), srcWidth.toDouble() / 2, srcHeight.toDouble() / 2);

//        //先旋转（以目标区域中心点为旋转中心点，源图片左上顶点对准目标区域中心点，然后旋转）
//        g2.translate(rect_des.width/2,rect_des.height/ 2);
//        g2.rotate(Math.toRadians(angel));
//        //再平移（原点恢复到源图的左上顶点处（现在的右上顶点处），否则只能画出1/4）
//        g2.translate(-src_width/2,-src_height/2);


            g2.drawImage(src, null, null);
            return imageToBytes(res,"PNG")
        }


        /**
         * 转换BufferedImage 数据为byte数组
         *
         * @param image
         * Image对象
         * @param format
         * image格式字符串.如"gif","png"
         * @return byte数组
         */
        fun imageToBytes(bImage:BufferedImage, format:String):ByteArrayOutputStream {
            val out : ByteArrayOutputStream = ByteArrayOutputStream();

            try {
                ImageIO.write(bImage, format, out);
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return out
        }

        /**
         * 计算转换后目标矩形的宽高
         * @param src 源矩形
         * @param angel 角度
         * @return 目标矩形
         */
        private fun CalcRotatedSize(src: Rectangle, angel: Int): Rectangle {
            val cos = Math.abs(Math.cos(Math.toRadians(angel.toDouble())))
            val sin = Math.abs(Math.sin(Math.toRadians(angel.toDouble())))
            val des_width = (src.width * cos).toInt() + (src.height * sin).toInt()
            val des_height = (src.height * cos).toInt() + (src.width * sin).toInt()
            return Rectangle(Dimension(des_width, des_height))
        }

    }
}