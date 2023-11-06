package by.loqueszs.spacexfan.core.network.models.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launch(
    val fairings: Fairings,
    val links: Links,
    @SerialName("static_fire_date_utc")
    val staticFireDateUtc: String,
    @SerialName("static_fire_date_unix")
    val staticFireDateUnix: Long,
    val tdb: Boolean,
    val net: Boolean,
    val window: Long,
    val rocket: String,
    val success: Boolean,
    val failures: List<Failure>,
    val details: String,
    val crew: List<String>,
    val ships: List<String>,
    val capsules: List<String>,
    val payloads: List<String>,
    val launchpad: String,
    @SerialName("auto_update")
    val autoUpdate: Boolean,
    @SerialName("flight_number")
    val flightNumber: Long,
    val name: String,
    @SerialName("date_utc")
    val dateUtc: String,
    @SerialName("date_unix")
    val dateUnix: Long,
    @SerialName("date_local")
    val dateLocal: String,
    @SerialName("date_precision")
    val datePrecision: String,
    val upcoming: Boolean,
    val cores: List<Core>,
    val id: String
)