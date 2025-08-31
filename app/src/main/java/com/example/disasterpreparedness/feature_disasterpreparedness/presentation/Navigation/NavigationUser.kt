package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.Navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.disasterpreparedness.core.navigation.Graphs
import com.example.disasterpreparedness.core.navigation.Routes
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.contact_screen.ContactScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_detail_screen.DisasterDetailScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_screen.DisasterScreen
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen.ProfileScreen


fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {

    navigation<Graphs.Main>(startDestination = Routes.Home){
        composable<Routes.Home> {
            DisasterScreen(navController = navController)
        }

        composable<Routes.Contact> {
            ContactScreen(navController = navController)
        }

        composable<Routes.Profile> {
            ProfileScreen(navController = navController)
        }

        composable<Routes.DisasterDetail> {
            DisasterDetailScreen(navController = navController)
        }

    }

}
