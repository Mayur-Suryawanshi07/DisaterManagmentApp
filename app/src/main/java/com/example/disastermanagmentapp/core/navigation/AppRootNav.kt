package com.example.disastermanagmentapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.Navigation.mainNavGraph
import com.example.disastermanagmentapp.feature_login.presentation.navigation.authNavScreen

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