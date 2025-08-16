package com.example.disastermanagmentapp.feature_disastermanagement.presentation.state

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent

sealed class DisasterEventUiState {
    object Loading : DisasterEventUiState()
    data class Success(val events: List<DisasterEvent>) : DisasterEventUiState()
    data class Error(val message: String) : DisasterEventUiState()
    object Empty : DisasterEventUiState()
}

data class DisasterEventListState(
    val uiState: DisasterEventUiState = DisasterEventUiState.Loading,
    val searchQuery: String = "",
    val selectedCategory: String? = null,
    val isRefreshing: Boolean = false
)
