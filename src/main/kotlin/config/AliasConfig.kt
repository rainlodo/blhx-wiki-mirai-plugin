package org.iris.wiki.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value
import java.util.HashMap


object AliasConfig : AutoSavePluginConfig("AliasConfig") {

    @ValueDescription("别名，指令自动转小写，别名中的英文请使用小写字母")
    val ALIAS_USER_MAP : Map<String, String> by value(mapOf<String, String>(

    ))
    @ValueDescription("驱逐别名")
    val ALIAS_DD_MAP : Map<String, String> by value(mapOf(
        "彩布里" to "特装型布里MKIII",
        "金布里" to "试作型布里MKII",
        "布里" to "泛用型布里",
        "紫布里" to "泛用型布里",
        "举高高" to "埃尔德里奇",
        "艾伦" to "艾伦·萨姆纳",
        "灯皇" to "布里斯托尔",
        "提灯" to "布里斯托尔",
        "赵四" to "尼古拉斯",
        "波特" to "史蒂芬·波特",
        "加倍林" to "标枪",
        "佳贝林" to "标枪",
        "言力华" to "标枪",
        "poi" to "夕立",
        "狗子" to "夕立",
        "雪亲王" to "雪风",
        "nanoda" to "雪风",
        "岛皇" to "岛风",
        "新月jp" to "新月JP",
        "臭鼬" to "长波",
        "z404" to "z23",
        "同志酱" to "塔什干",
        "铜治酱" to "塔什干",
        "μ塔什干" to "塔什干(μ兵装)",
        "u塔什干" to "塔什干(μ兵装)",
        "μ恶毒" to "恶毒(μ兵装)",
        "u恶毒" to "恶毒(μ兵装)",
        "庞贝" to "庞培·马格诺",
        "庞培" to "庞培·马格诺",
        "玛丽罗斯" to "玛莉萝丝"
    ))

    @ValueDescription("轻巡别名")
    val ALIAS_CL_MAP : Map<String, String> by value(mapOf(
        "凤凰" to "菲尼克斯",
        "海妈" to "海伦娜",
        "檀香山" to "火奴鲁鲁",
        "圣姨" to "圣路易斯",
        "亚棍" to "亚特兰大",
        "金坷垃" to "圣地亚哥",
        "彩坷垃" to "圣地亚哥",
        "克爹" to "克利夫兰",
        "二叔" to "哥伦比亚",
        "懵逼" to "蒙彼利埃",
        "蒙彼" to "蒙彼利埃",
        "蒙蔽" to "蒙彼利埃",
        "三叔" to "蒙彼利埃",
        "四叔" to "丹佛",
        "六叔" to "伯明翰",
        "十二叔" to "比洛克西",
        "屑雅图" to "西雅图",
        "铁屁股" to "西雅图",
        "小海妈" to "小海伦娜",
        "小克爹" to "小克利夫兰",
        "小金坷垃" to "小圣地亚哥",
        "μ克爹" to "克利夫兰(μ兵装)",
        "重庆" to "欧若拉",
        "谢菲" to "谢菲尔德",
        "贝爷" to "贝尔法斯特",
        "贝法" to "贝尔法斯特",
        "杰哥" to "确捷",
        "贝罗娜" to "司战女神",
        "皇家企业" to "进取",
        "μ黛朵" to "黛朵(μ兵装)",
        "能带" to "能代",
        "500" to "五十铃",
        "双子" to "埃姆登",
        "amd" to "埃姆登",
        "群主" to "美因茨",
        "水星" to "水星纪念",
        "共产国际" to "水星纪念",
        "阿芙" to "阿芙乐尔",
        "恰巴" to "恰巴耶夫",
        "夏伯阳" to "恰巴耶夫",
        "毛马哈" to "摩尔曼斯克",
        "阿布" to "阿布鲁齐公爵",
        "贞德" to "圣女贞德",
        "黑海伦娜" to "海伦娜·META",
        "黑妈" to "海伦娜·META",
        "黑海妈" to "海伦娜·META",
        "海伦娜meta" to "海伦娜·META",
        "海妈meta" to "海伦娜·META"
    ))

