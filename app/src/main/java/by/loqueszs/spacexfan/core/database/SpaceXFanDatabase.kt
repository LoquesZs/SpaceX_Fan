package by.loqueszs.spacexfan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.loqueszs.spacexfan.core.database.dao.RocketsDao
import by.loqueszs.spacexfan.core.database.entities.RocketEntity

@Database(
    entities = [RocketEntity::class],
    version = 1
)
abstract class SpaceXFanDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "space_x_fan_db"
    }

    abstract val rocketsDao: RocketsDao
}