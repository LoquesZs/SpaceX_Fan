package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SecondStage(
    val thrust: Thrust,
    val payloads: Payloads,
    val reusable: Boolean,
    val engines: Long,

    @SerialName("fuel_amount_tons")
    val fuelAmountTons: Long,

    @SerialName("burn_time_sec")
    val burnTimeSEC: Long
)