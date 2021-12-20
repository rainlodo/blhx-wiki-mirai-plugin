package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.utils.ImageUtil
import org.jsoup.nodes.Document

@Serializable
data class ImagesData (
    @SerialName("images")
    var images : ArrayList<String> = arrayListOf()
) : Data() {

    override fun parse(doc: Document, commandList: List<String>) : Data {

        val table = doc.select("div[class='Contentbox2']")
        val imgs = table.select("img")
        for (i in 1 until imgs.size) {
            images.add(imgs[i].attr("src"))
        }
        if (images.isEmpty()) {
            images.add("data/image/emoji/meiyinci.jpg")
        }
        if (commandList[1] == "美因茨") {
            images.add("data/image/emoji/joker_is_me.jpeg")
        }
        return super.parse(doc, commandList)
    }

    override suspend fun toMessage(sender: Member): Message {
        val builder = MessageChainBuilder()
        for (url in images) {

            val src = ImageUtil.getImage(url)
            val imageId: String = src.uploadAsImage(sender.group).imageId
            builder.add(Image(imageId))
        }

        return builder.build()
    }

}