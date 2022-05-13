package org.iris.wiki.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value
import java.util.HashMap


object AliasConfig : AutoSavePluginConfig("AliasConfig") {

    @ValueDescription("别名，指令自动转小写，别名中的英文请使用小写字母")
    val ALIAS_USER_MAP : Map<String, String> by value(mapOf<String, String>(

    ))

    @ValueDescription("驱逐别名,此后为通用别名，用户无法修改，即使修改程序中也无法使用")
    val ALIAS_DD_MAP : Map<String, String> by value(mapOf<String, String>(
        "小加加" to "萨拉托加",
        "彩布里" to "特装型布里MKIII",
        "金布里" to "试作型布里MKII",
        "poi" to "夕立",
        "同志酱" to "塔什干",
        "铜治酱" to "塔什干",
        "μ塔什干" to "塔什干(μ兵装)",
        "μ恶毒" to "恶毒(μ兵装)",
        "布里" to "泛用型布里",
        "紫布里" to "泛用型布里",
        "加倍林" to "标枪",
        "加倍淋" to "标枪",
        "赵四" to "尼古拉斯",
        "举高高" to "埃尔德里奇",
        "新月jp" to "新月JP",
        "臭鼬" to "长波",
        "凌波" to "绫波",
        "z404" to "z23",
        "玛丽罗斯" to "玛莉萝丝",
        "nanoda" to "雪风"
    ))

    @ValueDescription("轻巡别名")
    val ALIAS_CL_MAP : Map<String, String> by value(mapOf<String, String>(
        "屑雅图" to "西雅图",
        "金坷垃" to "圣地亚哥",
        "彩坷垃" to "圣地亚哥",
        "贝爷" to "贝尔法斯特",
        "贝法" to "贝尔法斯特",
        "阿芙" to "阿芙乐尔",
        "能带" to "能代",
        "懵逼" to "蒙彼利埃",
        "蒙彼" to "蒙彼利埃",
        "蒙蔽" to "蒙彼利埃",
        "三叔" to "蒙彼利埃",
        "杰哥" to "确捷",
        "恰巴" to "恰巴耶夫",
        "μ黛朵" to "黛朵(μ兵装)",
        "黑海伦娜" to "海伦娜·META",
        "黑海妈" to "海伦娜·META",
        "海伦娜meta" to "海伦娜·META",
        "海妈meta" to "海伦娜·META",
        "海妈" to "海伦娜",
        "克爹" to "克利夫兰",
        "二叔" to "哥伦比亚",
        "重庆" to "欧若拉",
        "圣姨" to "圣路易斯",
        "四叔" to "丹佛",
        "四叔" to "丹佛",
        "小海妈" to "小海伦娜",
        "小克爹" to "小克利夫兰",
        "小金坷垃" to "小圣地亚哥",
        "μ克爹" to "克利夫兰(μ兵装)",
        "μ克利夫兰" to "克利夫兰(μ兵装)",
        "水星" to "水星纪念",
        "500" to "五十铃",
        "双子" to "埃姆登"
    ))

    @ValueDescription("重巡别名")
    val ALIAS_CA_MAP : Map<String, String> by value(mapOf<String, String>(
        "6194" to "路易九世",
        "路易" to "路易九世",
        "安宝" to "安克雷奇",
        "爆地魔" to "巴尔的摩",
        "大狗" to "爱宕",
        "吃手手" to "欧根亲王",
        "欧根" to "欧根亲王",
        "μ罗恩" to "罗恩(μ兵装)",
        "斯佩" to "斯佩伯爵海军上将",
        "斯佩伯爵" to "斯佩伯爵海军上将",
        "希佩尔" to "希佩尔海军上将",
        "μ希佩尔" to "希佩尔海军上将(μ兵装)",
        "μ巴尔的摩" to "巴尔的摩(μ兵装)",
        "μ爆地魔" to "巴尔的摩(μ兵装)",
        "波特彩" to "波特兰",
        "57" to "吾妻",
        "艾吉尔" to "埃吉尔",
        "屎壳郎" to "喀琅施塔得",
        "铁血约克" to "约克DE",
        "皇家约克" to "约克"
    ))

