package org.iris.wiki.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import org.iris.wiki.Wiki
import org.iris.wiki.config.CommonConfig
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
    suspend fun CommandSender.clear() {
        deleteDirectoryFiles(File(CommonConfig.ship_output_path))
        deleteDirectoryFiles(File(CommonConfig.equip_output_path))
        sendMessage("缓存清除成功喵")
    }

}