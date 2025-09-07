package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen

data class ProfileScreenState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
