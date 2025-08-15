package com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen

sealed class LoginUiState{
    object Authorized: LoginUiState()
    object Unauthorized : LoginUiState()
    object Loading: LoginUiState()
    data class Error(val message: String): LoginUiState()
}

