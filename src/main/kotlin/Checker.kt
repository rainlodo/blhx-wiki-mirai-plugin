package org.iris.wiki

import net.mamoe.mirai.contact.Member
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.action.Draw
import org.iris.wiki.config.AutoReplyConfig
import org.iris.wiki.config.CommandConfig
import org.iris.wiki.config.WikiConfig
import org.iris.wiki.data.*
import org.iris.wiki.utils.DrawUtils
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * 用于对搜索的词进行检测，过滤掉一些特殊意义的词汇
 */
object Checker {

    fun check(commandList: Array<String>, sender: Member) : Data? {

        if (commandList[1] in CommandConfig.table) {
            return TextData(CommandConfig.tableList)
        }
        else if (commandList[1] in listOf("help", "Help", "HELP", "帮助", "指令")) {
            return ImagesData(arrayListOf("${CommonConfig.emoji_path}/help.png"))
        }
        else if (commandList[1] in listOf("锉刀", "锉宝", "挫刀", "小锉锉")) {
            // 锉宝可是要生气了
            return ImagesData(arrayListOf("${CommonConfig.emoji_path}/cuodao_${(1..4).random()}.jpg")).activateAt()
        }
        else if (commandList[1].contains("大建") || commandList.size > 2 && commandList[2].contains("大建")) {
            if (WikiConfig.draw_ship_ban_list.contains(sender.group.id.toString())) {
                return TextData("本群未开启大建喵")
            }
            var command = commandList[1]
            if (commandList.size > 2) {
                command += commandList[2]
            }
            return draw(command.replace("大建", ""))
        }
        else if (commandList.size > 2 && commandList[2] in listOf("语音", "[语音]")) {
            return TextData(CommandConfig.voiceList)
        }
        else if (commandList[1] in AutoReplyConfig.REPLY_COMMAND_MAP) {
            return AutoReplyData(AutoReplyConfig.REPLY_COMMAND_MAP[commandList[1]]!!)
        }
        else if (commandList[1] in CommandConfig.setu) {
            return setu(sender)
        }
        else {
            return null
        }
    }

    /**
     * 抽卡
     */
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

    fun setu(sender: Member) : Data {
        if (!WikiConfig.setu_list.contains(sender.group.id.toString()) || !File(CommandConfig.setu_path).exists() || !File(CommandConfig.setu_path).isDirectory) {
            return TextData("不许涩涩喵")
        }

        val files = File(CommandConfig.setu_path).listFiles()
        return if (files.isNotEmpty()) {
            // 随机加几个噪点，规避tx的md5检测
            val image: BufferedImage = ImageIO.read(files.random())
            image.setRGB(0,0,0)
            (0..5).forEach { _ ->
                val x = (0 until image.width).random()
                val y = (0 until image.height).random()
                image.setRGB(x, y, 0xffffff)
            }
            BufferImagesData(arrayListOf(image))
        } else {
            TextData("不许涩涩喵")
        }
    }

}