    @ValueDescription("战列别名")
    val ALIAS_BB_MAP : Map<String, String> by value(mapOf<String, String>(

        "大帝" to "腓特烈大帝",
        "波斯猫" to "俾斯麦",
        "北宅" to "提尔比茨",
        "大加加" to "加斯科涅",
        "北卡" to "北卡罗来纳",
        "花生" to "华盛顿",
        "大哥" to "威尔士亲王",
        "二哥" to "约克公爵",
        "公爵" to "约克公爵",
        "乔五" to "英王乔治五世",
        "苏白" to "苏维埃贝拉罗斯",
        "罗西亚" to "苏维埃罗西亚",
        "黎姐" to "黎塞留",
        "小饼干" to "豪",
        "维托里奥" to "维托里奥·维内托",
        "维内托" to "维托里奥·维内托",
        "vv" to "维托里奥·维内托",
        "绿托" to "利托里奥",
        "伊丽莎白" to "伊丽莎白女王",
        "傻白" to "伊丽莎白女王",
        "扶桑META" to "扶桑·META",
        "黑扶桑" to "扶桑·META",
        "黑格奈森瑙" to "格奈森瑙·META",
        "黑格奈" to "格奈森瑙·META",
        "格奈森瑙meta" to "格奈森瑙·META",
        "格奈meta" to "格奈森瑙·META",
        "格奈" to "格奈森瑙",
        "崩姐" to "宾夕法尼亚",
        "几把兔" to "新泽西",
        "胡滕" to "乌尔里希·冯·胡滕"
    ))

    @ValueDescription("航母别名")
    val ALIAS_CV_MAP : Map<String, String> by value(mapOf<String, String>(
        "奥古斯特" to "奥古斯特·冯·帕塞瓦尔",
        "肥恐龙" to "可畏",
        "饺子" to "埃塞克斯",
        "香饺" to "香格里拉",
        "μ赤城" to "赤城(μ兵装)",
        "μ大凤" to "大凤(μ兵装)",
        "μ光辉" to "光辉(μ兵装)",
        "彼得" to "彼得·史特拉塞",
        "黑飞龙" to "飞龙·META",
        "飞龙meta" to "飞龙·META",
        "黑方舟" to "皇家方舟·META",
        "方舟meta" to "皇家方舟·META",
        "黑苍龙" to "苍龙·META",
        "苍龙meta" to "苍龙·META",
        "方舟" to "皇家方舟",
        "狱友" to "皇家方舟",
        "黑飞鹰" to "飞鹰·META",
        "飞鹰meta" to "飞鹰·META",
        "妹妹" to "独角兽",
        "780" to "齐柏林伯爵",
        "齐柏林" to "齐柏林伯爵",
        "390" to "小齐柏林"
    ))

    @ValueDescription("其他别名")
    val ALIAS_OTHER_MAP : Map<String, String> by value(mapOf<String, String>(
        "举炮炮" to "阿贝克隆比",
        "奸商" to "明石",
        "牛牛" to "樫野",
        "u37" to "U-37",
        "u47" to "U-47",
        "u74" to "U-73",
        "u81" to "U-81",
        "u96" to "U-96",
        "u101" to "U-101",
        "u110" to "U-110",
        "u410" to "U-410",
        "u522" to "U-522",
        "u556" to "U-556",
        "u557" to "U-557",
        "u1206" to "U-1206",
        "霞doa" to "霞DOA",


        "一图榜" to "PVE用舰船综合性能强度榜",
        "舰娘一图榜" to "PVE用舰船综合性能强度榜",
        "强度榜" to "PVE用舰船综合性能强度榜",
        "PVE一图榜" to "PVE用舰船综合性能强度榜",
        "认知觉醒榜" to "认知觉醒推荐榜",
        "觉醒榜" to "认知觉醒推荐榜",
        "打捞表" to "井号打捞表",
        "兑换榜" to "兑换装备推荐榜",
        "商店榜" to "兑换装备推荐榜",
        "商店兑换榜" to "兑换装备推荐榜",
        "装备研发榜" to "装备研发推荐榜",
        "研发榜" to "装备研发推荐榜",
        "改造榜" to "改造舰船推荐榜",
        "萌新榜" to "萌新入坑推荐榜",
    ))

