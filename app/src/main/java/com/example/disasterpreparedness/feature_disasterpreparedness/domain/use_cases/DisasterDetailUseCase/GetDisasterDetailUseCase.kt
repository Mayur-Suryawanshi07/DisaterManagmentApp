package com.example.disasterpreparedness.feature_disasterpreparedness.domain.use_cases.DisasterDetailUseCase

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterDetailAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterDetailRepository
import javax.inject.Inject

class GetDisasterDetailUseCase @Inject constructor(
    private val repository: DisasterDetailRepository
) {
    suspend operator fun invoke(identifier: String): DisasterDetailAlert? {
        return repository.getDisasterDetail(identifier)
    }
}
