package com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SignUpViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    val database = Firebase.database
    val usersRef = database.getReference("users")

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



    fun signup(username: String, email: String, password: String) {
        _state.update {
            SignUpState.Loading
        }

        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            _state.update {
                SignUpState.Error("Email,Password and Username Cannot be empty")
            }
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    storeDataFireBase(username, email)
                    _state.update { SignUpState.Authenticated }
                } else {
                    _state.update { SignUpState.Error(task.exception?.message ?: "Unknown error") }
                }
            }
    }

    fun storeDataFireBase(username: String, email: String) {
        val userId = auth.currentUser?.uid ?: return
        val user = User(userId, username, email)

        usersRef
            .child(userId)
            .setValue(user)
            .addOnSuccessListener {
                Log.d("FirebaseDB", "User data stored successfully")
            }
            .addOnFailureListener {
                Log.e("FirebaseDB", "Error storing user data", it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        // Clean up any listeners if needed
        Log.d("FirebaseDB", "SignUpViewModel cleared")
    }


}



