package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_detail_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disasterpreparedness.core.navigation.Routes
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterDetailRepository
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.repository.DisasterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisasterDetailViewModel @Inject constructor(
    private val disasterRepository: DisasterRepository,
    private val disasterDetailRepository: DisasterDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DisasterDetailState())
    val uiState: StateFlow<DisasterDetailState> = _uiState.asStateFlow()

    // Get the disaster ID from the navigation arguments
    private val disasterId: String = checkNotNull(savedStateHandle[Routes.DisasterDetail::disasterId.name])

    init {
        loadDisasterDetail()
    }

    private fun loadDisasterDetail() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(uiState = DisasterDetailUiState.Loading, isLoading = true)
            }

            try {
                val basicDisaster = disasterRepository.getDisasterByID(disasterId)
                
                if (basicDisaster != null) {
                    // Try to extract identifier from the RSS link or use the link itself
                    val potentialIdentifier = extractIdentifierFromLink(basicDisaster.link)

                    // Try to fetch detailed information
                    val detailedDisaster = disasterDetailRepository.getDisasterDetail(potentialIdentifier)

                    if (detailedDisaster != null) {
                        // Successfully got detailed information
                        _uiState.update {
                            it.copy(
                                uiState = DisasterDetailUiState.Success(detailedDisaster),
                                isLoading = false
                            )
                        }
                    } else {
                        // Fallback: Show enhanced RSS data
                        _uiState.update {
                            it.copy(
                                uiState = DisasterDetailUiState.SuccessRss(basicDisaster),
                                isLoading = false
                            )
                        }
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            uiState = DisasterDetailUiState.Error("Disaster detail not found"),
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        uiState = DisasterDetailUiState.Error(e.message ?: "Unknown error occurred"),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun extractIdentifierFromLink(link: String): String {
        return when {
            // If link contains a numeric identifier, extract it
            link.contains(Regex("\\d{10,}")) -> {
                Regex("\\d{10,}").find(link)?.value ?: link
            }
            // If link contains "identifier=" parameter, extract it
            link.contains("identifier=") -> {
                link.split("identifier=").getOrNull(1)?.split("&")?.firstOrNull() ?: link
            }
            // Otherwise, use the link as is
            else -> link
        }
    }

    fun retry() {
        loadDisasterDetail()
    }
}
