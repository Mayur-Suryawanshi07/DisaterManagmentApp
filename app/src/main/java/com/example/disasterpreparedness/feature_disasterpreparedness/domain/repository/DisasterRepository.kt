package com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import kotlinx.coroutines.flow.Flow

interface DisasterRepository {
    fun getDisasterEvents(): Flow<List<DisasterAlert>>
    suspend fun getDisasterByID(id: String): DisasterAlert?
    fun searchDisasterEvents(query: String): Flow<List<DisasterAlert>>
    fun getDisasterEventsByCategory(categoryId: String): Flow<List<DisasterAlert>>

}
