package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class PayloadWeight(
    val id: String? = null,
    val name: String? = null,
    val kg: Long? = null,
    val lb: Long? = null
)