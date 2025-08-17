package com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.disaster_screen

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent

sealed class DisasterScreenUiState {
    object Loading : DisasterScreenUiState()
    data class Success(val events: List<DisasterEvent>) : DisasterScreenUiState()
    data class Error(val message: String) : DisasterScreenUiState()
    object Empty : DisasterScreenUiState()
}

data class DisasterScreenState(
    val uiState: DisasterScreenUiState = DisasterScreenUiState.Loading,
    val searchQuery: String = "",
    val selectedCategory: String? = null,
    val isRefreshing: Boolean = false
)
