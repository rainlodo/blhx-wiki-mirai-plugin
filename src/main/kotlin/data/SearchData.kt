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
        for (div in divList) {
            val title = div.child(0).attr("title")
            if (title.contains(commandList[1])) {
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

}
