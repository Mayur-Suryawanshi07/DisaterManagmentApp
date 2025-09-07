package com.example.disasterpreparedness.core.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.disasterpreparedness.core.navigation.destination.auth.AuthDestination


@Composable
fun AppNavigationGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthDestination,
        modifier = modifier
    ) {
        homeNavigationGraph(navController = navController)
        authNavigationGraph(navController = navController)
    }
}
