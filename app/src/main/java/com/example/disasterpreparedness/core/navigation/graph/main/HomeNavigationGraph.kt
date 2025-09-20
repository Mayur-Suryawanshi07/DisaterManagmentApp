package com.example.disasterpreparedness.core.navigation.graph.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.disasterpreparedness.core.navigation.destination.Graph
import com.example.disasterpreparedness.core.navigation.destination.Routes
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.contact_screen.ContactScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_detail_screen.DisasterDetailScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_screen.DisasterScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen.ProfileScreen


fun NavGraphBuilder.homeNavigationGraph(navController: NavHostController) {

    navigation<Graph.Main>(startDestination = Routes.Disaster){
        composable<Routes.Disaster> {
            DisasterScreen(navController = navController)
        }

        composable<Routes.Contact> {
            ContactScreen(navController = navController)
        }

        composable<Routes.Profile> {
            ProfileScreen(navController = navController)
        }

        composable<Routes.DisasterDetail> { backStackEntry ->
            // The disasterId is automatically available through type-safe navigation
            // and will be accessible in the SavedStateHandle for the ViewModel
            DisasterDetailScreen(navController = navController)
        }

    }

}
