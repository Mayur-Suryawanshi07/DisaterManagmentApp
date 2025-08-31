package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_detail_screen

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterDetailAlert

data class DisasterDetailState(
    val uiState: DisasterDetailUiState = DisasterDetailUiState.Loading,
    val isLoading: Boolean = false
)
sealed interface DisasterDetailUiState {
    data object Loading : DisasterDetailUiState
    data class Success(val disaster: DisasterDetailAlert) : DisasterDetailUiState
    data class SuccessRss(val disaster: DisasterAlert) : DisasterDetailUiState
    data class Error(val message: String) : DisasterDetailUiState
}
