package org.iris.wiki.config

val SEARCH_URL = "https://wiki.biligame.com/blhx/index.php?search="

val COMMON = "基本信息"

val BOAT_UPDATE = "改造"
val BOAT_NO_UPDATA = "还没有改造呢~"

val BOAT_SKILL = "技能"

val MESSAGE_HELP = "指令格式如下：\n" +
    "wiki [舰船]/[装备]\n" +
    "wiki [舰船] 皮肤/配装/出处/评价/科技点/[语音]\n" +
    "wiki [装备] 出处\n" +
    "wiki 大建 轻池/特池/重池/[活动池]\n" +
    "装备名中的空格用_代替~"

val MESSAGE_ERROR = "输入指令有误~\n" +
    MESSAGE_HELP

val MESSAGE_SEARCH = "没有对应的词条但是找到了以下相关内容，可以尝试搜索下面的词条~\n" +
    "(〃'▽'〃)\n"

val MESSAGE_NO_RESULT = "没有查询到结果呢~\n" +
    "(〃'▽'〃)\n" +
    MESSAGE_HELP

val MESSAGE_PARSE_ERROR = "页面解析有误，暂不支持此种类页面解析~\n" +
    "(｡•́︿•̀｡)\n" +
    "可以直接点击下方链接打开页面\n"

val QQ_DIC = hashMapOf<Long, String>(
    1609277270L to "cct",
    2597766090L to "枪炮",
    3302191152L to "百合子",
    943584533L  to "奈芙莲"
)