    @ValueDescription("和谐名")
    val ALIAS_JP_MAP : Map<String, String> by value(mapOf<String, String>(
        "樱" to "峰风",
        "榊" to "神风",
        "櫂" to "朝风",
        "橪" to "春风",
        "棡" to "松风",
        "樋" to "旗风",
        "椎" to "追风",
        "枳" to "夕凪",
        "松" to "睦月",
        "樟" to "如月",
        "楙" to "卯月",
        "杌" to "水无月",
        "橗" to "文月",
        "枨" to "长月",
        "檧" to "三日月",
        "桐" to "吹雪",
        "杉" to "白雪",
        "杨" to "初雪",
        "梧" to "深雪",
        "朴" to "浦波",
        "柚" to "绫波",
        "柿" to "敷波",
        "棪" to "曙",
        "槿" to "涟",
        "槟" to "潮",
        "枫" to "晓",
        "栀" to "响",
        "梓" to "雷",
        "柏" to "电",
        "梅" to "初春",
        "楉" to "若叶",
        "檨" to "初霜",
        "榎" to "有明",
        "棭" to "夕暮",
        "梿" to "白露",
        "栴" to "时雨",
        "椿" to "夕立",
        "菪" to "海风",
        "杣" to "山风",
        "茳" to "江风",
        "莨" to "凉风",
        "棹" to "朝潮",
        "荙" to "大潮",
        "樠" to "满潮",
        "栘" to "荒潮",
        "蕸" to "霞",
        "萩" to "阳炎",
        "蒲" to "不知火",
        "蓉" to "黑潮",
        "藮" to "亲潮",
        "菙" to "初风",
        "莲" to "雪风",
        "菡" to "天津风",
        "槆" to "浦风",
        "柉" to "矶风",
        "樇" to "滨风",
        "栭" to "谷风",
        "苓" to "野分",
        "萍" to "岚",
        "莵" to "萩风",
        "蒠" to "舞风",
        "茭" to "夕云",
        "荨" to "卷云",
        "枟" to "风云",
        "苌" to "长波",
        "棬" to "卷波",
        "棈" to "清波",
        "椛" to "秋月",
        "檚" to "初月",
        "栎" to "凉月",
        "枥" to "新月JP",
        "㮔" to "冬月",
        "桸" to "春月",
        "楛" to "宵月",
        "槾" to "满月",
        "榵" to "花月",
        "芒" to "岛风",
        "㭴" to "樫野",
        "豺" to "天龙",
        "豹" to "龙田",
        "熊" to "球磨",
        "狸" to "北上",
        "獾" to "大井",
        "貊" to "长良",
        "貉" to "五十铃",
        "㹨" to "由良",
        "猤" to "鬼怒",
        "貃" to "阿武隈",
        "狐" to "夕张",
        "貆" to "川内",
        "貎" to "神通",
        "豻" to "那珂",
        "豼" to "阿贺野",
        "貅" to "能代",
        "貁" to "矢矧",
        "淀" to "大淀",
        "狼" to "古鹰",
        "狌" to "加古",
        "犹" to "青叶",
        "猅" to "衣笠",
        "猨" to "最上",
        "狻" to "三隈",
        "狺" to "铃谷",
        "猁" to "熊野",
        "猑" to "利根",
        "狘" to "筑摩",
        "獌" to "妙高",
        "狏" to "那智",
        "狳" to "足柄",
        "犰" to "羽黑",
        "獒" to "高雄",
        "犬" to "爱宕",
        "犮" to "摩耶",
        "猋" to "鸟海",
        "鲤" to "金刚",
        "鲟" to "比叡",
        "鲑" to "榛名",
        "鳗" to "雾岛",
        "鳐" to "天城",
        "鲙" to "敷岛",
        "鲐" to "三笠",
        "魟" to "扶桑",
        "鲼" to "山城",
        "鳌" to "伊势",
        "螯" to "日向",
        "鲨" to "长门",
        "鲛" to "陆奥",
        "䲠" to "土佐",
        "鲣" to "纪伊",
        "鲪" to "骏河",
        "鲸" to "大和",
        "鳄" to "武藏",
        "凤" to "凤翔",
        "枭" to "龙骧",
        "鹞" to "祥凤",
        "鹳" to "瑞凤",
        "鹂" to "千岁",
        "鹃" to "千代田",
        "迖" to "大鲸",
        "鸗" to "龙凤",
        "鸱" to "飞鹰",
        "鸢" to "隼鹰",
        "凰" to "赤城",
        "鸾" to "加贺",
        "蛟" to "苍龙",
        "龙" to "飞龙",
        "鹬" to "翔鹤",
        "鹤" to "瑞鹤",
        "鹩" to "大凤",
        "靇" to "云龙",
        "鹖" to "葛城",
        "鵗" to "信浓",
        "鹫" to "白龙",
        "茗" to "明石",
        "峦" to "伊吹",
        "侌" to "出云",
        "苝" to "北风",
        "猉" to "吾妻",
        "玖" to "伊9",
        "十纱" to "伊13",
        "伊知梦" to "伊16",
        "衣玖" to "伊19",
        "双叶檎" to "伊25",
        "双叶梦" to "伊26",
        "冴诗" to "伊54",
        "冴梦" to "伊56",
        "冴矢" to "伊58",
        "六重" to "伊60",
        "伊吕波" to "伊168",
        "诗玖玲" to "伊490",

        "花园" to "新泽西",
        "卡莉永" to "提康德罗加",
        "树城" to "博伊西",
        "喷水鱼" to "射水鱼",
        "雾城" to "旧金山",
        "塞普拉斯" to "莫里森"
    ))


