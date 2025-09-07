package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProfileScreenViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val usersRef = Firebase.database.getReference("users")


    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    // Current user data state
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()


    init {
        setupRealtimeUserListener()
    }

    private fun setupRealtimeUserListener() {
        val currentUserId = auth.currentUser?.uid ?: return

        val userRef = usersRef.child(currentUserId)
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                viewModelScope.launch {
                    _currentUser.update {
                        user
                    }
                    Log.d("FirebaseDB", "User data updated in realtime: $user")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseDB", "Failed to read user data", error.toException())
            }
        })
    }

    fun logOut() {
        auth.signOut()

    }
}

data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = ""
)


