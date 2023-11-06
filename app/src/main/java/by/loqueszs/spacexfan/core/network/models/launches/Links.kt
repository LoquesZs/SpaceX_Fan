package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val patch: Patch,
    val reddit: Reddit,
    val flickr: Flickr,
    val presskit: String,
    val webcast: String,
    @SerialName("youtube_id")
    val youtubeId: String,
    val article: String,
    val wikipedia: String,
)