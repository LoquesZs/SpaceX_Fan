package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.Serializable

@Serializable
data class Flickr(
    val small: List<String>,
    val original: List<String>,
)