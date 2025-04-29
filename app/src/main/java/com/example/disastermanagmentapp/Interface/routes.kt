package com.example.disastermanagmentapp.Interface


sealed class Screen(val route: String) {
        object AllScreen : Screen("all")
        object Earthquake : Screen("earthquakes")
        object FireScreen : Screen("fires")
        object Cyclone : Screen("cyclones") // Added Cyclone route
        object Flood : Screen("Flood")
        object About : Screen("about")
        object EmergencyCallList:Screen("emergencyCallList")
}