    @ValueDescription("重巡别名")
    val ALIAS_CA_MAP : Map<String, String> by value(mapOf(
        "波特彩" to "波特兰",
        "印第" to "印第安纳波利斯",
        "印地" to "印第安纳波利斯",
        "印第酱" to "印第安纳波利斯",
        "印地酱" to "印第安纳波利斯",
        "印第安那波利斯" to "印第安纳波利斯",
        "印地安那波利斯" to "印第安纳波利斯",
        "印地安纳波利斯" to "印第安纳波利斯",
        "鸡腿堡" to "新奥尔良",
        "小明" to "明尼阿波利斯",
        "爆地魔" to "巴尔的摩",
        "布莱" to "布莱默顿",
        "布莱莱" to "布莱默顿",
        "μ巴尔的摩" to "巴尔的摩(μ兵装)",
        "μ爆地魔" to "巴尔的摩(μ兵装)",
        "安宝" to "安克雷奇",

        "猫猫" to "柴郡",
        "小猫猫" to "小柴郡",
        "德宝" to "德雷克",
        "德皇" to "德雷克",
        "吕布" to "德雷克",
        "林社长" to "伦敦",

        "大狗" to "爱宕",

        "希佩尔" to "希佩尔海军上将",
        "μ希佩尔" to "希佩尔海军上将(μ兵装)",
        "吃手手" to "欧根亲王",
        "欧根" to "欧根亲王",
        "斯佩" to "斯佩伯爵海军上将",
        "斯佩伯爵" to "斯佩伯爵海军上将",
        "μ罗恩" to "罗恩(μ兵装)",
        "海亲王" to "海因里希亲王",
        "阿亲王" to "阿达尔伯特亲王",

        "6194" to "路易九世",
        "路易" to "路易九世",

        "57" to "吾妻",
        "我老婆" to "吾妻",
        "艾吉尔" to "埃吉尔",
        "爱鸡儿" to "埃吉尔",
        "喀琅" to "喀琅施塔得",
        "施塔得" to "喀琅施塔得"
    ))

    @ValueDescription("战列别名")
    val ALIAS_BB_MAP : Map<String, String> by value(mapOf(

        "河马" to "俄克拉荷马",
        "崩姐" to "宾夕法尼亚",
        "泪姐" to "亚利桑那",
        "北卡" to "北卡罗来纳",
        "花生" to "华盛顿",
        "南胖" to "南达科他",
        "麻省" to "马萨诸塞",
        "albm" to "阿拉巴马",
        "黑龙" to "新泽西",
        "格鲁吉亚" to "佐治亚",
        "几把兔" to "新泽西",

        "saber" to "声望",
        "老纳" to "纳尔逊",
        "老罗" to "罗德尼",
        "乔五" to "英王乔治五世",
        "大哥大" to "英王乔治五世",
        "大哥" to "威尔士亲王",
        "二哥" to "约克公爵",
        "小饼干" to "豪",
        "伊丽莎白" to "伊丽莎白女王",
        "傻白" to "伊丽莎白女王",
        "老女士" to "厌战",

        "大先辈" to "三笠",
        "大仙贝" to "三笠",
        "长萌" to "长门",
        "长萌萌" to "长门",
        "土特劣大佐" to "土佐",
        "小甜橙" to "小天城",
        "火烧云" to "出云",

        "沙恩" to "沙恩霍斯特",
        "独眼龙" to "沙恩霍斯特",
        "格奈" to "格奈森瑙",
        "波斯猫" to "俾斯麦",
        "北宅" to "提尔比茨",
        "胡滕" to "乌尔里希·冯·胡滕",
        "胡腾" to "乌尔里希·冯·胡滕",
        "大帝" to "腓特烈大帝",

        "苏白" to "苏维埃贝拉罗斯",
        "苏俄" to "苏维埃罗西亚",
        "罗西亚" to "苏维埃罗西亚",
        "君权" to "阿尔汉格尔斯克",
        "十月革命" to "甘古特",

        "黎姐" to "黎塞留",
        "槟槟" to "香槟",
        "jb" to "让·巴尔",
        "大加加" to "加斯科涅",
        "加μ" to "加斯科涅(μ兵装)",

        "凯撒" to "朱利奥·凯撒",
        "加富尔" to "加富尔伯爵",
        "绿托" to "利托里奥",
        "维托里奥" to "维托里奥·维内托",
        "维内托" to "维托里奥·维内托",
        "vv" to "维托里奥·维内托",
        "菠萝" to "马可·波罗",

        "扶桑META" to "扶桑·META",
        "黑扶桑" to "扶桑·META",
        "黑山城" to "山城·META",
        "山城meta" to "山城·META",
        "反击meta" to "反击·META",
        "黑反击" to "反击·META",
        "黑格奈森瑙" to "格奈森瑙·META",
        "黑格奈" to "格奈森瑙·META",
        "格奈森瑙meta" to "格奈森瑙·META",
        "格奈meta" to "格奈森瑙·META",
        "黑沙恩霍斯特" to "沙恩霍斯特·META",
        "黑沙恩" to "沙恩霍斯特·META",
        "沙恩霍斯特meta" to "沙恩霍斯特·META",
        "沙恩meta" to "沙恩霍斯特·META"
    ))

