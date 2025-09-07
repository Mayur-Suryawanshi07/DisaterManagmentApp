package com.example.disasterpreparedness.core.navigation.graph


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.disasterpreparedness.core.navigation.destination.auth.AuthDestination
import com.example.disasterpreparedness.core.navigation.destination.login.LoginDestination
import com.example.disasterpreparedness.core.navigation.destination.signup.SignUpDestination
import com.example.disasterpreparedness.feature_login.presentation.auth.loginscreen.LogInScreen
import com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen.SignUpScreen

fun NavGraphBuilder.authNavigationGraph( navController: NavHostController) {
    navigation<AuthDestination>(startDestination = LoginDestination){
        composable<LoginDestination> {
            LogInScreen(navController = navController)
        }
        composable<SignUpDestination> {
            SignUpScreen(navController = navController)
        }

    }
}

