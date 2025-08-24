package com.example.disastermanagmentapp.feature_disastermanagement.di

import com.example.disastermanagmentapp.feature_disastermanagement.data.repository.DisasterDetailRepoImp
import com.example.disastermanagmentapp.feature_disastermanagement.data.repository.DisasterRepositoryImpl
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterDetailRepository
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun DisasterRepository(
        disasterRepositoryImpl: DisasterRepositoryImpl
    ): DisasterRepository

    @Binds
    @Singleton
    abstract fun DisasterDetailRepository(
        disasterDetailRepoImp: DisasterDetailRepoImp
    ): DisasterDetailRepository

}
