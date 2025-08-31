package com.example.disasterpreparedness.feature_disasterpreparedness.domain.use_cases.DisasterUseCases

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DisasterSearchUseCase @Inject constructor(
    private val repository: DisasterRepository
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
