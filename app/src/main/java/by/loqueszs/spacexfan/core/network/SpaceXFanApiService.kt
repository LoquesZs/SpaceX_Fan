package by.loqueszs.spacexfan.core.network

import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXFanApiService {

    @GET("rockets")
    fun getRockets(): Single<List<Rocket>>

    @GET("rockets/{id}")
    fun getRocketByID(
        @Path("id") id: String
    ): Single<Rocket>

    @GET("launches")
    fun getLaunches(): Single<List<Launch>>

    @GET("launches/{id}")
    fun getLaunchByID(
        @Path("id") id: String
    ): Single<Launch>
}
