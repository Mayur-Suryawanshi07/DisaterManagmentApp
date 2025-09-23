package com.example.disasterpreparedness.feature_login.presentation.auth.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen.LoginEvent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LogInScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow<LoginUiState>(LoginUiState.Unauthorized)
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    val auth = FirebaseAuth.getInstance()

    init {
        checkAuthentication()
    }

    fun checkAuthentication() {
        if (auth.currentUser == null) {
            _state.update {
                LoginUiState.Unauthorized
            }
        } else {
            _state.update {
                LoginUiState.Authorized
            }
        }
    }

    fun login(email: String, password: String) {
        _state.update {
            LoginUiState.Loading
        }
        if (email.isEmpty() || password.isEmpty()) {
            viewModelScope.launch{

                _event.emit(LoginEvent.ShowMessage(message = "Email or Password can't be Empty"))
            }
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _state.update {
                        LoginUiState.Authorized
                    }
                } else {
                    _state.update {
                        LoginUiState.Unauthorized
                    }
                    _state.update {
                        LoginUiState.Error(message = "Email is not Registered")
                    }
                }
            }
    }


}
