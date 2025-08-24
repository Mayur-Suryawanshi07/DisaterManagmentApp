package com.example.disastermanagmentapp.feature_login.presentation.auth.signupscreen

import android.R
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen.LoginUiState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignUpViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    val database = Firebase.database
    val myRef = database.getReference("users")

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
                SignUpState.Error("Email,Password and Username Cannot be empty")
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

    fun storeDataFireBase(username: String, email: String) {
        val userId = auth.currentUser?.uid ?: return
        val user = User(userId, username, email)
        myRef.child(userId).setValue(user)

    }
}

data class User(
    val name: String,
    val email: String,
    val userId: String
)

