package com.example.disastermanagmentapp.feature_disastermanagement.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.Interface.DisasterListForCategory
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.EmergencyContactScreen.EmergencyCallList
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.HomeScreen.AboutScreen
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.MapScreen.MapScreen


@Composable
fun Nav(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.AllScreen.route
    ) {
        composable(Screen.AllScreen.route) {
            DisasterListForCategory(categoryName = "all") // Show all events
        }
        composable(Screen.Earthquake.route) {
            DisasterListForCategory(categoryName = "Earthquakes")
        }
        composable(Screen.FireScreen.route) {
            DisasterListForCategory(categoryName = "Wildfires") // Or "Fires" - check API
        }
        composable(Screen.Cyclone.route) {  // Added Cyclone route
            DisasterListForCategory(categoryName = "Severe Storms") // Or "Tropical Cyclone"
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
        composable(Screen.Flood.route) {
            DisasterListForCategory(categoryName = "Flood")
        }
        composable(Screen.EmergencyCallList.route){
            EmergencyCallList()
        }
        composable(Screen.Map.route){
            MapScreen()
        }
    }
}
//mew