package org.iris.wiki

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.version
import org.iris.wiki.action.QuestionListener
import org.iris.wiki.command.ReplyCommand
import org.iris.wiki.command.WikiConfigCommand
import org.iris.wiki.config.AliasConfig
import org.iris.wiki.config.AutoReplyConfig
import org.iris.wiki.config.CommandConfig
import org.iris.wiki.config.WikiConfig

/**
 * 使用 kotlin 版请把
 * `src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin`
 * 文件内容改成 `org.example.mirai.plugin.PluginMain` 也就是当前主类全类名
 *
 * 使用 kotlin 可以把 java 源集删除不会对项目有影响
 *
 * 在 `settings.gradle.kts` 里改构建的插件名称、依赖库和插件版本
 *
 * 在该示例下的 [JvmPluginDescription] 修改插件名称，id和版本，etc
 *
 * 可以使用 `src/test/kotlin/RunMirai.kt` 在 ide 里直接调试，
 * 不用复制到 mirai-console-loader 或其他启动器中调试
 */

object Wiki : KotlinPlugin(
    JvmPluginDescription(
        id = "org.iris.wiki",
        name = "blhx-wiki",
        version = "0.3.17"
    ) {
        author("iris")
        // author 和 info 可以删除.
    }
) {
    override fun onEnable() {

        //配置文件目录 "${dataFolder.absolutePath}/"

        AliasConfig.reload()
        CommandConfig.reload()
        WikiConfig.reload()
        AutoReplyConfig.reload()

        Listener.subscribe()
        QuestionListener.subscribe()

        WikiConfigCommand.register()
        ReplyCommand.register()
    }

    override fun onDisable() {

        WikiConfigCommand.unregister()
        ReplyCommand.unregister()

        Listener.stop()
        QuestionListener.stop()

        super.onDisable()
    }

}
