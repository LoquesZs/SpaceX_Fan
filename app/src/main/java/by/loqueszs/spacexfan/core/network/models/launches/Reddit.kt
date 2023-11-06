package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.Serializable

@Serializable
data class Reddit(
    val campaign: String,
    val launch: String,
    val media: String,
    val recovery: String,
)