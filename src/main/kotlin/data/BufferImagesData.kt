package org.iris.wiki.data

import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


data class BufferImagesData (
    var images : ArrayList<BufferedImage> = arrayListOf()
) : Data() {


    override suspend fun toMessage(sender: Member): Message {
        val builder = MessageChainBuilder()
        for (image in images) {

            val out = ByteArrayOutputStream()
            ImageIO.write(image, "png", out)
            val src = out.toByteArray().toExternalResource()
            val imageId: String = src.uploadAsImage(sender.group).imageId
            src.close()
            builder.add(Image(imageId))
        }

        return builder.build()
    }

}