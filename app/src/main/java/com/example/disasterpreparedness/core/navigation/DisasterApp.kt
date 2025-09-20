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

    // Determine if we're in auth flow or main flow
    val currentRoute = currentDestination?.route ?: ""
    val isAuthFlow = currentRoute.contains("Auth") || 
                     currentRoute.contains("Login") ||
                     currentRoute.contains("SignUp")
    val isMainFlow = currentRoute.contains("Main") || 
                     currentRoute.contains("Disaster") ||
                     currentRoute.contains("Contact") ||
                     currentRoute.contains("Profile") ||
                     currentRoute.contains("DisasterDetail")
    
    // Debug: Print current route for troubleshooting
    // println("Current Route: $currentRoute, isMainFlow: $isMainFlow, isAuthFlow: $isAuthFlow")

    // Get the appropriate title for the top bar
    val topBarTitle = when {
        currentRoute.contains("Disaster") && !currentRoute.contains("Detail") -> "Disaster Preparedness"
        currentRoute.contains("Contact") -> "Emergency Contacts"
        currentRoute.contains("Profile") -> "Profile"
        currentRoute.contains("DisasterDetail") -> "Disaster Details"
        else -> "Disaster Preparedness"
    }

    Scaffold(
        topBar = {
            // Only show top bar for main flow screens, not auth screens
            if (isMainFlow) {
                MyTopAppBar(title = topBarTitle)
            }
        },
        bottomBar = {
            // Only show bottom navigation for main flow screens, not auth screens
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