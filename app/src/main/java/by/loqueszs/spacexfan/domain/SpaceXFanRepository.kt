package by.loqueszs.spacexfan.domain

import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import kotlinx.coroutines.flow.Flow

interface SpaceXFanRepository {

    suspend fun getRockets(): List<Rocket>

    suspend fun getRocketByID(id: String): Rocket

    suspend fun getLaunches(): List<Launch>

    suspend fun getLaunchByID(id: String): Launch

    suspend fun addToFavorites(rocketEntity: RocketEntity)

    suspend fun deleteFromFavorites(id: String)
    suspend fun getFavorites(): Flow<List<RocketEntity>>
}
