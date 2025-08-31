package com.example.disasterpreparedness.feature_login.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.disasterpreparedness.core.navigation.Graphs
import com.example.disasterpreparedness.core.navigation.Routes
import com.example.disasterpreparedness.feature_login.presentation.auth.loginscreen.LogInScreen
import com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen.SignUpScreen


fun NavGraphBuilder.authNavScreen( navController: NavHostController) {
    navigation<Graphs.Auth>(startDestination = Routes.Login){
        composable<Routes.Login> {
            LogInScreen(navController = navController)
        }
        composable<Routes.Signup> {
            SignUpScreen(navController = navController)
        }

    }
}

