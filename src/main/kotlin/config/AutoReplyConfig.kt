package org.iris.wiki.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object AutoReplyConfig : AutoSavePluginConfig("AutoReplyConfig") {

    @ValueDescription("自动回复指令及其对应的回复内容")
    var REPLY_COMMAND_MAP : MutableMap<String, String> by value(mutableMapOf<String, String>())

}