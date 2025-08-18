package com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.profilescreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.disastermanagmentapp.core.navigation.Graphs
import com.example.disastermanagmentapp.core.navigation.Routes
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.component.MyBottomNavBar
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.component.MyTopAppBar
import com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen.LogInScreenViewModel
import com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen.LoginUiState

@Composable
fun ProfileScreen(
    navController : NavHostController,
    viewModel : LogInScreenViewModel= hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(state.value) {
        when (val s = state.value) {
            is LoginUiState.Unauthorized -> {
                navController.navigate(Graphs.Auth) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                    restoreState = false
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
    ) {
    Box(modifier =Modifier.padding(it) ){
        Column(modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Profile Page")
            Button(
                onClick = {
                    viewModel.signOut()
                }
            ) {
                Text(text = "Sign out")
            }
        }

    }

    }


}