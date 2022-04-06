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
    MESSAGE_HELP

val MESSAGE_SEARCH = "没有对应的词条但是找到了以下相关内容，可以尝试搜索下面的词条~\n" +
    "(〃'▽'〃)\n"

val MESSAGE_NO_RESULT = "没有查询到结果呢~\n" +
    "(〃'▽'〃)\n" +
    MESSAGE_HELP

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
    "高性能对空雷达",
    "纳尔逊的旗语",
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
    "单装150mm主炮",
    "四神之印",
    "“宁海号”水上侦察机",
    "上游-1",
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
    "b-37_三联装406mm主炮mk-1",
    "三联装305mm主炮model1907",
    "b-34_100mm双联装防空炮mz-14",
    "b-54_100mm双联装防空炮",
    "37mm防空炮70-k",
    "100mm双联装防空炮sm-5-1s",
    "海魂迷彩",
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
    "试作型三联装406mm/50主炮",
    "四联装380mm主炮mle1935",
    "四联装330mm主炮mle1931",
    "三联装550mm鱼雷",
    "双联装550mm鱼雷",
    "潜艇用550mm24v鱼雷",
    "双联37mm高射炮mle1936",
    "双联装100mm高炮",
    "gl.2舰载战斗机",
    "pl.7舰载鱼雷机",
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
    "b-50_三联装305mm主炮mk-15"
)

