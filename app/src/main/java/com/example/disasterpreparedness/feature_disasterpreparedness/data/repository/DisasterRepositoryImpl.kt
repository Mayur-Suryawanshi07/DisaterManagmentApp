package com.example.disasterpreparedness.feature_disasterpreparedness.data.repository

import com.example.disasterpreparedness.feature_disasterpreparedness.data.mapper.DisasterMapper.toDomain
import com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.api.SachetApiService
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DisasterRepositoryImpl @Inject constructor(
    private val apiService: SachetApiService
) : DisasterRepository {

    private var cachedAlerts: List<DisasterAlert>? = null

    override fun getDisasterEvents(): Flow<List<DisasterAlert>> = flow {
        try {
            // Try to get from cache first
            cachedAlerts?.let {
                emit(it)
            }

            // Fetch fresh data
            val alerts = getDisasterEventsFromNetwork()
            emit(alerts)

        } catch (e: HttpException) {
            // Handle HTTP errors
            emit(cachedAlerts ?: emptyList())
        } catch (e: IOException) {
            // Handle network errors
            emit(cachedAlerts ?: emptyList())
        } catch (e: Exception) {
            // Handle any other errors
            emit(cachedAlerts ?: emptyList())
        }
    }

    override suspend fun getDisasterByID(id: String): DisasterAlert? {
        return cachedAlerts?.find { it.id == id } ?: getDisasterEventsFromNetwork().find { it.id == id }
    }

    override fun searchDisasterEvents(query: String): Flow<List<DisasterAlert>> = flow {
        try {
            val allAlerts = getDisasterEventsFromNetwork()
            val filteredAlerts = if (query.isBlank()) {
                allAlerts
            } else {
                allAlerts.filter { alert ->
                    alert.title.contains(query, ignoreCase = true) ||
                    alert.description.contains(query, ignoreCase = true) ||
                    alert.category?.contains(query, ignoreCase = true) == true
                }
            }
            emit(filteredAlerts)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override fun getDisasterEventsByCategory(categoryId: String): Flow<List<DisasterAlert>> = flow {
        try {
            val allAlerts = getDisasterEventsFromNetwork()
            val filteredAlerts = allAlerts.filter { alert ->
                alert.category?.equals(categoryId, ignoreCase = true) == true
            }
            emit(filteredAlerts)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    private suspend fun getDisasterEventsFromNetwork(): List<DisasterAlert> {
        return try {
            val response = apiService.getRssFeed()
            if (response.isSuccessful) {
                val dto = response.body() // <- should be DisasterDto
                val alerts = dto?.toDomain() ?: emptyList()
                cachedAlerts = alerts
                alerts
            } else {
                cachedAlerts ?: emptyList()
            }
        } catch (e: Exception) {
            cachedAlerts ?: emptyList()
        }
    }

}
