package com.example.disastermanagmentapp.feature_disastermanagement.data.repository

import com.example.disastermanagmentapp.feature_disastermanagement.data.mapper.toDomain
import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.api.DisasterApiService
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DisasterRepositoryImpl @Inject constructor(
    private val apiService: DisasterApiService
) : DisasterRepository {

    override suspend fun getDisasterEvents(): Flow<List<DisasterEvent>> = flow {
        try {
            val response = apiService.getDisasterEvents()
            emit(response.events.map { it.toDomain() })
        } catch (e: Exception) {
            // In a real app, you might want to emit an error state or fallback to cached data
            emit(emptyList())
        }
    }

    override suspend fun getDisasterEventById(id: String): DisasterEvent? {
        return try {
            val response = apiService.getDisasterEventById(id)
            response.events.firstOrNull()?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchDisasterEvents(query: String): Flow<List<DisasterEvent>> = flow {
        try {
            val response = apiService.searchDisasterEvents(query)
            emit(response.events.map { it.toDomain() })
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override suspend fun getDisasterEventsByCategory(categoryId: String): Flow<List<DisasterEvent>> = flow {
        try {
            val response = apiService.getDisasterEventsByCategory(categoryId)
            emit(response.events.map { it.toDomain() })
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
