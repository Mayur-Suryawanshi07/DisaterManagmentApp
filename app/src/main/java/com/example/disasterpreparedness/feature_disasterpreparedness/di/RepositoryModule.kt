package com.example.disasterpreparedness.feature_disasterpreparedness.di

import com.example.disasterpreparedness.feature_disasterpreparedness.data.repository.DisasterDetailRepoImp
import com.example.disasterpreparedness.feature_disasterpreparedness.data.repository.DisasterRepositoryImpl
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterDetailRepository
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterRepository
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
