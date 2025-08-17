package com.example.disastermanagmentapp.feature_disastermanagement.presentation.component

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.disastermanagmentapp.core.navigation.Routes

@Composable
fun MyBottomNavBar(
    navController: NavHostController,
) {
    var selectedBottomItems by rememberSaveable {
        mutableIntStateOf(0)
    }
    val items = listOf(
        NavigationBottomTheme(
            Icons.Filled.Home,
            Icons.Outlined.Home,
            route = Routes.Home
        ),
        NavigationBottomTheme(
            Icons.Filled.ContactEmergency,
            Icons.Outlined.ContactEmergency,
            route = Routes.Contact
        ),
        NavigationBottomTheme(
            Icons.Filled.AccountCircle,
            Icons.Outlined.AccountCircle,
            route = Routes.Profile
        ),
    )

    NavigationBar {
        items.forEachIndexed { index, navigationBottomTheme ->
            NavigationBarItem(
                selected = selectedBottomItems == index,
                onClick = {
                    selectedBottomItems = index
                    navController.navigate(navigationBottomTheme.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selectedBottomItems == index) navigationBottomTheme.SelectedItems else navigationBottomTheme.UnSelectedItems,
                        contentDescription = "Bottom Navigation Icon"
                    )
                },
            )
        }
    }
}


data class NavigationBottomTheme(
    val SelectedItems: ImageVector,
    val UnSelectedItems: ImageVector,
    val route: Routes
)