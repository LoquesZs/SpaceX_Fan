package by.loqueszs.spacexfan.data

import by.loqueszs.spacexfan.core.database.dao.RocketsDao
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.SpaceXFanApiService
import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpaceXFanRepositoryImpl @Inject constructor(
    private val apiService: SpaceXFanApiService,
    private val dao: RocketsDao
) : SpaceXFanRepository {
    override fun getRockets(): Single<List<Rocket>> {
        return apiService.getRockets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLaunches(): Single<List<Launch>> {
        return apiService.getLaunches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRocketByID(id: String): Single<Rocket> {
        return apiService.getRocketByID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addToFavorites(rocketEntity: RocketEntity): Completable {
        return dao.addToFavorites(rocketEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteFromFavorites(id: String): Completable {
        return dao.deleteFromFavorites(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFavorites(): Flowable<List<RocketEntity>> {
        return dao.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}