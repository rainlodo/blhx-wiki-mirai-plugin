package org.iris.wiki.config

import org.iris.wiki.Wiki
import org.iris.wiki.utils.ConfigYamlUtils
import java.util.LinkedHashMap

object AutoReplyConfig {

    private val fileName = "AutoReplyConfig.yml"
    private val defaultReplyCommandMap = linkedMapOf<String, String>()

    var REPLY_COMMAND_MAP: MutableMap<String, String> = LinkedHashMap(defaultReplyCommandMap)

    fun load() {
        val configFile = Wiki.resolveConfigFile(fileName)
        if (!configFile.exists()) {
            save()
            return
        }

        val configMap = ConfigYamlUtils.loadMap(configFile)
        REPLY_COMMAND_MAP = ConfigYamlUtils.readStringMap(configMap, "REPLY_COMMAND_MAP", defaultReplyCommandMap)
    }

    fun save() {
        ConfigYamlUtils.saveMap(
            Wiki.resolveConfigFile(fileName),
            linkedMapOf(
                "REPLY_COMMAND_MAP" to LinkedHashMap(REPLY_COMMAND_MAP)
            )
        )
    }
}
