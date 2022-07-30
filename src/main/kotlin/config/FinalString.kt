package org.iris.wiki.config

val SEARCH_URL = "https://wiki.biligame.com/blhx/index.php?search="


val BOAT_UPDATE = "改造"
val BOAT_NO_UPDATA = "还没有改造呢~"


val MESSAGE_HELP = "指令格式如下：\n" +
    "wiki [舰船]/[装备]\n" +
    "wiki [舰船] 皮肤/配装/出处/评价/科技点/[语音]\n" +
    "wiki [装备] 出处\n" +
    "wiki 大建 轻池/特池/重池/[活动池]\n" +
    "wiki [榜单]\n" +
    "猜老婆/舰娘\n" +
    "装备名中的空格用_代替~\n\n" +
    "bug请在https://gitee.com/arisaka-iris/blhx-wiki-mirai-plugin/issues反馈"

val MESSAGE_ERROR = "输入指令有误~\n" +
    "请输入 wiki 查看指令列表\n\n" +
    "bug请在https://gitee.com/arisaka-iris/blhx-wiki-mirai-plugin/issues反馈"

val MESSAGE_SEARCH = "没有对应的词条但是找到了以下相关内容，可以尝试搜索下面的词条~\n" +
    "(〃'▽'〃)\n"

val MESSAGE_NO_RESULT = "没有查询到结果呢~\n" +
    "(〃'▽'〃)\n" +
    "输入 wiki 可以查看指令列表喵"

val MESSAGE_PARSE_ERROR = "页面解析有误，暂不支持此种类页面解析~\n" +
    "(｡•́︿•̀｡)\n" +
    "可以直接点击下方链接打开页面\n"


