package org.iris.wiki

import net.mamoe.mirai.contact.Member
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.Data
import org.iris.wiki.data.ImagesData
import org.iris.wiki.utils.ParserUtils

/**
 * 用于对搜索的词进行检测，过滤掉一些特殊意义的词汇
 */
object Checker {

    fun check(commandList: Array<String>, sender: Member) : Data? {

        if (ParserUtils.wordToPinyin(commandList[1]) == "yls" || commandList[1] == "iris") {
            // 不许wiki作者QAQ
            return ImagesData(arrayListOf("${CommonConfig.emoji_path}/wiki_iris.jpg")).activateAt()
        }
        else if (commandList[1] in listOf("锉刀", "锉宝", "挫刀", "小锉锉")) {
            // 锉宝可是要生气了
            return ImagesData(arrayListOf("${CommonConfig.emoji_path}/cuodao_${(1..4).random()}.jpg")).activateAt()
        }
        else {
            return null
        }
    }

}