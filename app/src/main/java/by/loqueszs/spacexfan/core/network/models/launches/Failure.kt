package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.Serializable

@Serializable
data class Failure(
    val time: Int,
    val type: Int,
    val reason: String
)