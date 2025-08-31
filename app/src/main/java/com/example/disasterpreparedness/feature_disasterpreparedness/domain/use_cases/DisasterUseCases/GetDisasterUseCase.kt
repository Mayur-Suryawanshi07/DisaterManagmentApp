package com.example.disasterpreparedness.feature_disasterpreparedness.domain.use_cases.DisasterUseCases

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDisasterUseCase @Inject constructor(
    private val repository: DisasterRepository
) {
    operator fun invoke(): Flow<List<DisasterAlert>> {
        return repository.getDisasterEvents()
    }

}
