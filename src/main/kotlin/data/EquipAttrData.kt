package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EquipAttrData(
    @SerialName("name")
    var name: String= "",
    @SerialName("type")
    var type: String= "",
    @SerialName("camp")
    var camp: String= "",
    @SerialName("pic")
    var pic: String= "",
    @SerialName("attr")
    var attr: String= ""
)
