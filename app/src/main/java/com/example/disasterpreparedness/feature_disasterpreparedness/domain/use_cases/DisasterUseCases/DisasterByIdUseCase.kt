package com.example.disasterpreparedness.feature_disasterpreparedness.domain.use_cases.DisasterUseCases

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterRepository
import javax.inject.Inject

class DisasterByIdUseCase @Inject constructor(
    private val repository : DisasterRepository
) {
    suspend operator fun invoke(id: String): DisasterAlert? {
        return repository.getDisasterByID(id)
    }
}