    @ValueDescription("驱逐炮")
    val ALIAS_DD_GUN_MAP : Map<String, String> by value(mapOf<String, String>(
        "单装127" to "127mm单装炮",
        "114" to "双联装114mm高平两用炮Mark_IV",
        "双联114" to "双联装114mm高平两用炮Mark_IV",
        "凯旋炮" to "138.6mm单装炮Mle1929",
        "金凯旋" to "138.6mm单装炮Mle1929",
        "空想炮" to "138.6mm单装炮Mle1929",
        "紫凯旋" to "138.6mm单装炮Mle1929T2",
        "76炮" to "76mm火炮T3",
        "金秋月" to "双联100mm98式高射炮T3",
        "秋月炮" to "双联100mm98式高射炮T3",
        "双联120" to "双联装120mm主炮T3",
        "双联120" to "双联装120mm主炮T3",
        "120高平" to "双联装120mm高平两用炮Mark_XIT0",
        "金120" to "双联装120mm高平两用炮Mark_XIT0",
        "127改" to "127mm连装炮改T0",
        "127连装" to "127mm连装炮改T0",
        "金高平" to "双联装127mm高平两用炮Mk12T3",
        "127高平" to "双联装127mm高平两用炮Mk12T3",
        "双联128" to "双联装128mmSKC41高平两用炮T3",
        "塔什干炮" to "B-13_双联装130mm主炮B-2LMT3",
        "b13" to "B-13_双联装130mm主炮B-2LMT3",
        "b2lm" to "B-13_双联装130mm主炮B-2LMT3",
        "紫130" to "B-130mm单装炮T3",
        "金穿甲" to "双联装128mmSKC41高平两用炮改T0",
        "穿甲驱逐炮" to "双联装128mmSKC41高平两用炮改T0",
        "金128" to "双联装128mmSKC41高平两用炮改T0",
        "128改" to "双联装128mmSKC41高平两用炮改T0",
        "沃克兰炮" to "138.6mm单装炮Mle1927T3"
    ))

