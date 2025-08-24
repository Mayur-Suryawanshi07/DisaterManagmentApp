package com.example.disastermanagmentapp.feature_disastermanagement.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.disastermanagmentapp.core.navigation.Graphs
import com.example.disastermanagmentapp.core.navigation.Routes
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.contact_screen.ContactScreen
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.disaster_detail_screen.DisasterDetailScreen
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.disaster_screen.DisasterScreen
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.profilescreen.ProfileScreen


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
