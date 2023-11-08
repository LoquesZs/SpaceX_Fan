package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class Diameter(
    val meters: Double? = null,
    val feet: Double? = null
)