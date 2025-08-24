package com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.DisasterUseCases

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterAlert
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterRepository
import javax.inject.Inject

class DisasterByIdUseCase @Inject constructor(
    private val repository : DisasterRepository
) {
    suspend operator fun invoke(id: String): DisasterAlert? {
        return repository.getDisasterByID(id)
    }
}