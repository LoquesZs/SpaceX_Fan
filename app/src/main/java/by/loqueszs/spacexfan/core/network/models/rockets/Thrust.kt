package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class Thrust(
    val kN: Long? = null,
    val lbf: Long? = null
)