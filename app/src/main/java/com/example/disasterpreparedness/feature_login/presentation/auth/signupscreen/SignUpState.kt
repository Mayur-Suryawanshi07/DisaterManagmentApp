package com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen

sealed class SignUpState {
    object Authenticated : SignUpState()
    object Unauthenticated : SignUpState()
    object Loading : SignUpState()
    data class Error(val message:String): SignUpState()
}

sealed class LoginEvent {
    data class ShowMessage(val message: String) : LoginEvent()
}