    @ValueDescription("航母别名")
    val ALIAS_CV_MAP : Map<String, String> by value(mapOf(
        "列太太" to "列克星敦",
        "小加加" to "萨拉托加",
        "大E" to "企业",
        "饺子" to "埃塞克斯",
        "香饺" to "香格里拉",
        "提饺" to "提康德罗加",
        "卡饺" to "提康德罗加",
        "邦饺" to "邦克山",
        "邦邦山" to "邦克山",
        "碉堡山" to "邦克山",
        "卡萨" to "卡萨布兰卡",

        "妹妹" to "独角兽",
        "神仙座" to "英仙座",
        "方舟" to "皇家方舟",
        "狱友" to "皇家方舟",
        "肥恐龙" to "可畏",

        "780" to "齐柏林伯爵",
        "齐柏林" to "齐柏林伯爵",
        "390" to "小齐柏林",
        "奥古斯特" to "奥古斯特·冯·帕塞瓦尔",
        "奥古" to "奥古斯特·冯·帕塞瓦尔",
        "彼得" to "彼得·史特拉塞",

        "μ赤城" to "赤城(μ兵装)",
        "μ大凤" to "大凤(μ兵装)",
        "μ光辉" to "光辉(μ兵装)",
        "黑飞龙" to "飞龙·META",
        "飞龙meta" to "飞龙·META",
        "黑方舟" to "皇家方舟·META",
        "方舟meta" to "皇家方舟·META",
        "黑苍龙" to "苍龙·META",
        "苍龙meta" to "苍龙·META",
        "黑飞鹰" to "飞鹰·META",
        "飞鹰meta" to "飞鹰·META",
        "黑隼鹰" to "隼鹰·META",
        "隼鹰meta" to "隼鹰·META"
    ))

    @ValueDescription("其他别名")
    val ALIAS_OTHER_MAP : Map<String, String> by value(mapOf(
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
        "舰娘榜" to "PVE用舰船综合性能强度榜",
        "强度榜" to "认知觉醒推荐榜",
        "PVE一图榜" to "PVE用舰船综合性能强度榜",
        "认知觉醒榜" to "认知觉醒推荐榜",
        "觉醒榜" to "认知觉醒推荐榜",
        "打捞表" to "井号打捞表",
        "打捞榜" to "井号打捞表",
        "兑换榜" to "兑换装备推荐榜",
        "商店榜" to "兑换装备推荐榜",
        "商店兑换榜" to "兑换装备推荐榜",
        "装备研发榜" to "装备研发推荐榜",
        "研发榜" to "装备研发推荐榜",
        "改造榜" to "改造舰船推荐榜",
        "萌新榜" to "萌新入坑推荐榜",
        "跨队榜" to "跨队舰船推荐榜"
    ))

    @ValueDescription("和谐名")
    val ALIAS_JP_MAP : Map<String, String> by value(mapOf(
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
        "塞普拉斯" to "莫里森",

        "滨江" to "哈尔滨"
    ))


