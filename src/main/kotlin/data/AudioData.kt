package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudioData(
    @SerialName("url")
    val url : String
)
