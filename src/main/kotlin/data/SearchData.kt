package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import org.iris.wiki.config.MESSAGE_NO_RESULT
import org.iris.wiki.config.MESSAGE_SEARCH
import org.jsoup.nodes.Document

@Serializable
data class SearchData(
    @SerialName("result")
    val result: ArrayList<String> = arrayListOf()
) : Data() {

    override fun parse(doc: Document, commandList: List<String>) : Data {
        super.parse(doc, commandList)
        val divList = doc.select("div[class='searchresults']")[0].select("div[class='mw-search-result-heading']")
        val keyword = normalize(commandList[1])
        for (div in divList) {
            val title = div.child(0).attr("title")
            if (keyword.isEmpty() || normalize(title).contains(keyword)) {
                result.add(title)
            }
        }
        return super.parse(doc, commandList)
    }

    override suspend fun toMessage(sender: Member): Message {
        val builder = MessageChainBuilder()

        if (result.isEmpty()) {
            builder.add(MESSAGE_NO_RESULT)
        }
        else {
            builder.add(MESSAGE_SEARCH)
            for (res in result) {
                builder.add("\n$res")
            }
        }

        return builder.build()
    }

    private fun normalize(value: String): String {
        return buildString(value.length) {
            value.lowercase().forEach { ch ->
                if (ch in '\u4e00'..'\u9fa5' ||
                    ch in '\u0030'..'\u0039' ||
                    ch in '\u0041'..'\u005A' ||
                    ch in '\u0061'..'\u007A'
                ) {
                    append(ch)
                }
            }
        }
    }

}