    @ValueDescription("驱逐炮")
    val ALIAS_DD_GUN_MAP : Map<String, String> by value(mapOf(
        "金高平" to "双联装127mm高平两用炮Mk12T3",
        "127高平" to "双联装127mm高平两用炮Mk12T3",
        "76炮" to "76mm火炮T3",
        "114" to "双联装114mm高平两用炮Mark_IV",
        "双联114" to "双联装114mm高平两用炮Mark_IV",
        "紫双120" to "双联装120mm主炮T3",
        "120高平" to "双联装120mm高平两用炮Mark_XIT0",
        "金120" to "双联装120mm高平两用炮Mark_XIT0",
        "金秋月" to "双联100mm98式高射炮T3",
        "秋月炮" to "双联100mm98式高射炮T3",
        "双联128" to "双联装128mmSKC41高平两用炮T3",
        "金128" to "双联装128mmSKC41高平两用炮改T0",
        "128改" to "双联装128mmSKC41高平两用炮改T0",
        "庞培炮" to "双联装135mm主炮Model1938T3",
        "沃克兰炮" to "138.6mm单装炮Mle1927T3",
        "凯旋炮" to "138.6mm单装炮Mle1929",
        "金凯旋" to "138.6mm单装炮Mle1929",
        "空想炮" to "138.6mm单装炮Mle1929",
        "紫凯旋" to "138.6mm单装炮Mle1929T2",
        "塔什干炮" to "B-13_双联装130mm主炮B-2LMT3",
        "b13" to "B-13_双联装130mm主炮B-2LMT3",
        "b2lm" to "B-13_双联装130mm主炮B-2LMT3",
        "哈尔滨炮" to "试作型双联装130mm主炮Model1936T0",
        "滨江炮" to "试作型双联装130mm主炮Model1936T0",
        "紫130" to "B-130mm单装炮T3",
        "社会主义130" to "B-130mm单装炮T3"
    ))

    @ValueDescription("巡洋炮")
    val ALIAS_C_GUN_MAP : Map<String, String> by value(mapOf(
        "西雅图炮" to "试作型三联装152mm高平两用炮Mk17T0",
        "巴尔的摩炮" to "三联装203mm主炮Mk15T0",
        "真克爹炮" to "三联装152mm主炮Mk16T0",
        "法戈炮" to "三联装152mm主炮Mk16T0",
        "海星炮" to "试作型三联装152mm主炮T0",
        "海王星炮" to "试作型三联装152mm主炮T0",
        "普利茅斯炮" to "试作型四联装152mm主炮T0",
        "4152" to "试作型四联装152mm主炮T0",
        "3234" to "试作型三联装234mm主炮T0",
        "三期彩炮" to "试作型三联装234mm主炮T0",
        "德雷克炮" to "试作型三联装234mm主炮T0",
        "方天画戟" to "试作型三联装234mm主炮T0",
        "2234" to "试作型双联装234mm主炮T0",
        "柴郡炮" to "试作型双联装234mm主炮T0",
        "贝爷炮" to "155mm三连装炮T3",
        "最上炮" to "155mm三连装炮T3",
        "155改" to "试作型155mm三连装炮改T0",
        "吾妻炮" to "试作型三联装310mm主炮T0",
        "单装150" to "单装SKC28式150mm主炮T3",
        "针管炮" to "单装SKC28式150mm主炮T3",
        "克爹炮" to "双联装TbtsKC36式150mm主炮T3",
        "尼米炮" to "双联装TbtsKC36式150mm主炮T3",
        "z23炮" to "双联装TbtsKC36式150mm主炮T3",
        "150改" to "试作型双联装SKC28式150mm主炮改T0",
        "欧根炮" to "双联装203mmSKC主炮T3",
        "罗恩炮" to "试作型三联装203mmSKC主炮T0",
        "前排283" to "三联283mmSKC28主炮T3",
        "埃吉尔炮" to "试作型三联装305mmSKC39主炮(超巡用)T0",
        "阿布炮" to "三联装152mm主炮Model1934T3",
        "恰巴炮" to "B-38_三联装152mm主炮MK-5T3",
        "B38" to "B-38_三联装152mm主炮MK-5T3",
        "扎拉炮" to "双联203mm主炮Model1927T3",
        "路易炮" to "试作型三联装203mm舰炮T0",
        "布雷斯特炮" to "试作型四联装330mm主炮Mle1931(超巡用)T0",
        "阿尔炮" to "双联装203mm主炮Mle1931T3",
        "喀琅施塔得炮" to "B-50_三联装305mm主炮MK-15T0",
        "喀琅炮" to "B-50_三联装305mm主炮MK-15T0",
        "施塔得炮" to "B-50_三联装305mm主炮MK-15T0",
        "B50" to "B-50_三联装305mm主炮MK-15T0",
        "b50" to "B-50_三联装305mm主炮MK-15T0"
    ))

