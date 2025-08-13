package com.example.disastermanagmentapp.feature_login.presentation.loginscreen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow<Boolean>(false)
    val state : StateFlow<Boolean> = _state.asStateFlow()


    // Username or Email
    var userName by mutableStateOf("")

    // Password
    var passWord by mutableStateOf("")

    // For showing loading state during login
    var isLoading by mutableStateOf(false)
        private set

    // For showing error messages
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Update username
    fun onUserNameChanged(newUserName: String) {
        userName = newUserName
    }

    // Update password
    fun onPasswordChanged(newPassword: String) {
        passWord = newPassword
    }
}