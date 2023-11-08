package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ISP(
    @SerialName("sea_level")
    val seaLevel: Long? = null,

    val vacuum: Long? = null
)