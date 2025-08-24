package com.example.disastermanagmentapp.feature_disastermanagement.data.repository

import com.example.disastermanagmentapp.feature_disastermanagement.data.mapper.DisasterDetailMapper.toDomain
import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.api.SachetApiService
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterDetailAlert
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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