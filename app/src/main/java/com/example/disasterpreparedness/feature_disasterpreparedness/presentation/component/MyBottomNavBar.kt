package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ContactEmergency
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ContactEmergency
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.disasterpreparedness.core.navigation.destination.Graph
import com.example.disasterpreparedness.core.navigation.destination.Routes

@Composable
fun MyBottomNavBar(
    navController: NavHostController,
    items: List<NavigationBottomTheme> = defaultBottomNavItems()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            val selected = currentDestination?.hierarchy?.any {
                it.route == item.route.toString() } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    // Navigate to the specific route within the Main graph
                    when (item.route) {
                        is Routes.Disaster -> {
                            navController.navigate(Routes.Disaster) {
                                popUpTo(Graph.Main) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        is Routes.Contact -> {
                            navController.navigate(Routes.Contact) {
                                popUpTo(Graph.Main) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        is Routes.Profile -> {
                            navController.navigate(Routes.Profile) {
                                popUpTo(Graph.Main) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        else -> {Unit}
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) item.SelectedItems else item.UnSelectedItems,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

data class NavigationBottomTheme(
    val SelectedItems: ImageVector,
    val UnSelectedItems: ImageVector,
    val route: Routes,
    val title: String
)

fun defaultBottomNavItems() = listOf(
    NavigationBottomTheme(
        Icons.Filled.Home,
        Icons.Outlined.Home,
        route = Routes.Disaster,
        title = "Home"
    ),
    NavigationBottomTheme(
        Icons.Filled.ContactEmergency,
        Icons.Outlined.ContactEmergency,
        route = Routes.Contact,
        title = "Contact"
    ),
    NavigationBottomTheme(
        Icons.Filled.AccountCircle,
        Icons.Outlined.AccountCircle,
        route = Routes.Profile,
        title = "Profile"
    )
)
