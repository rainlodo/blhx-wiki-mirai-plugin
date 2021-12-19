package org.iris.wiki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchData(
    @SerialName("result")
    val result: List<String>
)
