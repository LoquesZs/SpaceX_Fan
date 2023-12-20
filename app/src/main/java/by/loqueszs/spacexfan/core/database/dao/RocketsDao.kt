package by.loqueszs.spacexfan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(rocketEntity: RocketEntity)

    @Query("DELETE FROM ${RocketEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteFromFavorites(id: String)

    @Query("SELECT * FROM ${RocketEntity.TABLE_NAME}")
    fun getFavorites(): Flow<List<RocketEntity>>
}
