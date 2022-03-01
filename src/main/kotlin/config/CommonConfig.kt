package org.iris.wiki.config

import org.iris.wiki.Wiki.dataFolder

object CommonConfig {

    private val root = dataFolder.absolutePath
    
    val ttf = "$root/思源黑体.ttf"

    val equip_output_path = "$root/out/equip"
    val ship_output_path = "$root/out/ship"
    val emoji_path = "$root/image/emoji"
    val json_string = "$root/config"
    val head_path = "$root/image/icon/head"
    val equip_path = "$root/image/equip"
    
    val ship_path = "$root/image/ship"
    val ship_skin_path = "$root/image/skin"
    val ship_label_path = "data/${root.split('\\').last()}/config/label"
}