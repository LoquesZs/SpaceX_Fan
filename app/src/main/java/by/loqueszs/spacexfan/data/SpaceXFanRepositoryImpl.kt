package by.loqueszs.spacexfan.data

import by.loqueszs.spacexfan.core.database.dao.RocketsDao
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.SpaceXFanApiService
import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class SpaceXFanRepositoryImpl @Inject constructor(
    private val apiService: SpaceXFanApiService,
    private val dao: RocketsDao
) : SpaceXFanRepository {
    override suspend fun getRockets(): List<Rocket> {
        return apiService.getRockets()
    }

    override suspend fun getLaunches(): List<Launch> {
        return apiService.getLaunches()
    }

    override suspend fun getLaunchByID(id: String): Launch {
        return apiService.getLaunchByID(id)
    }

    override suspend fun getRocketByID(id: String): Rocket {
        return apiService.getRocketByID(id)
    }

    override suspend fun addToFavorites(rocketEntity: RocketEntity) {
        return dao.addToFavorites(rocketEntity)
    }

    override suspend fun deleteFromFavorites(id: String) {
        return dao.deleteFromFavorites(id)
    }

    override suspend fun getFavorites(): Flow<List<RocketEntity>> {
        return dao.getFavorites()
    }
}