    @ValueDescription("战列炮")
    val ALIAS_BB_GUN_MAP : Map<String, String> by value(mapOf(
        "mk6" to "三联装406mm主炮Mk6T3",
        "妹控6" to "三联装406mm主炮Mk6T3",
        "mk7" to "三联装406mm主炮Mk7T0",
        "佐治亚炮" to "试作型双联装457mm主炮MkAT0",
        "457" to "试作型双联装457mm主炮MkAT0",
        "二期彩炮" to "试作型双联装457mm主炮MkAT0",
        "关服炮" to "试作型双联装457mm主炮MkAT0",
        "mk4" to "试作型双联装406mm主炮Mk4T0",
        "麻省炮" to "三联装406mm主炮T3",
        "马萨炮" to "三联装406mm主炮T3",
        "381改" to "双联装381mm主炮改T0",
        "大哥炮" to "四联装356mm主炮T3",
        "4356" to "四联装356mm主炮T3",
        "君主炮" to "试作型三联装381mm主炮T0",
        "3381" to "试作型三联装381mm主炮T0",
        "三联381" to "试作型三联装381mm主炮T0",
        "礼花炮" to "410mm连装炮(三式弹)T0",
        "烟花炮" to "410mm连装炮(三式弹)T0",
        "金410" to "410mm连装炮(三式弹)T0",
        "紫410" to "410mm连装炮T3",
        "410" to "410mm连装炮T3",
        "410改" to "410mm连装炮改T0",
        "出云炮" to "试作型410mm三连装炮T0",
        "后排283" to "三联283mmSKC34主炮T3",
        "大帝炮" to "试作型双联装406mmSKC主炮T0",
        "406skc" to "试作型双联装406mmSKC主炮T0",
        "奥丁炮" to "试作型三联装305mmSKC39主炮T0",
        "俾斯麦炮" to "双联380mmSKC主炮T3",
        "嗑药炮" to "三联装381mm主炮Model1934T3",
        "马可炮" to "试作型三联装406mm主炮Model1940T0",
        "82炮" to "四联装380mm主炮Mle1935T3",
        "巴尔炮" to "四联装380mm主炮Mle1935T3",
        "黎塞留炮" to "四联装380mm主炮Mle1935T3",
        "香槟炮" to "试作型三联装406mm/50主炮T0"
    ))

    @ValueDescription("防空炮")
    val ALIAS_AIR_GUN_MAP : Map<String, String> by value(mapOf(
        "双76" to "双联装76mmRF火炮Mk27T0",
        "四联博福斯" to "四联40mm博福斯对空机炮T3",
        "八联砰砰" to "八联装40mm“砰砰”炮T3",
        "金八砰" to "八联装40mm“砰砰”炮T3",
        "六博" to "六联装40mm博福斯对空机炮T0",
        "六联博福斯" to "六联装40mm博福斯对空机炮T0",
        "113" to "双联装113mm高射炮T3",
        "马桶圈" to "双联装113mm高射炮T3",
        "圆盘" to "双联装113mm高射炮T3",
        "双联113" to "双联装113mm高射炮T3",
        "134" to "双联装134mm高炮T0",
        "炮击防空炮" to "双联装134mm高炮T0",
        "staag" to "双联装40mm博福斯STAAGT0",
        "命中防空炮" to "双联装40mm博福斯STAAGT0",
        "双联博福斯" to "双联装40mm博福斯STAAGT0",
        "海兹梅耶" to "双联装40mm博福斯海兹梅耶T0",
        "架子鼓" to "双联装40mm博福斯海兹梅耶T0",
        "九六式暴风" to "九六式25mm三连装暴风避盾机炮T0",
        "烟灰缸" to "试作型五式40mm高射机关炮T0",
        "八音盒" to "试作型五式40mm高射机关炮T0",
        "垃圾桶" to "双联37mm高射炮Mle1936T0",
        "105" to "双联105mmSKC高炮T3",
        "105改" to "双联105mmSKC高炮改进型T0"
    ))

