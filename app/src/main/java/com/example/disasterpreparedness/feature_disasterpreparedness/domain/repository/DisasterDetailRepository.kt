package com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterDetailAlert

interface DisasterDetailRepository {
    suspend fun getDisasterDetail(identifier: String): DisasterDetailAlert?
}
