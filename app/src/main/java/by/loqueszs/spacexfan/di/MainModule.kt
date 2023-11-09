package by.loqueszs.spacexfan.di

import by.loqueszs.spacexfan.data.SpaceXFanRepositoryImpl
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface MainModule {

    @Binds
    @ViewModelScoped
    fun bindSpaceXFanRepository(repository: SpaceXFanRepositoryImpl): SpaceXFanRepository
}