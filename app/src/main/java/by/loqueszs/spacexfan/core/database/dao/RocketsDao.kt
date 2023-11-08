package by.loqueszs.spacexfan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface RocketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(rocketEntity: RocketEntity): Completable

    @Query("DELETE FROM ${RocketEntity.TABLE_NAME} WHERE id = :id")
    fun deleteFromFavorites(id: String): Completable

    @Query("SELECT * FROM ${RocketEntity.TABLE_NAME}")
    fun getFavorites(): Flowable<List<RocketEntity>>
}