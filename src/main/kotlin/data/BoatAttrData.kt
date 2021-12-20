package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoatAttrData(
    @SerialName("名称")
    var name: String = "",
    @SerialName("类型")
    var type: String = "",
    @SerialName("稀有度")
    var level: String = "",
    @SerialName("阵营")
    var camp: String = "",
    @SerialName("建造时间")
    var time: String? = null,
    @SerialName("pic")
    var pic: String = "",
    @SerialName("改造")
    var canUpgrade: Boolean= false,

    @SerialName("普通掉落点")
    var normal: String = "",
    @SerialName("活动掉落点")
    var active: String = "",
    @SerialName("其他来源")
    var other: String = "",


    @SerialName("获得科技点")
    var tech_gain: String = "",
    @SerialName("满级科技点")
    var tech_full: String = "",

    @SerialName("耐久")
    var naijiu : String = "",
    @SerialName("装甲")
    var zhuangjia : String = "",
    @SerialName("装填")
    var zhuangtian : String = "",
    @SerialName("炮击")
    var paoji : String = "",
    @SerialName("雷击")
    var leiji : String = "",
    @SerialName("机动")
    var jidong : String = "",
    @SerialName("防空")
    var fangkong : String = "",
    @SerialName("航空")
    var hangkong : String = "",
    @SerialName("命中")
    var mingzhong : String = "",
    @SerialName("反潜")
    var fanqian : String = "",
    @SerialName("幸运")
    var xingyun : String = "",
    @SerialName("航速")
    var hangsu : String = "",
    @SerialName("氧气")
    var yangqi : String = "",
    @SerialName("弹药")
    var danyao : String = "",
    @SerialName("消耗")
    var xiaohao : String = "",
)
