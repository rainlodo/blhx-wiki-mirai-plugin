package org.iris.wiki.config

import org.iris.wiki.Wiki
import org.iris.wiki.utils.ConfigYamlUtils

object CommandConfig {

    private const val fileName = "CommandConfig.yml"

    private val defaultWiki = arrayOf("wiki", ".wiki", "/wiki", "yls")
    private val defaultAttribute = arrayOf("属性", "基本信息", "基本")
    private val defaultDress = arrayOf("皮肤", "换装")
    private val defaultDressLarge = arrayOf("皮肤原图", "皮肤大图")
    private val defaultPicLarge = arrayOf("原皮", "大图")
    private val defaultUpdate = arrayOf("改造", "改")
    private val defaultFrom = arrayOf("出处", "建造", "来源", "获得途径")
    private val defaultTech = arrayOf("科技点", "科技", "舰队科技")
    private val defaultEvaluate = arrayOf("评价")
    private val defaultOther = arrayOf("角色信息", "其他", "其它")
    private val defaultEquip = arrayOf("装备", "出装", "配装")
    private val defaultTable = arrayOf("榜单", "表单", "图榜")
    private val defaultWedding = arrayOf("婚皮原图", "婚皮大图", "婚纱原图", "婚纱大图")
    private val defaultSetu = arrayOf("涩图", "涩涩", "色图", "色色")
    private const val defaultTouchFirstParam = "小加加"
    private const val defaultTouchSecondParam = "触摸"
    private const val defaultSetuPath = "无"
    private const val defaultTableList =
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

    var wiki: Array<String> = defaultWiki.copyOf()
    var attribute: Array<String> = defaultAttribute.copyOf()
    var dress: Array<String> = defaultDress.copyOf()
    var dressLarge: Array<String> = defaultDressLarge.copyOf()
    var picLarge: Array<String> = defaultPicLarge.copyOf()
    var update: Array<String> = defaultUpdate.copyOf()
    var from: Array<String> = defaultFrom.copyOf()
    var tech: Array<String> = defaultTech.copyOf()
    var evaluate: Array<String> = defaultEvaluate.copyOf()
    var other: Array<String> = defaultOther.copyOf()
    var equip: Array<String> = defaultEquip.copyOf()
    var table: Array<String> = defaultTable.copyOf()
    var wedding: Array<String> = defaultWedding.copyOf()
    var touch_first_param: String = defaultTouchFirstParam
    var touch_second_param: String = defaultTouchSecondParam
    var setu: Array<String> = defaultSetu.copyOf()
    var setu_path: String = defaultSetuPath
    var tableList: String = defaultTableList

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

    val voice_map = mapOf(
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

    fun load() {
        val configFile = Wiki.resolveConfigFile(fileName)
        if (!configFile.exists()) {
            save()
            rebuildAllCommands()
            return
        }

        val configMap = ConfigYamlUtils.loadMap(configFile)
        wiki = ConfigYamlUtils.readStringArray(configMap, "wiki", defaultWiki)
        attribute = ConfigYamlUtils.readStringArray(configMap, "attribute", defaultAttribute)
        dress = ConfigYamlUtils.readStringArray(configMap, "dress", defaultDress)
        dressLarge = ConfigYamlUtils.readStringArray(configMap, "dressLarge", defaultDressLarge)
        picLarge = ConfigYamlUtils.readStringArray(configMap, "picLarge", defaultPicLarge)
        update = ConfigYamlUtils.readStringArray(configMap, "update", defaultUpdate)
        from = ConfigYamlUtils.readStringArray(configMap, "from", defaultFrom)
        tech = ConfigYamlUtils.readStringArray(configMap, "tech", defaultTech)
        evaluate = ConfigYamlUtils.readStringArray(configMap, "evaluate", defaultEvaluate)
        other = ConfigYamlUtils.readStringArray(configMap, "other", defaultOther)
        equip = ConfigYamlUtils.readStringArray(configMap, "equip", defaultEquip)
        table = ConfigYamlUtils.readStringArray(configMap, "table", defaultTable)
        wedding = ConfigYamlUtils.readStringArray(configMap, "wedding", defaultWedding)
        touch_first_param = ConfigYamlUtils.readString(configMap, "touch_first_param", defaultTouchFirstParam)
        touch_second_param = ConfigYamlUtils.readString(configMap, "touch_second_param", defaultTouchSecondParam)
        setu = ConfigYamlUtils.readStringArray(configMap, "setu", defaultSetu)
        setu_path = ConfigYamlUtils.readString(configMap, "setu_path", defaultSetuPath)
        tableList = ConfigYamlUtils.readString(configMap, "tableList", defaultTableList)
        rebuildAllCommands()
    }

    fun save() {
        ConfigYamlUtils.saveMap(
            Wiki.resolveConfigFile(fileName),
            linkedMapOf(
                "wiki" to wiki.toList(),
                "attribute" to attribute.toList(),
                "dress" to dress.toList(),
                "dressLarge" to dressLarge.toList(),
                "picLarge" to picLarge.toList(),
                "update" to update.toList(),
                "from" to from.toList(),
                "tech" to tech.toList(),
                "evaluate" to evaluate.toList(),
                "other" to other.toList(),
                "equip" to equip.toList(),
                "table" to table.toList(),
                "wedding" to wedding.toList(),
                "touch_first_param" to touch_first_param,
                "touch_second_param" to touch_second_param,
                "setu" to setu.toList(),
                "setu_path" to setu_path,
                "tableList" to tableList
            )
        )
    }

    fun rebuildAllCommands() {
        ALL_COMMAND.clear()
        val commands = listOf(
            attribute,
            dress,
            dressLarge,
            picLarge,
            from,
            tech,
            evaluate,
            equip,
            wedding
        )
        commands.forEach {
            ALL_COMMAND.addAll(it)
        }
        ALL_COMMAND.addAll(voice_map.keys)
    }
}
