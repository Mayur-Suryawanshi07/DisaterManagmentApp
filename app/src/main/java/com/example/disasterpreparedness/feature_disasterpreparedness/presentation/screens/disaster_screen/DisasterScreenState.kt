package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_screen

import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.Info

sealed class DisasterScreenUiState {
    object Loading : DisasterScreenUiState()
    data class Success(val events: List<DisasterAlert>) : DisasterScreenUiState()
    data class DisasterDetail (val disaster: List<DisasterDetail>) : DisasterScreenUiState()
    data class SuccessInfo(val info: List<Info>) : DisasterScreenUiState()
    data class Error(val message: String) : DisasterScreenUiState()
    object Empty : DisasterScreenUiState()
}

data class DisasterScreenState(
    val uiState: DisasterScreenUiState = DisasterScreenUiState.Loading,
    val searchQuery: String = "",
    val selectedCategory: String? = null,
    val isRefreshing: Boolean = false
)
