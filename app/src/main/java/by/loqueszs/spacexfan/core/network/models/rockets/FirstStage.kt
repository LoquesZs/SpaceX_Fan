package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirstStage(
    @SerialName("thrust_sea_level")
    val thrustSeaLevel: Thrust,

    @SerialName("thrust_vacuum")
    val thrustVacuum: Thrust,

    val reusable: Boolean,
    val engines: Long,

    @SerialName("fuel_amount_tons")
    val fuelAmountTons: Long,

    @SerialName("burn_time_sec")
    val burnTimeSEC: Long
)