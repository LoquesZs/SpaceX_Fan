package by.loqueszs.spacexfan.core.database.di

import android.content.Context
import androidx.room.Room
import by.loqueszs.spacexfan.core.database.SpaceXFanDatabase
import by.loqueszs.spacexfan.core.database.dao.RocketsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SpaceXFanDatabase {
        return Room.databaseBuilder(
            context,
            SpaceXFanDatabase::class.java,
            SpaceXFanDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRocketsDao(db: SpaceXFanDatabase): RocketsDao = db.rocketsDao
}