package com.example.disastermanagmentapp.Interface

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Nav(modifier: Modifier, navController: NavHostController) {
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
    }
}