    @ValueDescription("巡洋炮")
    val ALIAS_C_GUN_MAP : Map<String, String> by value(mapOf<String, String>(
        "贝爷炮" to "155mm三连装炮T3",
        "155" to "155mm三连装炮T3",
        "金155" to "155mm三连装炮T3",
        "155改" to "试作型155mm三连装炮改T0",
        "mk16" to "三联装152mm主炮Mk16T0",
        "牙签炮" to "单装SKC28式150mm主炮T3",
        "单装150" to "单装SKC28式150mm主炮T3",
        "针管炮" to "单装SKC28式150mm主炮T3",
        "双联152" to "双联装152mm主炮T3",
        "克爹炮" to "双联装TbtsKC36式150mm主炮T3",
        "海星炮" to "试作型三联装152mm主炮T0",
        "海王星炮" to "试作型三联装152mm主炮T0",
        "西雅图炮" to "试作型三联装152mm高平两用炮Mk17T0",
        "b38" to "B-38_三联装152mm主炮MK-5",
        "150改" to "试作型双联装SKC28式150mm主炮改T0",
        "巴尔的摩炮" to "三联装203mm主炮Mk15T0",
        "恰巴炮" to "B-38_三联装152mm主炮MK-5T3",
        "扎拉炮" to "双联203mm主炮Model1927T3",
        "欧根炮" to "双联装203mmSKC主炮T3",
        "罗恩炮" to "试作型三联装203mmSKC主炮T0",
        "路易炮" to "试作型三联装203mm舰炮T0",
        "阿尔及利亚炮" to "双联装203mm主炮Mle1931T3",
        "234" to "试作型三联装234mm主炮T0",
        "三期彩炮" to "试作型三联装234mm主炮T0",
        "德雷克炮" to "试作型三联装234mm主炮T0",
        "柴郡炮" to "试作型双联装234mm主炮T0",
        "吾妻炮" to "试作型三联装310mm主炮T0",
        "埃吉尔炮" to "试作型三联装305mmSKC39主炮(超巡用)T0",
        "屎壳郎炮" to "B-50_三联装305mm主炮MK-15T0",
        "喀琅施塔得炮" to "B-50_三联装305mm主炮MK-15T0"
    ))

    @ValueDescription("战列炮")
    val ALIAS_BB_GUN_MAP : Map<String, String> by value(mapOf<String, String>(
        "礼花炮" to "410mm连装炮(三式弹)T0",
        "烟花炮" to "410mm连装炮(三式弹)T0",
        "金410" to "410mm连装炮(三式弹)T0",
        "紫410" to "410mm连装炮T3",
        "410" to "410mm连装炮T3",
        "410改" to "410mm连装炮改T0",
        "283" to "三联283mmSKC34主炮T3",
        "嗑药炮" to "三联装381mm主炮Model1934T3",
        "mk6" to "三联装406mm主炮Mk6T3",
        "妹控6" to "三联装406mm主炮Mk6T3",
        "金406" to "三联装406mm主炮T3",
        "381改" to "双联装381mm主炮改T0",
        "大哥炮" to "四联装356mm主炮T3",
        "4356" to "四联装356mm主炮T3",
        "82炮" to "四联装380mm主炮Mle1935T3",
        "巴尔炮" to "四联装380mm主炮Mle1935T3",
        "黎塞留炮" to "四联装380mm主炮Mle1935T3",
        "出云炮" to "试作型410mm三连装炮T0",
        "君主炮" to "试作型三联装381mm主炮T0",
        "3381" to "试作型三联装381mm主炮T0",
        "三联381" to "试作型三联装381mm主炮T0",
        "大帝炮" to "试作型双联装406mmSKC主炮T0",
        "406skc" to "试作型双联装406mmSKC主炮T0",
        "佐治亚炮" to "试作型双联装457mm主炮MkAT0",
        "457" to "试作型双联装457mm主炮MkAT0",
        "二期彩炮" to "试作型双联装457mm主炮MkAT0",
        "关服炮" to "试作型双联装457mm主炮MkAT0",
        "彩炮" to "试作型双联装457mm主炮MkAT0",
        "香槟炮" to "试作型三联装406mm/50主炮T0",
        "奥丁炮" to "试作型三联装305mmSKC39主炮T0",
        "马可波罗炮" to "试作型三联装406mm主炮Model1940",
        "mk7" to "三联装406mm主炮Mk7T0"
    ))

