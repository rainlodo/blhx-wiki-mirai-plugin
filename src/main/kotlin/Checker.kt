package org.iris.wiki

import net.mamoe.mirai.contact.Member
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.data.Data
import org.iris.wiki.action.Draw
import org.iris.wiki.data.ImagesData
import org.iris.wiki.data.TextData
import org.iris.wiki.utils.DrawUtils
import org.iris.wiki.utils.ParserUtils

/**
 * 用于对搜索的词进行检测，过滤掉一些特殊意义的词汇
 */
object Checker {

    fun check(commandList: Array<String>, sender: Member) : Data? {

        if (ParserUtils.wordToPinyin(commandList[1]) == "yls" || commandList[1] in listOf("iris", "atri", "亚托莉")) {
            // 不许wiki作者QAQ
            return ImagesData(arrayListOf("${CommonConfig.emoji_path}/wiki_iris.jpg")).activateAt()
        }
        else if (commandList[1] in listOf("ccc", "cct", "菜菜冲", "菜菜秃", "小林", "林总")) {
            return TextData("ccttql")
        }
        else if (commandList[1] in listOf("锉刀", "锉宝", "挫刀", "小锉锉")) {
            // 锉宝可是要生气了
            return ImagesData(arrayListOf("${CommonConfig.emoji_path}/cuodao_${(1..4).random()}.jpg")).activateAt()
        }
        else if (commandList[1].contains("大建") || commandList.size > 2 && commandList[2].contains("大建")) {
            var command = commandList[1]
            if (commandList.size > 2) {
                command += commandList[2]
            }
            return draw(command.replace("大建", ""))
        }
        else {
            return null
        }
    }

    fun draw(command : String) : Data {
        var type = DrawUtils.DrawType.Light

        if (command.contains("重池") || command.contains("重型")) {
            type = DrawUtils.DrawType.Heavy
        }
        else if (command.contains("特池") || command.contains("特型")) {
            type = DrawUtils.DrawType.Special
        }
        else if (command.contains("轻池") || command.contains("轻型")) {
            type = DrawUtils.DrawType.Light
        }
        else if (command.contains("列表")) {
            var text = "目前的建造列表有：\n轻池、重池、特池\n"
            DrawUtils.active_ship_map.keys.forEach {
                text += it + "\n"
            }
            return TextData(text)
        }
        else if (command in DrawUtils.active_ship_map.keys) {
            return Draw().drawActive(DrawUtils.active_ship_map[command]!!)
        }
        else if (command != "") {
            var text = "输入参数有误，目前的建造列表有：\n轻池、重池、特池\n"
            DrawUtils.active_ship_map.keys.forEach {
                text += it + "\n"
            }
            return TextData(text)
        }
        return Draw().draw(type)
    }

}