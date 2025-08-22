package com.example.disastermanagmentapp.feature_disastermanagement.domain.repository

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterDetailAlert
import kotlinx.coroutines.flow.Flow

interface DisasterDetailRepository {
    suspend fun getDisasterDetail(identifier: String): DisasterDetailAlert?
}
