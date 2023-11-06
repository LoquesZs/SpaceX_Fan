package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Payloads(
    @SerialName("composite_fairing")
    val compositeFairing: CompositeFairing,

    @SerialName("option_1")
    val option1: String
)