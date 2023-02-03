package org.iris.wiki.command


import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.UserCommandSender
import net.mamoe.mirai.contact.Group
import org.iris.wiki.Wiki
import org.iris.wiki.config.CommonConfig
import org.iris.wiki.config.WikiConfig
import org.iris.wiki.utils.UpdateUtils
import java.io.File

object WikiConfigCommand : CompositeCommand(
    Wiki,
    primaryName = "wiki-config",
    secondaryNames = arrayOf("wikiconfig", "wkcf")
) {

    /**
     * 遍历删除指定文件或文件夹下面的文件
     */
    private fun deleteDirectoryFiles(directory: File?) {
        if (directory == null || !directory.exists() || !directory.isDirectory) {
            return
        }
        if (directory != null && directory.exists() && directory.isDirectory) {
            for (listFile in directory.listFiles()) {
                if (listFile.isFile) {
                    listFile.delete()
                } else if (listFile.isDirectory) {
                    deleteDirectoryFiles(listFile)
                }
            }
        }
    }

    @SubCommand("clear")
    @Description("清除图片缓存")
    suspend fun CommandSender.clear() {
        deleteDirectoryFiles(File(CommonConfig.ship_output_path))
        deleteDirectoryFiles(File(CommonConfig.equip_output_path))
        sendMessage("缓存清除成功喵")
    }

    @SubCommand("舰船装备详情", "equipdetailon")
    @Description("开启/关闭舰娘wiki中的舰娘装备详情板块")
    suspend fun CommandSender.equip_detail(enabled: Boolean = true) {
        WikiConfig.ship_equip_efficiency_on = enabled
        sendMessage("设置成功喵")
    }

//    @SubCommand("alias", "别名")
//    suspend fun CommandSender.alias(enabled: Boolean = true) {
//        WikiConfig.ship_equip_efficiency_on = enabled
//        sendMessage("设置成功喵")
//    }

    @SubCommand("大建")
    @Description("开启/关闭本群的大建功能")
    suspend fun UserCommandSender.draw_ship_enable(enabled: Boolean) {
        if (subject is Group) {
            if (enabled) {
                WikiConfig.draw_ship_ban_list.remove(subject.id.toString())
                sendMessage("设置成功喵")
            } else {
                if (!WikiConfig.draw_ship_ban_list.contains(subject.id.toString())) {
                    WikiConfig.draw_ship_ban_list.add(subject.id.toString())
                }
                sendMessage("设置成功喵")
            }
        }
    }

    @SubCommand("猜老婆")
    @Description("开启/关闭本群的猜老婆功能")
    suspend fun UserCommandSender.gusss_ship_enable(enabled: Boolean) {
        if (subject is Group) {
            if (enabled) {
                WikiConfig.gauss_ship_ban_list.remove(subject.id.toString())
                sendMessage("设置成功喵")
            } else {
                if (!WikiConfig.gauss_ship_ban_list.contains(subject.id.toString())) {
                    WikiConfig.gauss_ship_ban_list.add(subject.id.toString())
                }
                sendMessage("设置成功喵")
            }
        }
    }

    @SubCommand("涩涩")
    suspend fun UserCommandSender.h_enable(enabled: Boolean) {
        if (subject is Group) {
            if (!enabled) {
                WikiConfig.setu_list.remove(subject.id.toString())
                sendMessage("设置成功喵")
            } else {
                if (!WikiConfig.setu_list.contains(subject.id.toString())) {
                    WikiConfig.setu_list.add(subject.id.toString())
                }
                sendMessage("设置成功喵")
            }
        }
    }

    @SubCommand("update")
    suspend fun UserCommandSender.update() {
        UpdateUtils.updateAll()
        sendMessage("更新完毕喵")
    }
}