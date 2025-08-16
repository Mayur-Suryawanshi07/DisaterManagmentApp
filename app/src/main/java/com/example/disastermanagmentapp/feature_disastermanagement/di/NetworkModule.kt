package com.example.disastermanagmentapp.feature_disastermanagement.di

import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.api.DisasterApiService
import com.example.disastermanagmentapp.feature_disastermanagement.data.repository.DisasterRepositoryImpl
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterRepository
import com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.DisasterUseCase
import com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.GetDisasterEventByIdUseCase
import com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.GetDisasterEventsUseCase
import com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.SearchDisasterEventsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://eonet.gsfc.nasa.gov/api/v2.1/") // NASA EONET API v2.1
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDisasterApiService(retrofit: Retrofit): DisasterApiService {
        return retrofit.create(DisasterApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDisasterRepository(impl: DisasterRepositoryImpl): DisasterRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: DisasterRepository) : DisasterUseCase{
        return DisasterUseCase(
            getDisasterUseCase = GetDisasterEventsUseCase(repository),
            getEventByID = GetDisasterEventByIdUseCase(repository),
            searchDisaster = SearchDisasterEventsUseCase(repository)
        )
    }
}
