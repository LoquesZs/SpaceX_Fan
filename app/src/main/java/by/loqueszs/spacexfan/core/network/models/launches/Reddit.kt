package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.Serializable

@Serializable
data class Reddit(
    val campaign: String? = null,
    val launch: String? = null,
    val media: String? = null,
    val recovery: String? = null
)