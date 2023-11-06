package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class CompositeFairing(
    val height: Diameter,
    val diameter: Diameter
)