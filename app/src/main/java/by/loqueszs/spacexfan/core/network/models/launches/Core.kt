package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Core(
    val core: String? = null,
    val flight: Long? = null,
    val gridfins: Boolean? = null,
    val legs: Boolean? = null,
    val reused: Boolean? = null,
    @SerialName("landing_attempt")
    val landingAttempt: Boolean? = null,
    @SerialName("landing_success")
    val landingSuccess: Boolean? = null,
    @SerialName("landing_type")
    val landingType: String? = null,
    val landpad: String? = null
)