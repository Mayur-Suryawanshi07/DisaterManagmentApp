package com.example.disastermanagmentapp.feature_login.presentation.auth.signupscreen

sealed class SignUpState {
    object Authenticated : SignUpState()
    object Unauthenticated : SignUpState()

    object Loading : SignUpState()
    data class Error(val message:String): SignUpState()
}