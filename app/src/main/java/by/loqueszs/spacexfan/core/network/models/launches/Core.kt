package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Core(
    val core: String,
    val flight: Long,
    val gridfins: Boolean,
    val legs: Boolean,
    val reused: Boolean,
    @SerialName("landing_attempt")
    val landingAttempt: Boolean,
    @SerialName("landing_success")
    val landingSuccess: Boolean,
    @SerialName("landing_type")
    val landingType: String,
    val landpad: String,
)