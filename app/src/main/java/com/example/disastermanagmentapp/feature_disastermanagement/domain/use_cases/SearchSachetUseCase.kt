package com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterAlert
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.SachetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchSachetUseCase @Inject constructor(
    private val repository: SachetRepository
) {
    operator fun invoke(query: String): Flow<List<DisasterAlert>> {
        if (query.isEmpty()){
            throw IllegalArgumentException("Search query cannot be empty")
        }
        if (query.length < 2){
            throw IllegalArgumentException("Search query must be at least 2 characters")
        }
        return repository.searchDisasterEvents(query.trim())
    }
}