    @ValueDescription("防空炮")
    val ALIAS_AIR_GUN_MAP : Map<String, String> by value(mapOf<String, String>(
        "八联砰砰" to "八联装40mm“砰砰”炮T3",
        "四联博福斯" to "四联40mm博福斯对空机炮T3",
        "六联博福斯" to "六联装40mm博福斯对空机炮T0",
        "staag" to "双联装40mm博福斯STAAGT0",
        "命中防空炮" to "双联装40mm博福斯STAAGT0",
        "双联博福斯" to "双联装40mm博福斯STAAGT0",
        "海兹梅耶" to "双联装40mm博福斯海兹梅耶T0",
        "架子鼓" to "双联装40mm博福斯海兹梅耶T0",
        "113" to "双联装113mm高射炮T3",
        "马桶圈" to "双联装113mm高射炮T3",
        "圆盘" to "双联装113mm高射炮T3",
        "双联113" to "双联装113mm高射炮T3",
        "垃圾桶" to "双联37mm高射炮Mle1936T0",
        "105" to "双联105mmSKC高炮T3",
        "垃圾桶盖" to "试作型五式40mm高射机关炮T0",
        "烟灰缸" to "试作型五式40mm高射机关炮T0",
        "八音盒" to "试作型五式40mm高射机关炮T0",
        "134" to "双联装134mm高炮T0",
        "炮击防空炮" to "双联装134mm高炮T0"
    ))

    @ValueDescription("鱼雷")
    val ALIAS_TORPEDO_MAP : Map<String, String> by value(mapOf<String, String>(
        "三联磁" to "三联装533mm磁性鱼雷T3",
        "彩五磁" to "五联装533mm磁性鱼雷T3",
        "彩雷" to "五联装533mm磁性鱼雷T3",
        "金五磁" to "五联装533mm磁性鱼雷T2",
        "533" to "五联装533mm鱼雷T3",
        "金五联" to "五联装533mm鱼雷T3",
        "金四联" to "四联装610mm鱼雷T3",
        "610" to "四联装610mm鱼雷T3",
        "四联磁" to "四联装533mm磁性鱼雷T3",
        "610改" to "四联装610mm鱼雷改T0",
        "一阳指" to "五联装533mm鱼雷Mark_IXT0",
        "彩610" to "五联装610mm鱼雷",
        "彩五联" to "五联装610mm鱼雷",

        "95雷" to "潜艇用95式纯氧鱼雷T3",
        "95改" to "潜艇用95式纯氧鱼雷改T0",
        "g7e" to "潜艇用G7e声导鱼雷T3",
        "g7a" to "潜艇用G7a鱼雷T3",
        "mark16" to "潜艇用Mark_16鱼雷T3",
        "mark28" to "潜艇用Mark_28鱼雷T0",
        "彼得雷" to "潜艇用Mark_20_S鱼雷-彼得T0",
        "96雷" to "潜艇用96式纯氧鱼雷T3",
        "菲里雷" to "潜艇用Mark_12鱼雷-菲里T0",

        "上游1" to "上游-1"
    ))

    @ValueDescription("飞机")
    val ALIAS_PLANE_MAP : Map<String, String> by value(mapOf<String, String>(
        "bf" to "试作舰载型BF-109GT0",
        "火箭弹" to "试作舰载型BF-109GT0",
        "地狱猫" to "F6F地狱猫T3",
        "紫电" to "紫电改二T0",
        "虎猫" to "F7F虎猫T0",
        "金水牛" to "F2A水牛(萨奇队)T0",
        "萨奇队" to "F2A水牛(萨奇队)T0",
        "金海盗" to "F4U(VF-17“海盗”中队)T0",
        "me155" to "Me-155A舰载战斗机T3",
        "me" to "Me-155A舰载战斗机T3",
        "天箭" to "XF5F天箭T0",
        "天剑" to "XF5F天箭T0",
        "零战" to "零战五二型T3",
        "052" to "零战五二型T3",
        "天雷" to "试作舰载型天雷T0",
        "sb3c" to "实验型XSB3C-1T0",
        "sb2c" to "SB2C地狱俯冲者T3",
        "地狱俯冲" to "SB2C地狱俯冲者T3",
        "彗星一二" to "彗星一二型甲T0",
        "彗星改" to "彗星一二型甲T0",
        "毁灭者" to "BTD-1毁灭者T3",
        "btd" to "BTD-1毁灭者T3",
        "麦辣鸡" to "SBD无畏(麦克拉斯基队)T0",
        "sbd" to "SBD无畏(麦克拉斯基队)T0",
        "831" to "梭鱼(831中队)T0",
        "斯图卡" to "Ju-87_D-4T0",
        "ju" to "Ju-87_D-4T0",
        "ju87" to "Ju-87_D-4T0",
        "彩云" to "试作型彩云(舰攻型)T0",
        "818" to "剑鱼(818中队)T0",
        "剑鱼818" to "剑鱼(818中队)T0",
        "天空海盗" to "XTB2D-1天空海盗T0",
        "tbm" to "TBM复仇者(VT-18中队)T0"
    ))