val SHIP_LIST = listOf(
    "22",
    "33",
    "u-101",
    "u-110",
    "u-1206",
    "u-37",
    "u-410",
    "u-47",
    "u-522",
    "u-556",
    "u-557",
    "u-73",
    "u-81",
    "u-96",
    "z1",
    "z18",
    "z19",
    "z2",
    "z20",
    "z21",
    "z23",
    "z24",
    "z25",
    "z26",
    "z28",
    "z35",
    "z36",
    "z46",
    "三日月",
    "三浦梓",
    "三笠",
    "三隈",
    "不知火",
    "丘比特",
    "丹佛",
    "久远",
    "乌尔里希·冯·胡滕",
    "乌璐露",
    "五十铃",
    "亚利桑那",
    "亚特兰大",
    "亲潮",
    "什罗普郡",
    "企业",
    "伊13",
    "伊168",
    "伊19",
    "伊25",
    "伊26",
    "伊56",
    "伊58",
    "伊丽莎白女王",
    "伊势",
    "伊卡洛斯",
    "伊吹",
    "休斯敦",
    "伦敦",
    "伯明翰",
    "佐治亚",
    "佩内洛珀",
    "俄克拉荷马",
    "信浓",
    "俾斯麦",
    "倔强",
    "光荣",
    "光辉(μ兵装)",
    "光辉",
    "克利夫兰(μ兵装)",
    "克利夫兰",
    "克雷文",
    "兰利",
    "内华达",
    "凉月",
    "凤翔",
    "凪咲",
    "凯旋",
    "出云",
    "列克星敦",
    "初春",
    "初霜",
    "利安得",
    "利托里奥",
    "加利福尼亚",
    "加古",
    "加富尔伯爵",
    "加拉蒂亚",
    "加斯科涅(μ兵装)",
    "加斯科涅",
    "加贺",
    "加贺bb",
    "勇敢",
    "勒马尔",
    "北卡罗来纳",
    "北安普敦",
    "千代田",
    "千岁",
    "半人马",
    "华盛顿",
    "南安普顿",
    "南梦芽",
    "南达科他",
    "博伊西",
    "博格",
    "卡尔斯鲁厄",
    "卡律布狄斯",
    "卡萨布兰卡",
    "卡辛",
    "卯月",
    "印第安纳波利斯",
    "卷波",
    "厌战",
    "双海亚美",
    "双海真美",
    "反击",
    "古鹰",
    "可怖",
    "可畏",
    "史蒂芬·波特",
    "君主",
    "吸血鬼",
    "吹雪",
    "吾妻",
    "命运女神",
    "哈尔西·鲍威尔",
    "哈曼",
    "响",
    "哥伦比亚",
    "唐斯",
    "回声",
    "土佐",
    "圣地亚哥",
    "圣女贞德",
    "圣胡安",
    "圣路易斯",
    "圣黑之心",
    "埃克塞特",
    "埃吉尔",
    "埃塞克斯",
    "埃尔德里奇",
    "埃米尔·贝尔汀",
    "基洛夫",
    "塔什干(μ兵装)",
    "塔什干",
    "塔尔图",
    "塔林",
    "声望",
    "夏色祭",
    "夕张",
    "夕暮",
    "夕立",
    "多塞特郡",
    "大凤(μ兵装)",
    "大凤",
    "大斗犬",
    "大潮",
    "大神澪",
    "大青花鱼(μ兵装)",
    "大青花鱼",
    "大黄蜂",
    "天后",
    "天城",
    "天海春香",
    "天狼星",
    "天鹰",
    "太原",
    "奈美子",
    "奥丁",
    "奥利克",
    "奥古斯特·冯·帕塞瓦尔",
    "奥马哈",
    "女天狗",
    "女将",
    "女灶神",
    "如月",
    "如月千早",
    "妙高",
    "威严",
    "威奇塔",
    "威尔士亲王",
    "威悉",
    "孟菲斯",
    "宁海",
    "安克雷奇",
    "宝多六花",
    "宵月",
    "宾夕法尼亚",
    "富特",
    "射水鱼",
    "小天鹅",
    "小猎兔犬",
    "尼古拉斯",
    "尼科洛索·达雷科",
    "山城",
    "山风",
    "岛风",
    "川内",
    "巴丹",
    "巴尔的摩(μ兵装)",
    "巴尔的摩",
    "布什",
    "布兰",
    "布莱默顿",
    "布里斯托尔",
    "布鲁克林",
    "希佩尔海军上将(μ兵装)",
    "希佩尔海军上将",
    "平海",
    "库拉索",
    "库珀",
    "应瑞",
    "康克德",
    "弗莱彻",
    "彗星",
    "彭萨科拉",
    "彼得·史特拉塞",
    "德意志",
    "德雷克",
    "恐怖",
    "恰巴耶夫",
    "恶毒(μ兵装)",
    "恶毒",
    "扎拉",
    "托里拆利",
    "扶桑",
    "扶桑·meta",
    "抚顺",
    "拉·加利索尼埃",
    "拉德福特",
    "拉菲",
    "提尔比茨",
    "提康德罗加",
    "摩尔曼斯克",
    "摩耶",
    "撒切尔",
    "敦刻尔克",
    "文月",
    "文森斯",
    "文琴佐·焦贝蒂",
    "斐济",
    "斯佩伯爵海军上将",
    "斯坦利",
    "斯彭斯",
    "斯莫利",
    "新奥尔良",
    "新月",
    "新月JP",
    "新条茜",
    "新泽西",
    "旗风",
    "无敌",
    "无畏",
    "日向",
    "旧金山",
    "时乃空",
    "时雨",
    "昆西",
    "明尼阿波利斯",
    "明斯克",
    "明石",
    "易北",
    "春月",
    "晓",
    "普林斯顿",
    "最上",
    "有明",
    "朝潮",
    "本森",
    "朱利奥·凯撒",
    "朱诺",
    "杓鹬",
    "杜威",
    "杰金斯",
    "松风",
    "查尔斯·奥斯本",
    "柯尼斯堡",
    "柴郡",
    "标枪",
    "格伦维尔",
    "格奈森瑙",
    "格奈森瑙·meta",
    "格拉斯哥",
    "格罗斯特",
    "格里德利",
    "棘鳍",
    "榛名",
    "樫野",
    "欧根亲王",
    "欧若拉",
    "比叡",
    "比洛克西",
    "水无月",
    "水星纪念",
    "水濑伊织",
    "江风",
    "沃克兰",
    "沙恩霍斯特",
    "泛用型布里",
    "波拉",
    "波特兰",
    "泽西",
    "洪亮",
    "浦波",
    "浦风",
    "海伦娜",
    "海伦娜·meta",
    "海咲",
    "海因里希亲王",
    "海圻",
    "海天",
    "海王星",
    "海风",
    "涅普顿",
    "清波",
    "湊阿库娅",
    "满潮",
    "滨风",
    "火奴鲁鲁",
    "火枪手",
    "热心",
    "熊野",
    "爱丁堡",
    "爱宕",
    "爱斯基摩人",
    "牙买加",
    "特伦托",
    "特装型布里MKIII",
    "狐提",
    "独立",
    "独角兽",
    "猎人",
    "猫音",
    "玛莉萝丝",
    "瑞鹤",
    "甘古特",
    "田纳西",
    "由良",
    "电",
    "白上吹雪",
    "白雪",
    "白露",
    "白龙",
    "百鬼绫目",
    "皇家方舟",
    "皇家方舟·meta",
    "盐湖城",
    "睦月",
    "矶风",
    "确捷",
    "神通",
    "神速",
    "神风",
    "祥凤",
    "福尔班",
    "福煦",
    "秋月律子",
    "科尔克",
    "科罗拉多",
    "科隆",
    "穗香",
    "突击者",
    "竞技神",
    "筑摩",
    "紫咲诗音",
    "絮库夫",
    "约克",
    "约克公爵",
    "约克城",
    "纪伊",
    "纳尔逊",
    "纽伦堡",
    "纽卡斯尔",
    "绀紫之心",
    "绊爱",
    "绊爱·Anniversary",
    "绊爱·Elegant",
    "绊爱·SuperGamer",
    "绫波",
    "维托里奥·维内托",
    "罗利",
    "罗德尼",
    "罗恩(μ兵装)",
    "罗恩",
    "美因茨",
    "群白之心",
    "翔鹤",
    "翡绿之心",
    "肇和",
    "肯特",
    "胜利",
    "胡德",
    "胡蜂",
    "能代",
    "腓特烈大帝",
    "艾伦·萨姆纳",
    "艾尔温",
    "芙米露露",
    "芝加哥",
    "花月",
    "苍龙",
    "苍龙·meta",
    "苏塞克斯",
    "苏维埃罗西亚",
    "苏维埃贝拉罗斯",
    "苝",
    "若叶",
    "英仙座",
    "英勇",
    "英格拉罕",
    "英王乔治五世",
    "荒潮",
    "莫妮卡",
    "莫里",
    "莫里森",
    "莱比锡",
    "莲SSSS",
    "菲尼克斯",
    "萤火虫",
    "萨拉娜",
    "萨拉托加",
    "萨福克",
    "葛城",
    "蒙彼利埃",
    "蓝鳃鱼",
    "衣笠",
    "西北风",
    "西南风",
    "西姆斯",
    "西弗吉尼亚",
    "西雅图",
    "让·巴尔",
    "试作型布里MKII",
    "诺瓦露",
    "诺福克",
    "谢菲尔德(μ兵装)",
    "谢菲尔德",
    "谷风",
    "豪",
    "貉",
    "貉SSSS",
    "贝亚恩",
    "贝利",
    "贝奇",
    "贝尔法斯特",
    "贝露",
    "赤城(μ兵装)",
    "赤城",
    "赫敏",
    "足柄",
    "路易九世",
    "追赶者",
    "追风",
    "逸仙",
    "那智",
    "那珂",
    "邦克山",
    "里士满",
    "里诺",
    "野分",
    "金伯利",
    "金刚",
    "铃谷",
    "镇海",
    "长岛",
    "长春",
    "长月",
    "长波",
    "长良",
    "长门",
    "阳炎",
    "阿卡司塔",
    "阿基里斯",
    "阿尔及利亚",
    "阿布鲁齐公爵",
    "阿拉巴马",
    "阿斯托利亚",
    "阿武隈",
    "阿瑞托莎",
    "阿芙乐尔",
    "阿贝克隆比",
    "阿贺野",
    "阿贾克斯",
    "阿达尔伯特亲王",
    "陆奥",
    "隼鹰",
    "隼鹰·meta",
    "雪风",
    "雷",
    "雷鸣",
    "雾岛",
    "霍比",
    "霞",
    "霞DOA",
    "露露缇耶",
    "青叶",
    "鞍山",
    "风云",
    "飞鸟川千濑",
    "飞鹰",
    "飞鹰·meta",
    "飞龙",
    "飞龙·meta",
    "香格里拉",
    "香槟",
    "马可·波罗",
    "马可波罗",
    "马布尔黑德",
    "马拉尼",
    "马格德堡",
    "马耶·布雷泽",
    "马萨诸塞",
    "马里兰",
    "骏河",
    "高雄",
    "鬼怒",
    "鲁莽",
    "鲦鱼",
    "鸟海",
    "鹦鹉螺",
    "鹰",
    "麦考尔",
    "黎塞留",
    "黑太子",
    "黑暗界",
    "黑泽伍德",
    "黑潮",
    "黛朵(μ兵装)",
    "黛朵",
    "齐柏林伯爵",
    "龙凤",
    "龙骑兵",
    "龙骧",
    "喀琅施塔得",
    "阿尔汉格尔斯克",
    "伏尔加",
    "灵敏",
    "基辅",
    "帝国",
    "庞培·马格诺",
    "的里雅斯特",
    "山城·meta"
)

val NAME_LIST = SHIP_LIST + EQUIP_LIST