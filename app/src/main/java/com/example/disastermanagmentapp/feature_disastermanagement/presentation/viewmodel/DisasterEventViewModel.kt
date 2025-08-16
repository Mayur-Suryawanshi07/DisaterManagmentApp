package com.example.disastermanagmentapp.feature_disastermanagement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.DisasterUseCase
import com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.GetDisasterEventsUseCase
import com.example.disastermanagmentapp.feature_disastermanagement.domain.use_cases.SearchDisasterEventsUseCase
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.state.DisasterEventListState
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.state.DisasterEventUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisasterEventViewModel @Inject constructor(
    private val useCases : DisasterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DisasterEventListState())
    val uiState: StateFlow<DisasterEventListState> = _uiState.asStateFlow()

    init {
        loadDisasterEvents()
    }

    fun loadDisasterEvents() {
        viewModelScope.launch {
            _uiState.update { it.copy(uiState = DisasterEventUiState.Loading) }
            
            try {
                useCases.getDisasterUseCase().collect { events ->
                    val newUiState = if (events.isEmpty()) {
                        DisasterEventUiState.Empty
                    } else {
                        DisasterEventUiState.Success(events)
                    }
                    _uiState.update { it.copy(uiState = newUiState) }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(uiState = DisasterEventUiState.Error(e.message ?: "Unknown error occurred"))
                }
            }
        }
    }

    fun searchDisasterEvents(query: String) {
        if (query.isBlank()) {
            loadDisasterEvents()
            return
        }

        viewModelScope.launch {
            _uiState.update { 
                it.copy(
                    searchQuery = query,
                    uiState = DisasterEventUiState.Loading
                )
            }
            
            try {
                useCases.searchDisaster(query).collect { events ->
                    val newUiState = if (events.isEmpty()) {
                        DisasterEventUiState.Empty
                    } else {
                        DisasterEventUiState.Success(events)
                    }
                    _uiState.update { it.copy(uiState = newUiState) }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(uiState = DisasterEventUiState.Error(e.message ?: "Search failed"))
                }
            }
        }
    }

    fun refreshEvents() {
        _uiState.update { it.copy(isRefreshing = true) }
        loadDisasterEvents()
        _uiState.update { it.copy(isRefreshing = false) }
    }

    fun clearSearch() {
        _uiState.update { it.copy(searchQuery = "") }
        loadDisasterEvents()
    }

    fun selectCategory(categoryId: String?) {
        _uiState.update { it.copy(selectedCategory = categoryId) }
        // TODO: Implement category filtering
    }

    fun retry() {
        loadDisasterEvents()
    }
}