val EQUIP_LIST = listOf(
    "五联装533mm鱼雷",
    "四联装533mm鱼雷",
    "三联装533mm鱼雷",
    "12.7mm防空机枪",
    "星云舞裙",
    "引力舞鞋",
    "闪耀之歌",
    "活力之歌",
    "炽烈之歌",
    "华盛顿海军条约",
    "开拓者奖章",
    "改良声呐",
    "液压弹射装置",
    "舰艇维修设备",
    "高压氧气瓶",
    "机密文件·极地海峡",
    "侦察报告·纽约近海",
    "基础声呐",
    "维修工具",
    "陀螺仪",
    "链式装弹机",
    "航空副油箱",
    "燃油滤清器",
    "改良锅炉",
    "防鱼雷隔舱",
    "灭火器",
    "海军迷彩",
    "液压舵机",
    "电动扬弹机",
    "双联装127mm高平两用炮mk12",
    "双联装127mm副炮",
    "127mm单装炮",
    "127mm单装炮早期型",
    "76mm火炮",
    "试作型三联装152mm高平两用炮mk17",
    "三联装152mm主炮mk16",
    "三联装152mm主炮(白鹰)",
    "双联152mm主炮mk15",
    "双联152mm主炮",
    "三联装203mm主炮mk15",
    "三联装203mm主炮改进型",
    "三联装203mm主炮mk13",
    "三联装203mm主炮",
    "试作型双联装457mm主炮mka",
    "三联装406mm主炮mk7",
    "试作型双联装406mm主炮mk4",
    "试作型三联装406mm主炮mkd",
    "三联装406mm主炮mk2",
    "双联装406mm主炮mk8",
    "试作型四联装356mm主炮mkb",
    "三联装406mm主炮mk6",
    "双联装406mm主炮mk5",
    "双联装406mm主炮mk1",
    "三联装356mm主炮",
    "五联装533mm鱼雷mk17",
    "四联装533mm鱼雷mk17",
    "三联装533mm鱼雷mk17",
    "潜艇用mark_28鱼雷",
    "潜艇用mark_16鱼雷",
    "潜艇用mark_18鱼雷",
    "潜艇用mark_14鱼雷",
    "双联装76mmrf火炮mk27",
    "四联40mm博福斯对空机炮",
    "四联装20mm厄利孔高射炮mk15",
    "76mm高射炮改进型",
    "76mm高射炮(白鹰)",
    "双联40mm博福斯对空机炮",
    "四联装28mm“芝加哥钢琴”",
    "双管20mm厄利孔高射炮",
    "20mm厄利孔高射炮",
    "f8f熊猫",
    "f7f虎猫",
    "f4u(vf-17“海盗”中队)",
    "f2a水牛(萨奇队)",
    "f6f地狱猫",
    "xf5f天箭",
    "试作型xf2a-4水牛",
    "f4u海盗",
    "f4f野猫",
    "f2a水牛",
    "xtb2d-1天空海盗",
    "tbm复仇者(vt-18中队)",
    "tbd蹂躏者(vt-8中队)",
    "tbf复仇者",
    "tbd蹂躏者",
    "实验型xsb3c-1",
    "sbd无畏(麦克拉斯基队)",
    "btd-1毁灭者",
    "sb2c地狱俯冲者",
    "sbd无畏",
    "高性能舵机",
    "高性能火控雷达",
    "白鹰精英损管",
    "超重弹",
    "珍珠之泪",
    "小海狸中队队徽",
    "sg雷达",
    "pby-5a卡特琳娜水上机",
    "作战报告：af",
    "100/150号航空燃油",
    "火控雷达",
    "对空雷达",
    "改良深弹投射器",
    "tbm-3复仇者(反潜)",
    "双联装114mm高平两用炮mark_iv",
    "双联装120mm高平两用炮mark_xi",
    "双联装102mm副炮mark_xvi",
    "双联装134mm高炮",
    "双联装120mm主炮",
    "三联装102mm副炮",
    "120mm单装炮(皇家)",
    "双联装102mm副炮",
    "单装102mm副炮",
    "试作型四联装152mm主炮",
    "试作型三联装152mm主炮",
    "三联装152mm主炮(皇家)",
    "双联装152mm主炮",
    "双联装152mm副炮",
    "单装152mm副炮",
    "试作型三联装234mm主炮",
    "试作型三联装203mm主炮mark_ix",
    "试作型双联装234mm主炮",
    "双联装203mm主炮",
    "双联装381mm主炮改",
    "试作型三联装381mm主炮",
    "三联装406mm主炮",
    "四联装356mm主炮",
    "双联装356mm主炮",
    "双联装381mm主炮",
    "五联装533mm鱼雷mark_ix",
    "四联装533mm鱼雷mark_ix",
    "三联装533mm鱼雷mark_ix",
    "潜艇用mark_20_s鱼雷-彼得",
    "潜艇用mark_12鱼雷-菲里",
    "潜艇用mark_viii鱼雷",
    "六联装40mm博福斯对空机炮",
    "双联装134mm高炮",
    "双联装40mm博福斯海兹梅耶",
    "双联装40mm博福斯staag",
    "双联装113mm高射炮",
    "八联装40mm“砰砰”炮",
    "120mm高射炮mark_viii",
    "20mm厄利孔高射炮mark_ii",
    "双联装40mm博福斯对空机炮mark_i",
    "102mm高射炮",
    "四联装40mm“砰砰”炮",
    "双联装40mm“砰砰”炮",
    "海大黄蜂",
    "海怒",
    "海喷火fr.47",
    "海毒牙",
    "海喷火",
    "海飓风",
    "海斗士",
    "飞龙(鱼雷机)",
    "火冠",
    "火把",
    "剑鱼(818中队)",
    "梭鱼",
    "青花鱼",
    "剑鱼",
    "梭鱼(831中队)",
    "萤火虫(轰炸机)",
    "海燕",
    "贼鸥",
    "海军部火控台",
    "高性能对空雷达",
    "6crh穿甲弹",
    "纳尔逊的旗语",
    "破损的演讲稿",
    "归航信标",
    "刺猬弹",
    "基础深弹投射器",
    "剑鱼mark_ii-asv(反潜)",
    "双联100mm98式高射炮",
    "127mm连装炮改",
    "120mm单装高角炮",
    "100mm88式火炮",
    "127mm连装炮",
    "127mm单装两用炮",
    "十一年式120mm单装炮",
    "120mm单装炮(重樱)",
    "试作型155mm三连装炮改",
    "155mm三连装炮",
    "152mm连装炮",
    "140mm连装炮",
    "152mm单装炮",
    "140mm单装炮",
    "试作型203mm(3号)连装炮",
    "203mm连装炮改",
    "203mm连装炮",
    "试作型三联装310mm主炮",
    "试作型410mm三连装炮",
    "410mm连装炮(三式弹)",
    "410mm连装炮改",
    "410mm连装炮",
    "356mm毘式连装炮",
    "305mm连装炮",
    "356mm连装炮",
    "五联装610mm鱼雷",
    "四联装610mm鱼雷改",
    "四联装610mm鱼雷",
    "三联装610mm鱼雷改",
    "三联装610mm鱼雷",
    "双联装610mm鱼雷",
    "潜艇用95式纯氧鱼雷改",
    "潜艇用96式纯氧鱼雷",
    "潜艇用95式纯氧鱼雷",
    "潜艇用92式潜射鱼雷改",
    "潜艇用92式潜射鱼雷",
    "试作型五式40mm高射机关炮",
    "127mm连装高角炮改",
    "100mm连装高炮",
    "九六式25mm三连装暴风避盾机炮",
    "80mm高射炮",
    "127mm连装高射炮",
    "76mm高射炮(重樱)",
    "毘式40mm连装机枪",
    "25mm三连装高射机枪",
    "25mm连装高射机枪",
    "25mm高射机枪",
    "紫电改二",
    "烈风",
    "零战五二型",
    "零战三二型",
    "零战二一型",
    "九六式舰战",
    "试作型彩云(舰攻型)",
    "流星",
    "天山改",
    "天山",
    "九七式舰攻改",
    "九七式舰攻",
    "试作舰载型天雷",
    "彗星二一型",
    "彗星一二型甲",
    "彗星(轰炸机)",
    "九九式舰爆改",
    "九九式舰爆",
    "z旗",
    "九三式纯氧鱼雷",
    "vh装甲钢板",
    "一式穿甲弹",
    "治愈系猫爪",
    "vc装甲钢板",
    "94式高射装置",
    "九八式射击延迟装置",
    "九一式穿甲弹",
    "晴岚",
    "强风",
    "二式水上战斗机",
    "瑞云",
    "“九四式40厘米炮”部件",
    "兵装补给（鱼雷）",
    "兵装补给（中小口径武器）",
    "兵装补给（航空）",
    "双联装128mmskc41高平两用炮改",
    "双联装127mmkm40主炮",
    "双联装128mmskc41高平两用炮",
    "单装127mm主炮",
    "试作型双联装skc28式150mm主炮改",
    "双联装skc28式150mm副炮",
    "单装tbtskc36式150mm主炮",
    "双联装tbtskc36式150mm主炮",
    "三联装skc25式150mm主炮",
    "单装skc28式150mm主炮",
    "试作型三联装203mmskc主炮",
    "双联装203mmskc主炮",
    "试作型三联装305mmskc39主炮(超巡用)",
    "三联283mmskc28主炮",
    "试作型三联装305mmskc39主炮",
    "试作型双联装406mmskc主炮",
    "双联380mmskc主炮",
    "三联283mmskc34主炮",
    "五联装533mm磁性鱼雷",
    "四联装533mm磁性鱼雷",
    "三联装533mm磁性鱼雷",
    "潜艇用g7e声导鱼雷",
    "潜艇用g7a鱼雷",
    "试作型四联装30mm机炮",
    "双联105mmskc高炮改进型",
    "双联105mmskc高炮",
    "双联37mm_flak_m43机枪",
    "双联装88mmskc32高炮",
    "双联37mm手拉机枪",
    "37mm机枪",
    "四联装20mm_mg机枪",
    "试作舰载型bf-109g",
    "me-155a舰载战斗机",
    "试作型舰载fw-190_a-5",
    "bf-109t舰载战斗机",
    "ar-197舰载战斗机",
    "ju-87_d-4",
    "fi-167舰载鱼雷机",
    "ar-195舰载鱼雷机",
    "ju-87c俯冲轰炸机",
    "he-50b舰载轰炸机",
    "改良蓄电池阵列",
    "改良型水下进气管",
    "fumo_25",
    "约定的证明",
    "533mm磁性鱼雷",
    "艇壳改良设计案",
    "fl-282直升机",
    "试作型双联装130mm主炮model1936",
    "单装150mm主炮",
    "四神之印",
    "“宁海号”水上侦察机",
    "上游-1",
    "双联装135mm主炮model1938",
    "双联装120mm炮model1936",
    "双联装120mm炮model1933",
    "双联装120mm主炮(撒丁)",
    "三联装152mm主炮model1934",
    "双联203mm主炮model1927",
    "双联203mm主炮model1924",
    "试作型三联装406mm主炮model1940",
    "三联装381mm主炮model1934",
    "三联装320mm主炮model1934",
    "潜艇用533mm鱼雷si_270",
    "试作型双联90mm高角炮model1939",
    "90mm单装高角炮model1939",
    "双联37mm机枪model1932",
    "re.2001公羊",
    "g.50箭式战斗机",
    "b-13_双联装130mm主炮b-2lm",
    "130mm单装炮",
    "b-38_三联装152mm主炮mk-5",
    "双联装152mm主炮model1892",
    "单装152mm主炮",
    "b-1-p_三联装180mm主炮model1932",
    "b-50_三联装305mm主炮mk-15",
    "b-37_三联装406mm主炮mk-1",
    "三联装305mm主炮model1907",
    "b-34_100mm双联装防空炮mz-14",
    "b-54_100mm双联装防空炮",
    "37mm防空炮70-k",
    "100mm双联装防空炮sm-5-1s",
    "试作型vit-2(vk-107)",
    "试作舰载型su-2",
    "海魂迷彩",
    "机密文件·日志残卷",
    "138.6mm单装炮mle1929",
    "138.6mm单装炮mle1927",
    "四联装130mm副炮mle1932",
    "130mm单装炮mle1924",
    "双联装130mm主炮mle1935",
    "单装155mm副炮mle1920",
    "双联装155mm主炮mle1920",
    "三联装152mm主炮mle1930",
    "试作型三联装203mm舰炮",
    "双联装203mm主炮mle1931",
    "双联装203mm主炮mle1924(潜艇用)",
    "双联装203mm主炮mle1924",
    "试作型四联装330mm主炮mle1931(超巡用)",
    "试作型三联装406mm/50主炮",
    "四联装380mm主炮mle1935",
    "四联装330mm主炮mle1931",
    "三联装550mm鱼雷",
    "双联装550mm鱼雷",
    "潜艇用550mm24v鱼雷",
    "双联37mm高射炮mle1936",
    "双联装100mm高炮",
    "d.790",
    "gl.2舰载战斗机",
    "br.810",
    "pl.7舰载鱼雷机",
    "天使之羽",
    "fba_19",
    "智慧模块",
    "晃悠悠",
    "随机单词生成器",
    "鮟鱇肝",
    "玉米灯笼",
    "gamers的证明",
    "组徽",
    "心之钥匙",
    "觉醒宝珠",
    "偶像手环",
    "煌翼炎龙",
    "戴拿爆能加农",
    "苍穹喷射机",
    "爆裂钻孔机",
    "古立特圣剑",
    "征战巨坦",
    "苦无",
    "短剑",
    "权杖",
    "猎弓",
    "轻弩",
    "指挥刀",
    "骑枪",
    "大剑",
    "手弩",
    "铁剑",
    "单手锤",
    "双剑",
    "准时的怀表",
    "护教骑士战旗",
    "探测平衡装置",
    "不再哭泣",
    "幸运舰的“惩罚”",
    "波涛与优雅的午后",
    "勇敢的美梦之枕",


    "PVE用舰船综合性能强度榜",
    "装备一图榜"
)

