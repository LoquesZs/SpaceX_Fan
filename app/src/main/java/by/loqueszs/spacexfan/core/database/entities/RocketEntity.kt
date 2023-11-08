package by.loqueszs.spacexfan.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RocketEntity.TABLE_NAME)
data class RocketEntity(
    @PrimaryKey
    val id: String,

    val name: String,
    val image: String
) {

    companion object {
        const val TABLE_NAME = "rockets"
    }
}
