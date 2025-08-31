package com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // Current user data state
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        checkAuthentication()
        setupRealtimeUserListener()
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

    private fun setupRealtimeUserListener() {
        val currentUserId = auth.currentUser?.uid ?: return

        val userRef = usersRef.child(currentUserId)
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                viewModelScope.launch {
                    _currentUser.update { user }
                    Log.d("FirebaseDB", "User data updated in realtime: $user")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseDB", "Failed to read user data", error.toException())
            }
        })
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
                setupRealtimeUserListener()
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

    data class User(
        val userId: String = "",
        val name: String = "",
        val email: String = ""
    )
}



