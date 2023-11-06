package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class Diameter(
    val meters: Double,
    val feet: Double
)