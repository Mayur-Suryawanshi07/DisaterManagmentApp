package com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.DisasterUseCases

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterAlert
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DisasterByCategoryUseCase @Inject constructor(
    private val repository: DisasterRepository
) {
    operator fun invoke(categoryId: String): Flow<List<DisasterAlert>> {
        return repository.getDisasterEventsByCategory(categoryId)
    }
}