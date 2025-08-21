package com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases

import javax.inject.Inject

data class SachetUseCase @Inject constructor(
    val getSachetUseCase: GetSachetUseCase,
    val getEventByID: GetSachetByIdUseCase,
    val searchDisaster: SearchSachetUseCase,
    val getSachetByCategoryUseCase: GetSachetByCategoryUseCase
)
