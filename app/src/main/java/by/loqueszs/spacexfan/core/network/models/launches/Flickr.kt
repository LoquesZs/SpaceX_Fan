package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.Serializable

@Serializable
data class Flickr(
    val small: List<String>? = emptyList(),
    val original: List<String>? = emptyList()
)