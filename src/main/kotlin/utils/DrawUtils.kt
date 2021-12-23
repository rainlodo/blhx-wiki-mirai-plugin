package org.iris.wiki.utils

import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.iris.wiki.config.CommonConfig
import java.io.FileReader

object DrawUtils {
    
    enum class DrawType {
        Light,    // 轻池
        Heavy,    // 重池
        Special,  // 特池
        Active    // 活动池
    }

    enum class Rarity {
        UR,
        SSR,
        SR,
        R,
        N
    }

    @Serializable
    data class Ship(
        @SerialName("name")
        var name: String,
        @SerialName("probability")
        var probability: Int
    )

    @Serializable
    data class ActivePool(
        @SerialName("name")
        var name: String,
        @SerialName("ur")
        var ur : List<Ship>,
        @SerialName("ssr")
        var ssr : List<Ship>,
        @SerialName("sr")
        var sr : List<Ship>,
        @SerialName("r")
        var r : List<Ship>,
    )


    val ship_contain_map = hashMapOf<Pair<DrawType, Rarity>, List<String>>()
    val active_ship_map = hashMapOf<String, ActivePool>()

    init {
        initIcon()
        initActivePool()
    }

    private fun initIcon() {
        // 图鉴url
        ship_contain_map[Pair(DrawType.Light, Rarity.SSR)] = listOf(
            "圣地亚哥", "圣地亚哥", "圣地亚哥", "圣地亚哥", "圣地亚哥", "蒙彼利埃", "黛朵", "确捷", "雪风", "明石", "Z46", "阿芙乐尔", "凯旋", "恶毒", "江风"
        )
        ship_contain_map[Pair(DrawType.Light, Rarity.SR)] = listOf(
            "莫里", "马拉尼", "拉菲", "圣路易斯", "小海伦娜", "丹佛", "小克利夫兰", "比洛克西", "克利夫兰(μ兵装)", "标枪", "无敌", "欧若拉", "谢菲尔德", "格罗斯特", "小贝法", "黑太子", "吹雪", "绫波", "野分", "最上", "三隈", "Z23", "Z25", "Z35", "长春", "太原", "逸仙", "宁海", "平海", "鲁莽", "倔强", "春月", "宵月", "花月", "长波"
        )
        ship_contain_map[Pair(DrawType.Light, Rarity.R)] = listOf(
            "哈曼", "弗莱彻", "贝奇", "布什", "黑泽伍德", "金伯利", "斯坦利", "斯莫利", "哈尔西·鲍威尔", "霍比", "科尔克", "康克德", "孟菲斯", "布鲁克林", "菲尼克斯", "亚特兰大", "朱诺", "女将", "阿卡司塔", "热心", "丘比特", "泽西", "库拉索", "杓鹬", "阿基里斯", "阿贾克斯", "南安普顿", "格拉斯哥", "牙买加", "神风", "松风", "旗风", "长月", "初春", "若叶", "初霜", "有明", "夕暮", "大潮", "满潮", "荒潮", "浦风", "矶风", "谷风", "Z18", "Z19", "清波", "莱比锡", "福尔班", "勒马尔", "文月", "朝潮", "滨风", "那珂"
        )
        ship_contain_map[Pair(DrawType.Light, Rarity.N)] = listOf(
            "卡辛", "唐斯", "克雷文", "麦考尔", "富特", "斯彭斯", "奥利克", "奥马哈", "罗利", "小猎兔犬", "大斗犬", "彗星", "新月", "小天鹅", "狐提", "利安得", "睦月", "如月", "卯月", "长良", "柯尼斯堡", "卡尔斯鲁厄", "科隆"
        )

        ship_contain_map[Pair(DrawType.Heavy, Rarity.SSR)] = listOf(
            "明尼阿波利斯", "北卡罗来纳", "华盛顿", "胡德", "厌战", "威尔士亲王", "约克公爵", "高雄", "爱宕", "欧根亲王", "提尔比茨", "让·巴尔", "马萨诸塞", "长门", "三笠", "天城", "加贺BB", "土佐", "俾斯麦", "英王乔治五世", "加斯科涅(μ兵装)"
        )
        ship_contain_map[Pair(DrawType.Heavy, Rarity.SR)] = listOf(
            "休斯敦", "印第安纳波利斯", "亚利桑那", "科罗拉多", "马里兰", "伦敦", "多塞特郡", "约克", "足柄", "声望", "伊丽莎白女王", "纳尔逊", "黑暗界", "恐怖", "阿贝克隆比", "雾岛", "德意志", "斯佩伯爵海军上将", "希佩尔海军上将", "希佩尔海军上将(μ兵装)", "小比叡", "敦刻尔克", "铃谷", "比叡"
        )
        ship_contain_map[Pair(DrawType.Heavy, Rarity.R)] = listOf(
            "北安普敦", "芝加哥", "宾夕法尼亚", "田纳西", "加利福尼亚", "什罗普郡", "苏塞克斯", "肯特", "萨福克", "诺福克", "反击", "伊势"
        )
        ship_contain_map[Pair(DrawType.Heavy, Rarity.N)] = listOf(
            "彭萨科拉", "内华达", "俄克拉荷马", "青叶", "衣笠"
        )

        ship_contain_map[Pair(DrawType.Special, Rarity.SSR)] = listOf(
            "企业", "埃塞克斯", "半人马", "胜利", "翔鹤", "瑞鹤", "伊19", "明石", "U-81", "U-47", "U-101", "伊168", "香格里拉", "伊13", "U-96", "赤城(μ兵装)"
        )
        ship_contain_map[Pair(DrawType.Special, Rarity.SR)] = listOf(
            "休斯敦", "印第安纳波利斯", "列克星敦", "萨拉托加", "约克城", "大黄蜂", "鲦鱼", "女灶神", "伦敦", "多塞特郡", "独角兽", "追赶者", "光荣", "伊26", "伊58", "U-557", "小赤城", "小齐柏林", "絮库夫", "伊25", "U-522", "伊56", "U-556", "U-73"
        )
        ship_contain_map[Pair(DrawType.Special, Rarity.R)] = listOf(
            "北安普敦", "芝加哥", "长岛", "什罗普郡", "肯特", "萨福克", "诺福克"
        )
        ship_contain_map[Pair(DrawType.Special, Rarity.N)] = listOf(
            "彭萨科拉", "博格", "兰利", "突击者", "竞技神"
        )
    }

    private fun initActivePool() {
        val fr = FileReader("${CommonConfig.json_string}/draw/active_pool.json")
        val parser= JsonParser()
        val jsonArray = parser.parse(fr.readText()).asJsonArray
        val gson=Gson()
        //将数据添加到userList中
        for(user in jsonArray){
            val pool = gson.fromJson(user,ActivePool::class.java)
            active_ship_map[pool.name] = pool
        }
        fr.close()
    }

    fun getRarity() : Rarity {
        return when ((0 until 100).random()) {
            in (0..6) -> Rarity.SSR
            in (7 .. 18) -> Rarity.SR
            in (19 .. 69) -> Rarity.R
            else -> Rarity.N
        }
    }


}