package com.example.disasterpreparedness.core.navigation.graph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController

import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyBottomNavBar
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyTopAppBar
import com.example.disasterpreparedness.core.navigation.destination.auth.AuthDestination

@Composable
fun DisasterNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(null)
    val currentRoute = backStackEntry?.destination?.route

    val isAuthGraph = currentRoute?.startsWith(AuthDestination::class.qualifiedName ?: "") == true
    val showBottomBar = listOf(
        Screen.Home.route,
        Screen.Contact.route,
        Screen.Profile.route
    ).any { route -> currentRoute?.startsWith(route) == true }

    val showTopBar = !isAuthGraph
    val title = when {
        currentRoute?.startsWith(Screen.Home.route) == true -> Screen.Home.title
        currentRoute?.startsWith(Screen.Contact.route) == true -> Screen.Contact.title
        currentRoute?.startsWith(Screen.Profile.route) == true -> Screen.Profile.title
        currentRoute?.startsWith(Screen.DisasterDetail.route.substringBefore("/{")) == true -> Screen.DisasterDetail.title
        else -> ""
    }

    Scaffold(
        topBar = {
            if (showTopBar) {
                MyTopAppBar(title = title)
            }
        },
        bottomBar = {
            if (showBottomBar) {
                MyBottomNavBar(navController)
            }
        }
    ) { innerPadding ->
        AppNavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

