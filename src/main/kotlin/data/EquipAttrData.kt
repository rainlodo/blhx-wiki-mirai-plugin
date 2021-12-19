package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EquipAttrData(
    @SerialName("name")
    var name: String= "",
    @SerialName("type")
    var type: String= "",
    @SerialName("tag")
    var tag: String= "",
    @SerialName("level")
    var level: Int = 0,
    @SerialName("tno")
    var tno: Int = -1,
    @SerialName("unicorn")
    var unicorn: Boolean = false,
    @SerialName("pic")
    var pic: String= "",
    @SerialName("attr")
    var attr: String= "",
    @SerialName("use")
    var use: String= ""
)
