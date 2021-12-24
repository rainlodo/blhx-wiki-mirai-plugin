package org.iris.wiki.config

object CommandString {

    // 指令头
    val wiki = listOf("wiki", ".wiki", "/wiki", "yls")

    val test = listOf("test")
//    val common = listOf("基本信息", "基本")

    // 基本属性页面
    val attribute = listOf("属性", "基本信息", "基本")

    // 舰娘皮肤
    val dress = listOf("皮肤", "换装")

    val update = listOf("改造", "改")

    // 舰娘获得途径
    val from = listOf("出处", "建造", "来源", "获得途径")

    // 舰娘提供的科技点
    val tech = listOf("科技点", "科技", "舰队科技")

    // 大佬们对舰娘的评价
    val evaluate = listOf("评价")

    // 画师声优等信息
    val other = listOf("角色信息", "其他", "其它")

    // 推荐装备
    val equip = listOf("装备", "出装", "配装")

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

}