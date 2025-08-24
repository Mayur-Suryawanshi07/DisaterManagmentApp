package com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.DisasterDetailUseCase

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterDetailAlert
import com.example.disastermanagmentapp.feature_disastermanagement.domain.repository.DisasterDetailRepository
import javax.inject.Inject

class GetDisasterDetailUseCase @Inject constructor(
    private val repository: DisasterDetailRepository
) {
    suspend operator fun invoke(identifier: String): DisasterDetailAlert? {
        return repository.getDisasterDetail(identifier)
    }
}