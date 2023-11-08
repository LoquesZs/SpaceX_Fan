package by.loqueszs.spacexfan.core.network.models.rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    val height: Diameter? = null,
    val diameter: Diameter? = null,
    val mass: Mass? = null,

    @SerialName("first_stage")
    val firstStage: FirstStage? = null,

    @SerialName("second_stage")
    val secondStage: SecondStage? = null,

    val engines: Engines? = null,

    @SerialName("landing_legs")
    val landingLegs: LandingLegs? = null,

    @SerialName("payload_weights")
    val payloadWeights: List<PayloadWeight> = emptyList(),

    @SerialName("flickr_images")
    val flickrImages: List<String> = emptyList(),

    val name: String? = null,
    val type: String? = null,
    val active: Boolean? = null,
    val stages: Long? = null,
    val boosters: Long? = null,

    @SerialName("cost_per_launch")
    val costPerLaunch: Long? = null,

    @SerialName("success_rate_pct")
    val successRatePct: Long? = null,

    @SerialName("first_flight")
    val firstFlight: String? = null,

    val country: String? = null,
    val company: String? = null,
    val wikipedia: String? = null,
    val description: String? = null,
    val id: String
)