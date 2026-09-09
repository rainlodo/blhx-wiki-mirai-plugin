package org.iris.wiki.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.iris.wiki.Listener
import org.iris.wiki.Wiki
import org.iris.wiki.config.AliasConfig
import org.iris.wiki.config.AutoReplyConfig
import org.iris.wiki.config.CommandConfig
import org.iris.wiki.config.WikiConfig
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.Files
import java.nio.file.Path

object ConfigHotReloadManager {

    private val watchedConfigNames = setOf(
        "AliasConfig.yml",
        "CommandConfig.yml",
        "WikiConfig.yml",
        "AutoReplyConfig.yml"
    )

    private const val POLL_INTERVAL_MILLIS = 2000L

    private var watchJob: Job? = null
    private var fileSnapshots: Map<String, String> = emptyMap()

    @Synchronized
    fun reloadAllConfigs(reason: String) {
        AliasConfig.load()
        CommandConfig.load()
        WikiConfig.load()
        AutoReplyConfig.load()
        Listener.reloadRuntimeConfigCaches()
        Wiki.logger.info("配置已重载: $reason")
    }

    fun start() {
        stop()

        val configFolder = Wiki.configFolderPath
        Files.createDirectories(configFolder)
        fileSnapshots = takeSnapshots(configFolder)

        watchJob = Wiki.launch(Dispatchers.IO) {
            pollLoop(configFolder)
        }

        Wiki.logger.info("已开启配置文件热重载: $configFolder")
    }

    fun stop() {
        watchJob?.cancel()
        watchJob = null
        fileSnapshots = emptyMap()
    }

    private suspend fun pollLoop(configFolder: Path) {
        while (watchJob?.isActive == true) {
            delay(POLL_INTERVAL_MILLIS)
            val latestSnapshots = takeSnapshots(configFolder)
            val changedFiles = watchedConfigNames.filter { fileSnapshots[it] != latestSnapshots[it] }
            if (changedFiles.isEmpty()) {
                fileSnapshots = latestSnapshots
                continue
            }

            runCatching {
                reloadAllConfigs("检测到 ${changedFiles.joinToString("、")} 变更")
                fileSnapshots = takeSnapshots(configFolder)
            }.onFailure {
                fileSnapshots = latestSnapshots
                Wiki.logger.warning("配置热重载失败: ${it.message}")
            }
        }
    }

    private fun takeSnapshots(configFolder: Path): Map<String, String> {
        return watchedConfigNames.associateWith { name ->
            val path = configFolder.resolve(name)
            if (!Files.exists(path)) {
                return@associateWith "missing"
            }
            runCatching {
                val attributes = Files.readAttributes(path, BasicFileAttributes::class.java)
                "${attributes.lastModifiedTime().toMillis()}:${attributes.size()}"
            }.getOrElse {
                "unreadable:${Files.getLastModifiedTime(path).toMillis()}"
            }
        }
    }
}
