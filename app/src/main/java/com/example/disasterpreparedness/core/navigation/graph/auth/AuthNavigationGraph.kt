package com.example.disasterpreparedness.core.navigation.graph.auth


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.disasterpreparedness.core.navigation.destination.Graph
import com.example.disasterpreparedness.core.navigation.destination.Routes
import com.example.disasterpreparedness.feature_login.presentation.auth.loginscreen.LogInScreen
import com.example.disasterpreparedness.feature_login.presentation.auth.signupscreen.SignUpScreen

fun NavGraphBuilder.authNavigationGraph( navController: NavHostController) {
    navigation<Graph.Auth>(startDestination = Routes.Login){
        composable<Routes.Login> {
            LogInScreen(navController = navController)
        }
        composable<Routes.SignUp> {
            SignUpScreen(navController = navController)
        }

    }
}