    @ValueDescription("设备")
    val ALIAS_DEVICE_MAP : Map<String, String> by value(mapOf<String, String>(
        "苍蝇拍" to "FuMO_25T0",
        "烧烤架" to "FuMO_25T0",
        "烤肉架" to "FuMO_25T0",
        "条约" to "华盛顿海军条约T0",
        "旗语" to "纳尔逊的旗语T0",
        "奖章" to "开拓者奖章T0",
        "舞裙" to "星云舞裙T0",
        "裙" to "星云舞裙T0",
        "舞鞋" to "引力舞鞋T0",
        "鞋" to "引力舞鞋T0",
        "艇壳" to "艇壳改良设计案T2",
        "船壳" to "艇壳改良设计案T2",
        "壳" to "艇壳改良设计案T2",
        "精英损管" to "白鹰精英损管T0",
        "白鹰损管" to "白鹰精英损管T0",
        "损管" to "白鹰精英损管T0",
        "燃油" to "100/150号航空燃油T0",
        "sg" to "SG雷达T3",
        "钢板" to "VH装甲钢板T0",
        "白弹" to "一式穿甲弹T0",
        "白蛋" to "一式穿甲弹T0",
        "黑蛋" to "超重弹T0",
        "黑弹" to "超重弹T0",
        "彩棒" to "九三式纯氧鱼雷T3",
        "黑棒" to "533mm磁性鱼雷T3",
        "小海狸" to "小海狸中队队徽T0",
        "信标" to "归航信标T0",
        "进气管" to "改良型水下进气管T0",
        "电池" to "改良蓄电池阵列T0",
        "蓄电池" to "改良蓄电池阵列T0",
        "锅炉" to "改良锅炉T3",
        "猫爪" to "治愈系猫爪T0",
        "液压" to "液压弹射装置T3",
        "金液压" to "液压弹射装置T3",
        "紫液压" to "液压弹射装置T2",
        "紫火控" to "火控雷达T3",
        "饭盒" to "维修工具T3",
        "维修箱" to "维修工具T3",
        "油箱" to "航空副油箱T3",
        "金维修" to "舰艇维修设备T3",
        "弹链" to "链式装弹机T3",
        "鱼雷板" to "防鱼雷隔舱T3",
        "金防空" to "高性能对空雷达T0",
        "金雷达" to "高性能对空雷达T0",
        "金防空雷达" to "高性能对空雷达T0",
        "金火控" to "高性能火控雷达T0",
        "火控" to "高性能火控雷达T0",
        "舵机" to "高性能舵机T0",
        "深投" to "改良深弹投射器T3"
    ))

    val ALIAS_MAP : HashMap<String, String> = hashMapOf()

    init {
        val mapList = listOf(
            ALIAS_DD_MAP,
            ALIAS_CL_MAP,
            ALIAS_CA_MAP,
            ALIAS_CV_MAP,
            ALIAS_BB_MAP,
            ALIAS_OTHER_MAP,
            ALIAS_JP_MAP,
            ALIAS_DD_GUN_MAP,
            ALIAS_C_GUN_MAP,
            ALIAS_BB_GUN_MAP,
            ALIAS_AIR_GUN_MAP,
            ALIAS_PLANE_MAP,
            ALIAS_TORPEDO_MAP,
            ALIAS_DEVICE_MAP
        )
        mapList.forEach {
            ALIAS_MAP.putAll(it)
        }
    }
}