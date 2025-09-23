package com.example.disasterpreparedness.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.disasterpreparedness.core.navigation.graph.AppNavigationGraph
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyBottomNavBar
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyTopAppBar

@Composable
fun DisasterPreparednessApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentRoute = currentDestination?.route ?: ""
    val isMainFlow = currentRoute.contains("Main") || 
                     currentRoute.contains("Disaster") ||
                     currentRoute.contains("Contact") ||
                     currentRoute.contains("Profile") ||
                     currentRoute.contains("DisasterDetail")

    val topBarTitle = when {
        currentRoute.contains("Disaster") && !currentRoute.contains("Detail") -> "Disaster Preparedness"
        currentRoute.contains("Contact") -> "Emergency Contacts"
        currentRoute.contains("Profile") -> "Profile"
        currentRoute.contains("DisasterDetail") -> "Disaster Details"
        else -> "Disaster Preparedness"
    }

    Scaffold(
        topBar = {
            if (isMainFlow) {
                MyTopAppBar(title = topBarTitle)
            }
        },
        bottomBar = {
            if (isMainFlow) {
                MyBottomNavBar(navController = navController)
            }
        }
    ) { paddingValues ->
        AppNavigationGraph(
            modifier = modifier.padding(paddingValues), 
            navController = navController
        )
    }
}