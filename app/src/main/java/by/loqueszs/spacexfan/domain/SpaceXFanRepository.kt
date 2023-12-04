package by.loqueszs.spacexfan.domain

import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface SpaceXFanRepository {

    fun getRockets(): Single<List<Rocket>>

    fun getRocketByID(id: String): Single<Rocket>

    fun getLaunches(): Single<List<Launch>>

    fun getLaunchByID(id: String): Single<Launch>

    fun addToFavorites(rocketEntity: RocketEntity): Completable

    fun deleteFromFavorites(id: String): Completable

    fun getFavorites(): Flowable<List<RocketEntity>>
}