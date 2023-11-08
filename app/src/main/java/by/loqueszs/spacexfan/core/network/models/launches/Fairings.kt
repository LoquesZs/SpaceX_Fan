package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fairings(
    val reused: Boolean? = null,
    @SerialName("recovery_attempt")
    val recoveryAttempt: Boolean? = null,
    val recovered: Boolean? = null,
    val ships: List<String>? = emptyList()
)