    @ValueDescription("鱼雷")
    val ALIAS_TORPEDO_MAP : Map<String, String> by value(mapOf(
        "三联磁" to "三联装533mm磁性鱼雷T3",
        "紫三磁" to "三联装533mm磁性鱼雷T3",
        "金四磁" to "四联装533mm磁性鱼雷T3",
        "四联磁" to "四联装533mm磁性鱼雷T3",
        "彩五磁" to "五联装533mm磁性鱼雷T3",
        "彩雷" to "五联装533mm磁性鱼雷T3",
        "金五磁" to "五联装533mm磁性鱼雷T2",
        "金五联" to "五联装533mm鱼雷T3",
        "610" to "四联装610mm鱼雷T3",
        "610改" to "四联装610mm鱼雷改T0",
        "彩610" to "五联装610mm鱼雷",
        "一阳指" to "五联装533mm鱼雷Mark_IXT0",
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
    val ALIAS_PLANE_MAP : Map<String, String> by value(mapOf(
        "地狱猫" to "F6F地狱猫T3",
        "虎猫" to "F7F虎猫T0",
        "熊猫" to "F8F熊猫T0",
        "水牛" to "F2A水牛(萨奇队)T0",
        "萨奇队" to "F2A水牛(萨奇队)T0",
        "金海盗" to "F4U(VF-17“海盗”中队)T0",
        "紫海盗" to "F4U海盗T3",
        "天箭" to "XF5F天箭T0",
        "天剑" to "XF5F天箭T0",
        "sb3c" to "实验型XSB3C-1T0",
        "sb2c" to "SB2C地狱俯冲者T3",
        "地狱俯冲" to "SB2C地狱俯冲者T3",
        "金毁灭" to "BTD-1毁灭者T3",
        "btd" to "BTD-1毁灭者T3",
        "麦辣鸡" to "SBD无畏(麦克拉斯基队)T0",
        "天空海盗" to "XTB2D-1天空海盗T0",
        "tbm" to "TBM复仇者(VT-18中队)T0",
        "831" to "梭鱼(831中队)T0",
        "818" to "剑鱼(818中队)T0",
        "剑鱼818" to "剑鱼(818中队)T0",
        "紫电" to "紫电改二T0",
        "彗星一二甲" to "彗星一二型甲T0",
        "12甲" to "彗星一二型甲T0",
        "052" to "零战五二型T3",
        "零战" to "零战五二型T3",
        "天雷" to "试作舰载型天雷T0",
        "彩云" to "试作型彩云(舰攻型)T0",
        "bf" to "试作舰载型BF-109GT0",
        "火箭弹" to "试作舰载型BF-109GT0",
        "me155" to "Me-155A舰载战斗机T3",
        "me" to "Me-155A舰载战斗机T3",
        "fw" to "试作型舰载FW-190_A-5T0",
        "百舌鸟" to "试作型舰载FW-190_A-5T0",
        "斯图卡" to "Ju-87_D-4T0",
        "ju" to "Ju-87_D-4T0",
        "ju87" to "Ju-87_D-4T0",
        "d790" to "D.790T0",
        "br810" to "BR.810T0",
        "br" to "BR.810T0"
    ))

    @ValueDescription("设备")
    val ALIAS_DEVICE_MAP : Map<String, String> by value(mapOf(
        "苍蝇拍" to "FuMO_25T0",
        "烧烤架" to "FuMO_25T0",
        "烤肉架" to "FuMO_25T0",
        "条约" to "华盛顿海军条约T0",
        "旗语" to "纳尔逊的旗语T0",
        "奖章" to "开拓者奖章T0",
        "舞裙" to "星云舞裙T0",
        "舞鞋" to "引力舞鞋T0",
        "5速鞋" to "引力舞鞋T0",
        "艇壳" to "艇壳改良设计案T2",
        "船壳" to "艇壳改良设计案T2",
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
        "彩雷棒" to "九三式纯氧鱼雷T3",
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
        "金舵机" to "高性能舵机T0",
        "重巡快乐机" to "高性能舵机T0",
        "深投" to "改良深弹投射器T3",
        "紫深投" to "改良深弹投射器T3",
        "珍珠" to "珍珠之泪T0",
        "羽毛" to "天使之羽T0",
        "彩火控" to "海军部火控台T0",
        "黄蛋" to "6CRH穿甲弹T0",
        "黄弹" to "6CRH穿甲弹T0"
    ))

    @ValueDescription("专武")
    val ALIAS_AUGMENT_MAP : Map<String, String> by value(mapOf(
        "贝法专武" to "波涛与优雅的午后T0",
        "贝尔法斯特专武" to "波涛与优雅的午后T0",
        "伊58专武" to "探测平衡装置T0",
        "亚利桑那专武" to "不再哭泣T0",
        "泪姐专武" to "不再哭泣T0",
        "欧根专武" to "幸运舰的“惩罚”T0",
        "欧根亲王专武" to "幸运舰的“惩罚”T0",
        "拉菲专武" to "勇敢的美梦之枕T0",
        "光荣专武" to "准时的怀表T0",
        "让巴尔专武" to "护教骑士战旗T0"
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
            ALIAS_DEVICE_MAP,
            ALIAS_AUGMENT_MAP
        )
        mapList.forEach {
            ALIAS_MAP.putAll(it)
        }
    }

}