package org.iris.wiki.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object WikiConfig: AutoSavePluginConfig("WikiConfig") {

    @ValueDescription("是否显示装备效率")
    var ship_equip_efficiency_on :Boolean by value(false)

    @ValueDescription("禁止猜老婆的群列表")
    var gauss_ship_ban_list :MutableList<String> by value(mutableListOf())

    @ValueDescription("禁止大建的群列表")
    var draw_ship_ban_list :MutableList<String> by value(mutableListOf())

}