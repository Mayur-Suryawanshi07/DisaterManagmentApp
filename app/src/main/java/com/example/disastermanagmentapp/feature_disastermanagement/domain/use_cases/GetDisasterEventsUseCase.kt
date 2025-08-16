package com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDisasterEventsUseCase @Inject constructor(
    private val repository: DisasterRepository
) {
    suspend operator fun invoke(): Flow<List<DisasterEvent>> {
        return repository.getDisasterEvents()
    }
}
