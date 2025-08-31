package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.disasterpreparedness.core.navigation.Graphs
import com.example.disasterpreparedness.core.navigation.Routes
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyBottomNavBar
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyTopAppBar
import com.example.disasterpreparedness.feature_login.presentation.auth.loginscreen.LogInScreenViewModel
import com.example.disasterpreparedness.feature_login.presentation.auth.loginscreen.LoginUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModelLogin: LogInScreenViewModel = hiltViewModel(),

    navController: NavHostController
) {
    val state =viewModelLogin.state.collectAsState()

    LaunchedEffect(state.value) {
        when(state.value){
            is LoginUiState.Unauthorized -> {
                navController.navigate(Routes.Login){
                    popUpTo(Graphs.Auth){inclusive = true}
                }
            }
            else -> Unit
        }

    }

    Scaffold(
        topBar = {
            MyTopAppBar("Profile")
        },
        bottomBar = {
            MyBottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            Button(
                onClick = {
                    viewModelLogin.signOut()
                    Log.d("ProfileScreen", "Logout button clicked")
                }
            ) {
                Text(text = "Sign out")
            }
        }
    }
}
