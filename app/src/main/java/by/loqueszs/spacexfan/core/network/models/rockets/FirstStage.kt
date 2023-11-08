package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirstStage(
    @SerialName("thrust_sea_level")
    val thrustSeaLevel: Thrust? = null,

    @SerialName("thrust_vacuum")
    val thrustVacuum: Thrust? = null,

    val reusable: Boolean? = null,
    val engines: Long? = null,

    @SerialName("fuel_amount_tons")
    val fuelAmountTons: Float? = null,

    @SerialName("burn_time_sec")
    val burnTimeSEC: Long? = null
)