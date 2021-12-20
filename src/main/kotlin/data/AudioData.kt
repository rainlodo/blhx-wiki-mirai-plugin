package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import org.iris.wiki.config.CommandString
import org.iris.wiki.utils.HttpUtils
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import kotlin.io.path.Path

@Serializable
data class AudioData(
    @SerialName("url")
    var url : String? = null
) : Data() {

    override fun parse(doc: Document, commandList: List<String>) : Data {


        val table = doc.select("table[class='table-ShipWordsTable']")
        val trList = table.select("tr")
        val linkList = arrayListOf<Element>()
        trList.forEach{ it ->
            if (it.attr("data-key") == CommandString.voice_map[commandList[2]]) {
                it.select("a").forEach { iit->
                    linkList.add(iit)
                }
            }
        }
        url = linkList[(0 until linkList.size).random()].attr("href")

        return super.parse(doc, commandList)
    }


    override suspend fun toMessage(sender: Member): Message {
        if (url == null) {
            return PlainText("该舰娘没有此语音哦~")
        }
        else {
            if (url!!.startsWith("http")) {
                val src = HttpUtils.getByteArray(url!!)?.toByteArray()?.toExternalResource()
                if (src != null) {
                    val audio = sender.group.uploadAudio(src)
                    src.close()
                    return audio
                }
                return PlainText("语音下载失败QAQ")
            } else {
                val src = Path(url!!).toFile().toExternalResource()
                val audio = sender.group.uploadAudio(src)
                src.close()
                return audio
            }
        }
    }
}
