package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoatData(
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

    @SerialName("pic")
    var pic: String = "",
)