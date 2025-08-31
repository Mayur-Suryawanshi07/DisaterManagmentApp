package com.example.disasterpreparedness.feature_disasterpreparedness.domain.use_cases.DisasterUseCases

import javax.inject.Inject

data class DisasterUseCase @Inject constructor(
    val getDisasterUseCase: GetDisasterUseCase,
    val getEventByID: DisasterByIdUseCase,
    val searchDisaster: DisasterSearchUseCase,
    val disasterByCategoryUseCase: DisasterByCategoryUseCase
)
