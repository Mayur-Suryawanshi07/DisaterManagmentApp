package com.example.disasterpreparedness.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.Navigation.mainNavGraph
import com.example.disasterpreparedness.feature_login.presentation.navigation.authNavScreen

@Composable
fun AppRootNav(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Graphs.Auth
    ) {
        mainNavGraph(navController = navController)
        authNavScreen(navController = navController)
    }
}
