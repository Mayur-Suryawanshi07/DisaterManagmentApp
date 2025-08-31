package com.example.disasterpreparedness.feature_disasterpreparedness.domain.use_cases.DisasterUseCases

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DisasterByCategoryUseCase @Inject constructor(
    private val repository: DisasterRepository
) {
    operator fun invoke(categoryId: String): Flow<List<DisasterAlert>> {
        return repository.getDisasterEventsByCategory(categoryId)
    }
}
