package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class Mass(
    val kg: Long? = null,
    val lb: Long? = null
)