val SHIP_LIST = listOf(
    "特装型布里mkiii",
    "新泽西",
    "前卫",
    "信浓",
    "岛风",
    "乌尔里希·冯·胡滕",
    "喀琅施塔得",
    "吾妻",
    "腓特烈大帝",
    "德雷克",
    "白龙",
    "埃吉尔",
    "普利茅斯",
    "布雷斯特",
    "试作型布里mkii",
    "埃尔德里奇",
    "圣地亚哥",
    "巴尔的摩",
    "北卡罗来纳",
    "华盛顿",
    "南达科他",
    "企业",
    "黛朵",
    "贝尔法斯特",
    "胡德",
    "厌战",
    "英王乔治五世",
    "威尔士亲王",
    "约克公爵",
    "光辉",
    "胜利",
    "可畏",
    "夕立",
    "雪风",
    "筑摩",
    "高雄",
    "爱宕",
    "摩耶",
    "鸟海",
    "长门",
    "纪伊",
    "土佐",
    "赤城",
    "加贺",
    "翔鹤",
    "瑞鹤",
    "大凤",
    "明石",
    "欧根亲王",
    "俾斯麦",
    "提尔比茨",
    "齐柏林伯爵",
    "阿芙乐尔",
    "z46",
    "江风",
    "三笠",
    "能代",
    "蒙彼利埃",
    "伊19",
    "u-81",
    "u-47",
    "凯旋",
    "让·巴尔",
    "马萨诸塞",
    "半人马",
    "埃塞克斯",
    "大青花鱼",
    "明尼阿波利斯",
    "天城",
    "加贺bb",
    "天狼星",
    "香格里拉",
    "邦克山",
    "伊13",
    "确捷",
    "恶毒",
    "伊168",
    "u-101",
    "阿拉巴马",
    "棘鳍",
    "利托里奥",
    "扎拉",
    "加斯科涅(μ兵装)",
    "赤城(μ兵装)",
    "骏河",
    "龙凤",
    "塔什干",
    "基洛夫",
    "恰巴耶夫",
    "苏维埃贝拉罗斯",
    "苏维埃罗西亚",
    "无畏",
    "布莱默顿",
    "里诺",
    "黎塞留",
    "圣女贞德",
    "阿尔及利亚",
    "豪",
    "英仙座",
    "赫敏",
    "u-96",
    "凉月",
    "大凤(μ兵装)",
    "塔什干(μ兵装)",
    "黛朵(μ兵装)",
    "罗恩(μ兵装)",
    "光辉(μ兵装)",
    "恶毒(μ兵装)",
    "彼得·史特拉塞",
    "海因里希亲王",
    "u-37",
    "波拉",
    "塔林",
    "维托里奥·维内托",
    "阿布鲁齐公爵",
    "天鹰",
    "艾伦·萨姆纳",
    "提康德罗加",
    "旧金山",
    "射水鱼",
    "风云",
    "英格拉罕",
    "葛城",
    "新奥尔良",
    "可怖",
    "马格德堡",
    "阿达尔伯特亲王",
    "卡律布狄斯",
    "布里斯托尔",
    "基辅",
    "伏尔加",
    "帝国",
    "庞培·马格诺",
    "塞德利茨",
    "吕佐夫",
    "埃姆登",
    "贾维斯",
    "不挠",
    "霞飞",
    "不屈",
    "莱昂纳多·达·芬奇",
    "朱塞佩·加里波第",
    "罗马",
    "绀紫之心",
    "圣黑之心",
    "群白之心",
    "翡绿之心",
    "久远",
    "猫音",
    "露露缇耶",
    "绊爱·elegant",
    "绊爱·anniversary",
    "绊爱·supergamer",
    "玛莉萝丝",
    "穗香",
    "霞doa",
    "海咲",
    "天海春香",
    "如月千早",
    "水濑伊织",
    "三浦梓",
    "宝多六花",
    "新条茜",
    "南梦芽",
    "飞鸟川千濑",
    "飞龙·meta",
    "皇家方舟·meta",
    "海伦娜·meta",
    "苍龙·meta",
    "格奈森瑙·meta",
    "沙恩霍斯特·meta",
    "反击·meta",
    "海王星",
    "君主",
    "伊吹",
    "出云",
    "罗恩",
    "路易九世",
    "西雅图",
    "佐治亚",
    "北风",
    "加斯科涅",
    "柴郡",
    "美因茨",
    "奥丁",
    "香槟",
    "安克雷奇",
    "奥古斯特·冯·帕塞瓦尔",
    "马可·波罗",
    "鲁普雷希特亲王",
    "哈尔滨",
    "契卡洛夫",
    "泛用型布里",
    "莫里",
    "查尔斯·奥斯本",
    "拉菲",
    "海伦娜",
    "克利夫兰",
    "哥伦比亚",
    "休斯敦",
    "印第安纳波利斯",
    "阿斯托利亚",
    "昆西",
    "文森斯",
    "威奇塔",
    "亚利桑那",
    "科罗拉多",
    "马里兰",
    "西弗吉尼亚",
    "列克星敦",
    "萨拉托加",
    "约克城",
    "大黄蜂",
    "女灶神",
    "格伦维尔",
    "萤火虫",
    "勇敢",
    "标枪",
    "吸血鬼",
    "谢菲尔德",
    "格罗斯特",
    "爱丁堡",
    "欧若拉",
    "伦敦",
    "多塞特郡",
    "约克",
    "埃克塞特",
    "声望",
    "伊丽莎白女王",
    "纳尔逊",
    "罗德尼",
    "独角兽",
    "鹰",
    "皇家方舟",
    "光荣",
    "黑暗界",
    "恐怖",
    "吹雪",
    "白雪",
    "绫波",
    "响",
    "时雨",
    "野分",
    "夕张",
    "由良",
    "鬼怒",
    "最上",
    "三隈",
    "足柄",
    "金刚",
    "比叡",
    "榛名",
    "雾岛",
    "陆奥",
    "凤翔",
    "龙骧",
    "苍龙",
    "飞龙",
    "z1",
    "z23",
    "z25",
    "希佩尔海军上将",
    "德意志",
    "斯佩伯爵海军上将",
    "沙恩霍斯特",
    "格奈森瑙",
    "鞍山",
    "抚顺",
    "长春",
    "太原",
    "逸仙",
    "宁海",
    "平海",
    "海风",
    "山风",
    "新月jp",
    "春月",
    "宵月",
    "尼古拉斯",
    "圣路易斯",
    "神通",
    "阿贺野",
    "无敌",
    "火枪手",
    "丹佛",
    "小贝法",
    "阿贝克隆比",
    "伊26",
    "伊58",
    "鲦鱼",
    "u-557",
    "z35",
    "埃米尔·贝尔汀",
    "絮库夫",
    "敦刻尔克",
    "鲁莽",
    "卷波",
    "马拉尼",
    "追赶者",
    "独立",
    "z2",
    "铃谷",
    "小比叡",
    "小赤城",
    "小齐柏林",
    "u-556",
    "u-73",
    "z36",
    "小海伦娜",
    "小克利夫兰",
    "小圣地亚哥",
    "倔强",
    "伊25",
    "伊56",
    "u-522",
    "巴丹",
    "伯明翰",
    "黑太子",
    "朱利奥·凯撒",
    "龙骑兵",
    "u-110",
    "克利夫兰(μ兵装)",
    "谢菲尔德(μ兵装)",
    "希佩尔海军上将(μ兵装)",
    "霞",
    "比洛克西",
    "浦波",
    "威严",
    "明斯克",
    "水星纪念",
    "甘古特",
    "库珀",
    "蓝鳃鱼",
    "花月",
    "长波",
    "小声望",
    "塔尔图",
    "拉·加利索尼埃",
    "沃克兰",
    "贝亚恩",
    "小光辉",
    "爱斯基摩人",
    "英勇",
    "伊卡洛斯",
    "z26",
    "熊野",
    "千岁",
    "千代田",
    "樫野",
    "普林斯顿",
    "大青花鱼(μ兵装)",
    "巴尔的摩(μ兵装)",
    "威悉",
    "纽伦堡",
    "z24",
    "z28",
    "文琴佐·焦贝蒂",
    "神速",
    "u-410",
    "应瑞",
    "肇和",
    "佩内洛珀",
    "雷鸣",
    "摩尔曼斯克",
    "洪亮",
    "托里拆利",
    "西北风",
    "西南风",
    "尼科洛索·达雷科",
    "史蒂芬·波特",
    "小天城",
    "博伊西",
    "莫里森",
    "小企业",
    "鹦鹉螺",
    "马耶·布雷泽",
    "福煦",
    "易北",
    "u-1206",
    "海天",
    "海圻",
    "镇海",
    "阿尔汉格尔斯克",
    "灵敏",
    "的里雅斯特",
    "图林根",
    "约克de",
    "埃尔宾",
    "小欧根",
    "司战女神",
    "小柴郡",
    "复仇",
    "进取",
    "博尔扎诺",
    "阿尔弗雷多·奥里亚尼",
    "埃曼努埃尔·佩萨格诺",
    "涅普顿",
    "诺瓦露",
    "布兰",
    "贝露",
    "22",
    "33",
    "乌璐露",
    "萨拉娜",
    "芙米露露",
    "绊爱",
    "凪咲",
    "女天狗",
    "莫妮卡",
    "秋月律子",
    "双海亚美",
    "双海真美",
    "莲ssss",
    "奈美子",
    "貉ssss",
    "扶桑·meta",
    "飞鹰·meta",
    "隼鹰·meta",
    "山城·meta",
    "孟菲斯·meta",
    "特伦托·meta",
    "杜威",
    "格里德利",
    "弗莱彻",
    "撒切尔",
    "本森",
    "西姆斯",
    "哈曼",
    "布鲁克林",
    "菲尼克斯",
    "亚特兰大",
    "朱诺",
    "北安普敦",
    "芝加哥",
    "波特兰",
    "宾夕法尼亚",
    "田纳西",
    "加利福尼亚",
    "长岛",
    "胡蜂",
    "女将",
    "阿卡司塔",
    "热心",
    "命运女神",
    "猎人",
    "天后",
    "阿基里斯",
    "阿贾克斯",
    "南安普顿",
    "阿瑞托莎",
    "加拉蒂亚",
    "什罗普郡",
    "肯特",
    "萨福克",
    "诺福克",
    "反击",
    "晓",
    "雷",
    "电",
    "白露",
    "阳炎",
    "初春",
    "若叶",
    "初霜",
    "有明",
    "夕暮",
    "黑潮",
    "亲潮",
    "五十铃",
    "妙高",
    "那智",
    "扶桑",
    "山城",
    "伊势",
    "日向",
    "飞鹰",
    "隼鹰",
    "祥凤",
    "莱比锡",
    "贝利",
    "z19",
    "神风",
    "松风",
    "文月",
    "长月",
    "清波",
    "拉德福特",
    "杰金斯",
    "火奴鲁鲁",
    "丘比特",
    "泽西",
    "川内",
    "那珂",
    "浦风",
    "矶风",
    "滨风",
    "谷风",
    "斐济",
    "牙买加",
    "朝潮",
    "大潮",
    "满潮",
    "荒潮",
    "苏塞克斯",
    "z18",
    "福尔班",
    "勒马尔",
    "布什",
    "孟菲斯",
    "纽卡斯尔",
    "霍比",
    "科尔克",
    "黑泽伍德",
    "康克德",
    "旗风",
    "库拉索",
    "杓鹬",
    "金伯利",
    "回声",
    "圣胡安",
    "艾尔温",
    "贝奇",
    "斯坦利",
    "加富尔伯爵",
    "特伦托",
    "斯莫利",
    "格拉斯哥",
    "哈尔西·鲍威尔",
    "卡萨布兰卡",
    "马布尔黑德",
    "追风",
    "卡辛",
    "唐斯",
    "克雷文",
    "麦考尔",
    "奥利克",
    "富特",
    "斯彭斯",
    "奥马哈",
    "罗利",
    "彭萨科拉",
    "盐湖城",
    "内华达",
    "俄克拉荷马",
    "博格",
    "兰利",
    "突击者",
    "小猎兔犬",
    "大斗犬",
    "彗星",
    "新月",
    "小天鹅",
    "狐提",
    "利安得",
    "竞技神",
    "不知火",
    "长良",
    "阿武隈",
    "古鹰",
    "加古",
    "青叶",
    "衣笠",
    "柯尼斯堡",
    "卡尔斯鲁厄",
    "科隆",
    "z20",
    "z21",
    "睦月",
    "如月",
    "卯月",
    "水无月",
    "三日月",
    "里士满",
    "圣地亚哥",
    "厌战",
    "夕立",
    "拉菲",
    "海伦娜",
    "萨拉托加",
    "标枪",
    "伦敦",
    "约克",
    "埃克塞特",
    "独角兽",
    "皇家方舟",
    "绫波",
    "时雨",
    "夕张",
    "鬼怒",
    "最上",
    "苍龙",
    "飞龙",
    "z1",
    "z23",
    "鞍山",
    "长春",
    "宁海",
    "平海",
    "尼古拉斯",
    "神通",
    "埃米尔·贝尔汀",
    "独立",
    "霞",
    "水星纪念",
    "西姆斯",
    "哈曼",
    "波特兰",
    "长岛",
    "女将",
    "阿卡司塔",
    "热心",
    "命运女神",
    "阿基里斯",
    "阿贾克斯",
    "萨福克",
    "阳炎",
    "初春",
    "初霜",
    "有明",
    "夕暮",
    "五十铃",
    "扶桑",
    "山城",
    "伊势",
    "日向",
    "祥凤",
    "莱比锡",
    "贝利",
    "神风",
    "松风",
    "川内",
    "滨风",
    "谷风",
    "福尔班",
    "勒马尔",
    "纽卡斯尔",
    "库拉索",
    "杓鹬",
    "卡辛",
    "唐斯",
    "内华达",
    "俄克拉荷马",
    "博格",
    "兰利",
    "突击者",
    "彗星",
    "新月",
    "小天鹅",
    "狐提",
    "利安得",
    "竞技神",
    "不知火",
    "阿武隈",
    "古鹰",
    "加古",
    "卡尔斯鲁厄",
    "科隆",
    "睦月",
    "如月",
)

val NAME_LIST = SHIP_LIST + EQUIP_LIST