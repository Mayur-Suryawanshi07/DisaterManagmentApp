package com.example.disasterpreparedness.core.navigation.graph

sealed class Screen(
    val route: String,
    val title: String,
    val showTopBar: Boolean = true,
    val showBottomBar: Boolean = false,
    val canNavigateBack: Boolean = false
) {
    object Home : Screen("home", "Disasters", showBottomBar = true)
    object Contact : Screen("contact", "Emergency Contacts", showBottomBar = true)
    object Profile : Screen("profile", "Profile", showBottomBar = true)
    object Login : Screen("login", "Log in", showTopBar = false)
    object Signup : Screen("signup", "Sign up", showTopBar = false)
    object DisasterDetail : Screen("disasterDetail/{id}", "Disaster Details")

    companion object {
        val allScreens = listOf(Home, Contact, Profile, Login, Signup, DisasterDetail)

        fun findByRoute(route: String?): Screen? {
            return allScreens.firstOrNull { screen ->
                // Support dynamic args for DisasterDetail
                if (screen.route.contains("{id}")) {
                    route?.startsWith(screen.route.substringBefore("/{")) == true
                } else {
                    screen.route == route
                }
            }
        }
    }
}
