package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.Serializable

@Serializable
data class Failure(
    val time: Int? = null,
    val type: Int? = null,
    val reason: String? = null
)