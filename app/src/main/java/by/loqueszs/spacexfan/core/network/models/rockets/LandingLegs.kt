package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.Serializable

@Serializable
data class LandingLegs(
    val number: Long? = null,
    val material: String? = null
)