package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.Serializable

@Serializable
data class Patch(
    val small: String? = null,
    val large: String? = null
)