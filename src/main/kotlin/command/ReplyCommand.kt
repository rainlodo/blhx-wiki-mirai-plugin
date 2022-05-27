package org.iris.wiki.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import org.iris.wiki.Wiki
import org.iris.wiki.config.AutoReplyConfig
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.utils.ImageUtil
import java.io.File
import javax.imageio.ImageIO

object ReplyCommand  : CompositeCommand(
    Wiki,
    primaryName = "reply",
    secondaryNames = arrayOf("设置自动回复")
) {


    private suspend fun getReplyContent(name : String, replies: Array<out SingleMessage>) : String {
        var msg = ""
        var index = 0

        // 检测图片输出文件夹是否存在
        for (path in listOf(CommonConfig.output_path, CommonConfig.reply_image_path)) {
            val folder = File(path)
            if (!folder.exists() && !folder.isDirectory) {
                folder.mkdir()
            }
        }


        for (r in replies) {
            when (r) {
                is PlainText -> msg += "{text:${r.contentToString()}}"
                is Image -> {
                    val img = ImageUtil.getImage(r.queryUrl())
                    val path = "${CommonConfig.reply_image_path}/${name}_${index++}.png"
                    ImageIO.write(img, "png", File(path))
                    msg += "{image:$path}"
                }
            }
        }
        return msg
    }

    /**
     * 正则匹配改自动回复中的图片并删除
     */
    private fun deleteReplyImage(command: String) {
        val regex = Regex("${command}_[^_.].png")
        val fileTree:FileTreeWalk = File(CommonConfig.reply_image_path).walk()
        fileTree.maxDepth(1)//遍历目录层级为1，即无需检查子目录
            .filter { it.isFile } //只挑选出文件,不处理文件夹
            .forEach {
                if (regex.matches(it.name)) {
                    it.delete()
                }
            }
    }

    @SubCommand("set", "设置")
    @Description("设置自动回复")
    suspend fun CommandSender.set(command: String, vararg replies: SingleMessage) {


        if (command in AutoReplyConfig.REPLY_COMMAND_MAP) {
            sendMessage("该自动回复指令已经被设置，覆盖请使用reset指令喵")
            return
        }


        try {
            val msg = getReplyContent(command, replies)
            AutoReplyConfig.REPLY_COMMAND_MAP[command] = msg
            sendMessage("设置成功喵")
        }
        catch (e: Exception) {
            sendMessage("出现未知错误喵")
        }
    }

    @SubCommand("reset", "覆盖设置")
    @Description("覆盖设置自动回复")
    suspend fun CommandSender.reset(command: String, vararg replies: SingleMessage) {

        try {
            deleteReplyImage(command)
            val msg = getReplyContent(command, replies)
            AutoReplyConfig.REPLY_COMMAND_MAP[command] = msg
            sendMessage("设置成功喵")
        }
        catch (e: Exception) {
            sendMessage("出现未知错误喵")
        }
    }

    @SubCommand("delete", "删除")
    @Description("删除自动回复")
    suspend fun CommandSender.delete(command: String) {

        if (AutoReplyConfig.REPLY_COMMAND_MAP.containsKey(command)) {

            deleteReplyImage(command)
            AutoReplyConfig.REPLY_COMMAND_MAP.remove(command)
            sendMessage("删除成功喵")
        }
        else {
            sendMessage("还没有该指令喵")
        }
    }

    @SubCommand("list", "列表")
    @Description("查看当前自动回复列表")
    suspend fun CommandSender.list() {
        if (AutoReplyConfig.REPLY_COMMAND_MAP.isEmpty()) {
            sendMessage("还没有设置自动回复指令喵")
        }
        else {
            sendMessage("现有自动回复指令：\n${AutoReplyConfig.REPLY_COMMAND_MAP.keys.joinToString("\n")}")
        }
    }

}