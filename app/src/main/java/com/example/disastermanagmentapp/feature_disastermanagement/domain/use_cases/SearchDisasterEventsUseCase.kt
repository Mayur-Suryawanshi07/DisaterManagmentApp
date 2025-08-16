package com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDisasterEventsUseCase @Inject constructor(
    private val repository: DisasterRepository
) {
    suspend operator fun invoke(query: String): Flow<List<DisasterEvent>> {
        // Business logic: validate search query
        if (query.isBlank()) {
            throw IllegalArgumentException("Search query cannot be empty")
        }
        if (query.length < 2) {
            throw IllegalArgumentException("Search query must be at least 2 characters")
        }
        
        return repository.searchDisasterEvents(query.trim())
    }
}
