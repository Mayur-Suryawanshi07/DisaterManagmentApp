package com.example.disasterpreparedness.feature_disasterpreparedness.data.repository

import com.example.disasterpreparedness.feature_disasterpreparedness.data.mapper.DisasterDetailMapper.toDomain
import com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.api.SachetApiService
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterDetailAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterDetailRepository
import javax.inject.Inject

class DisasterDetailRepoImp @Inject constructor(
    private val api : SachetApiService
): DisasterDetailRepository {
    override suspend fun getDisasterDetail(identifier: String): DisasterDetailAlert? {
        return try {
            val response =api.getRssFeedDetail(identifier)
            if (response.isSuccessful) {
                response.body()?.toDomain()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


}
