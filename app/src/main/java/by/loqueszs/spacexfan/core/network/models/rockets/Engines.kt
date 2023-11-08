package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Engines(
    val isp: ISP? = null,

    @SerialName("thrust_sea_level")
    val thrustSeaLevel: Thrust? = null,

    @SerialName("thrust_vacuum")
    val thrustVacuum: Thrust? = null,

    val number: Long? = null,
    val type: String? = null,
    val version: String? = null,
    val layout: String? = null,

    @SerialName("engine_loss_max")
    val engineLossMax: Long? = null,

    @SerialName("propellant_1")
    val propellant1: String? = null,

    @SerialName("propellant_2")
    val propellant2: String? = null,

    @SerialName("thrust_to_weight")
    val thrustToWeight: Double? = null
)