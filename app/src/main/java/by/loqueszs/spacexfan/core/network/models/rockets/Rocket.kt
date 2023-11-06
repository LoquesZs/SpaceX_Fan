package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    val height: Diameter,
    val diameter: Diameter,
    val mass: Mass,

    @SerialName("first_stage")
    val firstStage: FirstStage,

    @SerialName("second_stage")
    val secondStage: SecondStage,

    val engines: Engines,

    @SerialName("landing_legs")
    val landingLegs: LandingLegs,

    @SerialName("payload_weights")
    val payloadWeights: List<PayloadWeight>,

    @SerialName("flickr_images")
    val flickrImages: List<String>,

    val name: String,
    val type: String,
    val active: Boolean,
    val stages: Long,
    val boosters: Long,

    @SerialName("cost_per_launch")
    val costPerLaunch: Long,

    @SerialName("success_rate_pct")
    val successRatePct: Long,

    @SerialName("first_flight")
    val firstFlight: String,

    val country: String,
    val company: String,
    val wikipedia: String,
    val description: String,
    val id: String
)