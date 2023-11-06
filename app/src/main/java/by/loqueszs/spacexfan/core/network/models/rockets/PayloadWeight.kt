package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class PayloadWeight(
    val id: String,
    val name: String,
    val kg: Long,
    val lb: Long
)