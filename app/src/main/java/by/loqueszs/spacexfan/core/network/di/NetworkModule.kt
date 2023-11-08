package by.loqueszs.spacexfan.core.network.di

import by.loqueszs.spacexfan.core.network.SpaceXFanApiService
import by.loqueszs.spacexfan.core.network.retrofit.RetrofitClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClientFactory {
        return RetrofitClientFactory()
    }

    @Provides
    @Singleton
    fun provideSpaceXFanNetworkDataSource(retrofitClientFactory: RetrofitClientFactory): SpaceXFanApiService {
        return retrofitClientFactory.getRetrofit().create(SpaceXFanApiService::class.java)
    }
}