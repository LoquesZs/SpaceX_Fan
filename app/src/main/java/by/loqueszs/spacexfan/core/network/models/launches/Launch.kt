package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launch(
    val fairings: Fairings? = null,
    val links: Links? = null,
    @SerialName("static_fire_date_utc")
    val staticFireDateUtc: String? = null,
    @SerialName("static_fire_date_unix")
    val staticFireDateUnix: Long? = null,
    val tdb: Boolean? = null,
    val net: Boolean? = null,
    val window: Long? = null,
    val rocket: String? = null,
    val success: Boolean? = null,
    val failures: List<Failure>? = emptyList(),
    val details: String? = null,
    val crew: List<String>? = emptyList(),
    val ships: List<String> = emptyList(),
    val capsules: List<String> = emptyList(),
    val payloads: List<String> = emptyList(),
    val launchpad: String? = null,
    @SerialName("auto_update")
    val autoUpdate: Boolean? = null,
    @SerialName("flight_number")
    val flightNumber: Long? = null,
    val name: String? = null,
    @SerialName("date_utc")
    val dateUtc: String? = null,
    @SerialName("date_unix")
    val dateUnix: Long? = null,
    @SerialName("date_local")
    val dateLocal: String? = null,
    @SerialName("date_precision")
    val datePrecision: String? = null,
    val upcoming: Boolean? = null,
    val cores: List<Core>? = emptyList(),
    val id: String
)