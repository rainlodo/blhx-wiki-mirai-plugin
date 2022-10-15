package org.iris.wiki.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object WikiConfig: AutoSavePluginConfig("WikiConfig") {

    @ValueDescription("是否显示装备效率")
    var ship_equip_efficiency_on :Boolean by value(false)

    @ValueDescription("是否开启噪点添加（在图像上随机添加噪点，能够一定程度上减少图片被吞概率，但是会略微增加处理时间）")
    var image_noise_on :Boolean by value(true)

    @ValueDescription("自动分割指令")
    var command_parse_on :Boolean by value(true)

    @ValueDescription("禁止猜老婆的群列表")
    var gauss_ship_ban_list :MutableList<String> by value(mutableListOf())

    @ValueDescription("禁止大建的群列表")
    var draw_ship_ban_list :MutableList<String> by value(mutableListOf())

}