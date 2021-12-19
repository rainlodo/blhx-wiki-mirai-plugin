package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EquipData(
    @SerialName("name")
    var name: String= "",
    @SerialName("type")
    var type: String= "",
    @SerialName("camp")
    var camp: String= "",
    @SerialName("pic")
    var pic: String= "",
    @SerialName("from")
    var from: String= "",
    @SerialName("route")
    var route: List<String> = listOf(),
    @SerialName("route_pic")
    var routePic: List<String> = listOf(),
    @SerialName("piece")
    var piece: String= ""
)
