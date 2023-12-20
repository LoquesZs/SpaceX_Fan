package by.loqueszs.spacexfan.core.network

import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXFanApiService {

    @GET("rockets")
    suspend fun getRockets(): List<Rocket>

    @GET("rockets/{id}")
    suspend fun getRocketByID(
        @Path("id") id: String
    ): Rocket

    @GET("launches")
    suspend fun getLaunches(): List<Launch>

    @GET("launches/{id}")
    suspend fun getLaunchByID(
        @Path("id") id: String
    ): Launch
}
