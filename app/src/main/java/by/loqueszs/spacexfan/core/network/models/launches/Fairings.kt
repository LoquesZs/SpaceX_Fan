package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fairings(
    val reused: Boolean,
    @SerialName("recovery_attempt")
    val recoveryAttempt: Boolean,
    val recovered: Boolean,
    val ships: List<String>
)