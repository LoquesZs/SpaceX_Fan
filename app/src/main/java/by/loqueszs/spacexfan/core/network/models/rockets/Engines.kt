package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Engines(
    val isp: ISP,

    @SerialName("thrust_sea_level")
    val thrustSeaLevel: Thrust,

    @SerialName("thrust_vacuum")
    val thrustVacuum: Thrust,

    val number: Long,
    val type: String,
    val version: String,
    val layout: String,

    @SerialName("engine_loss_max")
    val engineLossMax: Long,

    @SerialName("propellant_1")
    val propellant1: String,

    @SerialName("propellant_2")
    val propellant2: String,

    @SerialName("thrust_to_weight")
    val thrustToWeight: Double
)