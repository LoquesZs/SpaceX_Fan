package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val patch: Patch? = null,
    val reddit: Reddit? = null,
    val flickr: Flickr? = null,
    val presskit: String? = null,
    val webcast: String? = null,
    @SerialName("youtube_id")
    val youtubeId: String? = null,
    val article: String? = null,
    val wikipedia: String? = null
)