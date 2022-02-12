package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.utils.ImageUtil
import org.jsoup.nodes.Document
import java.util.*
import kotlin.collections.ArrayList

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
            images.add("${CommonConfig.emoji_path}/meiyinci.jpg")
        }
        if (commandList[1] == "美因茨") {
            images.add("${CommonConfig.emoji_path}/joker_is_me.jpeg")
        }
        return super.parse(doc, commandList)
    }

    override suspend fun toMessage(sender: Member): Message {
        val builder = MessageChainBuilder()
        if (images.size > 3) {
            val mutableList = mutableListOf<ForwardMessage.Node>()
            for (url in images) {
                mutableList.add(ForwardMessage.Node(
                    sender.bot.id,
                    Date().time.toInt(),
                    sender.bot.nameCardOrNick,
                    buildMessageChain {
                        +sender.uploadImage(ImageUtil.getImageAsExResource(url))
                    }
                ))
            }
            return  RawForwardMessage(mutableList).render(
                object : ForwardMessage.DisplayStrategy {
                    override fun generateTitle(forward: RawForwardMessage): String {
                        return "多张图片"
                    }
                }
            )
        }
        else {
            for (url in images) {

                val src = ImageUtil.getImageAsExResource(url)
                val imageId: String = src.uploadAsImage(sender.group).imageId
                src.close()
                builder.add(Image(imageId))
            }
            return builder.build()
        }
    }

}