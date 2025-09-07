package com.example.disasterpreparedness.core.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.disasterpreparedness.core.navigation.destination.Contact.ContactDestination
import com.example.disasterpreparedness.core.navigation.destination.disaster.DisasterDestination
import com.example.disasterpreparedness.core.navigation.destination.disasterdetail.DisasterDetailDestination
import com.example.disasterpreparedness.core.navigation.destination.main.MainDestination
import com.example.disasterpreparedness.core.navigation.destination.profile.ProfileDestination
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.contact_screen.ContactScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_detail_screen.DisasterDetailScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_screen.DisasterScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen.ProfileScreen


fun NavGraphBuilder.homeNavigationGraph(navController: NavHostController) {

    navigation<MainDestination>(startDestination = DisasterDestination){
        composable<DisasterDestination> {
            DisasterScreen(navController = navController)
        }

        composable<ContactDestination> {
            ContactScreen(navController = navController)
        }

        composable<ProfileDestination> {
            ProfileScreen(navController = navController)
        }

        composable<DisasterDetailDestination> {
            DisasterDetailScreen(navController = navController)
        }

    }

}
