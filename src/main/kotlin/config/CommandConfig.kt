package org.iris.wiki.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value


/**
 * 命令名的配置
 * 用户输入指令会自动转变为小写
 * @constructor 实例化命令配置
 * @see AutoSavePluginConfig
 */
object CommandConfig : AutoSavePluginConfig("CommandConfig") {

    // 指令头
    @ValueDescription("wiki指令名")
    val wiki : Array<String> by value(arrayOf("wiki", ".wiki", "/wiki", "yls"))


    // 基本属性页面
    @ValueDescription("舰娘、装备基本属性")
    val attribute : Array<String> by value(arrayOf("属性", "基本信息", "基本"))

    // 舰娘皮肤
    @ValueDescription("舰娘皮肤")
    val dress : Array<String> by value(arrayOf("皮肤", "换装"))

    // 舰娘皮肤
    @ValueDescription("舰娘皮肤原图")
    val dressLarge : Array<String> by value(arrayOf("皮肤原图", "皮肤大图"))

    // 舰娘原皮
    @ValueDescription("舰娘原图")
    val picLarge : Array<String> by value(arrayOf("原皮", "大图"))

    @ValueDescription("舰娘改造所需素材")
    val update : Array<String> by value(arrayOf("改造", "改"))

    // 舰娘获得途径
    @ValueDescription("舰娘、装备获得途径")
    val from : Array<String> by value(arrayOf("出处", "建造", "来源", "获得途径"))

    // 舰娘提供的科技点
    @ValueDescription("舰娘提供的科技点")
    val tech : Array<String> by value(arrayOf("科技点", "科技", "舰队科技"))

    // 大佬们对舰娘的评价
    @ValueDescription("大佬们对舰娘的评价")
    val evaluate : Array<String> by value(arrayOf("评价"))

    // 画师声优等信息
    @ValueDescription("画师声优等信息")
    val other : Array<String> by value(arrayOf("角色信息", "其他", "其它"))

    // 推荐装备
    @ValueDescription("推荐舰娘使用的装备")
    val equip : Array<String> by value(arrayOf("装备", "出装", "配装"))

    // 榜单
    @ValueDescription("常用榜单")
    val table : Array<String> by value(arrayOf("榜单", "表单", "图榜"))

    @ValueDescription("婚皮原图")
    val wedding : Array<String> by value(arrayOf("婚皮原图", "婚皮大图", "婚纱原图", "婚纱大图"))

    // 戳一戳事件对应舰娘
    @ValueDescription("戳一戳事件对应舰娘(wiki __1__ __2__ 指令中的__1__), 为 '无' 时不对戳一戳事件反应")
    val touch_first_param : String by value("小加加")

    // 戳一戳事件对应查询
    @ValueDescription("戳一戳事件对应查询命令(wiki __1__ __2__ 指令中的__2__)")
    val touch_second_param : String by value("触摸")

    // 戳一戳事件对应舰娘
    @ValueDescription("隐藏功能，发送本地涩图（需要设置本地涩图文件夹路径）")
    val setu : Array<String> by value(arrayOf("涩图", "涩涩", "色图", "色色"))

    // 戳一戳事件对应查询
    @ValueDescription("本地涩图文件夹路径(注意不能使用\\，请用/代替)")
    val setu_path : String by value("无")

    // 目前支持榜单
    @ValueDescription("支持查询的榜单")
    val tableList : String by value(
        "目前支持查询的榜单有：\n" +
        "*()内为简写\n" +
        "PVE用舰船综合性能强度榜(舰娘一图榜)\n" +
        "装备一图榜\n" +
        "认知觉醒推荐榜(觉醒榜)\n" +
        "萌新入坑推荐榜(萌新榜)\n" +
        "兑换装备推荐榜(兑换榜)\n" +
        "装备研发推荐榜(研发榜)\n" +
        "改造舰船推荐榜(改造榜)\n" +
        "井号打捞表(打捞表)\n"
    )

    // 支持查询的语音
    val voiceList =
        "请输入具体语音类型，如：\n" +
        "wiki 小加加 技能\n" +
        "目前支持查询的语音有：\n" +
            "自我介绍，获取语音，" +
            "登录，查看详情，主页，" +
            "触摸，特触，摸头，" +
            "任务提醒，任务完成，邮件，" +
            "回港，失望/未知，陌生/调率/普通，" +
            "友好/理解，喜欢/同步/协作，爱/共鸣/应援，" +
            "誓约，委托完成，强化，" +
            "开战，mvp，失败，" +
            "技能，大破"


    // 舰娘语音目录
    val voice_map = mapOf<String, String>(
        "自我介绍" to "profile",
        "获取台词" to "unlock",
        "获取语音" to "unlock",
        "登录" to "login",
        "hello" to "login",
        "你好" to "login",
        "登录台词" to "login",
        "登录语音" to "login",
        "查看详情" to "detail",
        "主界面" to "main",
        "主页" to "main",
        "摸摸" to "touch",
        "戳戳" to "touch",
        "触摸" to "touch",
        "触摸台词" to "touch",
        "触摸语音" to "touch",
        "特触" to "touch2",
        "特触语音" to "touch2",
        "摸胸" to "touch2",
        "特殊触摸" to "touch2",
        "摸摸头" to "headtouch",
        "摸头" to "headtouch",
        "摸头台词" to "headtouch",
        "摸头语音" to "headtouch",
        "任务" to "mission",
        "任务提醒" to "mission",
        "任务完成" to "mission_complete",
        "任务完成提醒" to "mission_complete",
        "邮件" to "mail",
        "邮件提醒" to "mail",
        "回港" to "home",
        "回港台词" to "home",
        "回港语音" to "home",
        "失望" to "feeling1",
        "未知" to "feeling1m",
        "骂我" to "feeling1",
        "陌生" to "feeling2",
        "调率" to "feeling2m",
        "普通" to "feeling2i",
        "友好" to "feeling3",
        "理解" to "feeling3m",
        "喜欢" to "feeling4",
        "同步" to "feeling4m",
        "协作" to "feeling4i",
        "爱" to "feeling5",
        "共鸣" to "feeling5m",
        "应援" to "feeling5i",
        "誓约" to "propose",
        "结婚" to "propose",
        "誓约台词" to "propose",
        "誓约语音" to "propose",
        "委托完成" to "expedition",
        "强化" to "upgrade",
        "强化语音" to "upgrade",
        "升级" to "upgrade",
        "旗舰开战" to "battle",
        "战斗" to "battle",
        "开战" to "battle",
        "胜利台词" to "win_mvp",
        "胜利语音" to "win_mvp",
        "mvp" to "win_mvp",
        "mvp台词" to "win_mvp",
        "mvp语音" to "win_mvp",
        "失败台词" to "lose",
        "失败语音" to "lose",
        "失败" to "lose",
        "技能台词" to "skill",
        "技能语音" to "skill",
        "技能" to "skill",
        "血量告急" to "hp_warning",
        "残血语音" to "hp_warning",
        "大破" to "hp_warning",
        "大破语音" to "hp_warning",
    )

    val ALL_COMMAND = hashSetOf<String>()
}