package com.example.disastermanagmentapp.feature_login.presentation.auth.signupscreen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen.LoginUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignUpViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow<SignUpState>(SignUpState.Unauthenticated)
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    init {
        checkAuthentication()
    }

    fun checkAuthentication() {
        if (auth.currentUser == null) {
            _state.update {
                SignUpState.Unauthenticated
            }
        } else {
            _state.update {
                SignUpState.Authenticated
            }
        }
    }

    fun signup(email: String, password: String) {
        _state.update {
            SignUpState.Loading
        }

        if (email.isEmpty() || password.isEmpty()) {
            _state.update {
                SignUpState.Error("Email and Password Cannot be empty")
            }
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _state.update {
                        SignUpState.Authenticated
                    }
                } else {
                    _state.update {
                        SignUpState.Unauthenticated
                    }
                }
            }
    }




}

