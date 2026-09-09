package org.iris.wiki.config

import org.iris.wiki.Wiki
import org.iris.wiki.utils.ConfigYamlUtils

object WikiConfig {

    private val fileName = "WikiConfig.yml"

    var ship_equip_efficiency_on: Boolean = false
    var image_noise_on: Boolean = false
    var command_parse_on: Boolean = true
    var gauss_ship_ban_list: MutableList<String> = mutableListOf()
    var draw_ship_ban_list: MutableList<String> = mutableListOf()
    var setu_list: MutableList<String> = mutableListOf()

    fun load() {
        val configFile = Wiki.resolveConfigFile(fileName)
        if (!configFile.exists()) {
            save()
            return
        }

        val configMap = ConfigYamlUtils.loadMap(configFile)
        ship_equip_efficiency_on = ConfigYamlUtils.readBoolean(configMap, "ship_equip_efficiency_on", false)
        image_noise_on = ConfigYamlUtils.readBoolean(configMap, "image_noise_on", false)
        command_parse_on = ConfigYamlUtils.readBoolean(configMap, "command_parse_on", true)
        gauss_ship_ban_list = ConfigYamlUtils.readStringList(configMap, "gauss_ship_ban_list", mutableListOf())
        draw_ship_ban_list = ConfigYamlUtils.readStringList(configMap, "draw_ship_ban_list", mutableListOf())
        setu_list = ConfigYamlUtils.readStringList(configMap, "setu_list", mutableListOf())
    }

    fun save() {
        ConfigYamlUtils.saveMap(
            Wiki.resolveConfigFile(fileName),
            linkedMapOf(
                "ship_equip_efficiency_on" to ship_equip_efficiency_on,
                "image_noise_on" to image_noise_on,
                "command_parse_on" to command_parse_on,
                "gauss_ship_ban_list" to gauss_ship_ban_list.toList(),
                "draw_ship_ban_list" to draw_ship_ban_list.toList(),
                "setu_list" to setu_list.toList()
            )
        )
    }
}
