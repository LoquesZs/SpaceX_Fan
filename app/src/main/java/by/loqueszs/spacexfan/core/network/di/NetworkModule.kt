package by.loqueszs.spacexfan.core.network.di

import by.loqueszs.spacexfan.core.network.SpaceXFanApiService
import by.loqueszs.spacexfan.core.network.retrofit.RetrofitClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitClient(): RetrofitClientFactory {
        return RetrofitClientFactory()
    }

    @Provides
    fun provideSpaceXFanNetworkDataSource(retrofitClientFactory: RetrofitClientFactory): SpaceXFanApiService {
        return retrofitClientFactory.getRetrofit().create(SpaceXFanApiService::class.java)
    }
}