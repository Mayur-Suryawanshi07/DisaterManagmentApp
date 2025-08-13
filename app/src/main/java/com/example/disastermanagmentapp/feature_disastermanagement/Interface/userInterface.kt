package com.example.disastermanagmentapp.Interface

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactEmergency
import androidx.compose.material.icons.filled.CrisisAlert
import androidx.compose.material.icons.filled.Cyclone
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Flood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.ContactEmergency
import androidx.compose.material.icons.outlined.CrisisAlert
import androidx.compose.material.icons.outlined.Cyclone
import androidx.compose.material.icons.outlined.Flood
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.disastermanagmentapp.Navigation.Nav
import com.example.disastermanagmentapp.Navigation.Screen
import com.example.disastermanagmentapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInterface(context: Context, isDarkTheme: MutableState<Boolean>) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.disaster_wallapaper),
                        contentDescription = "cyclone",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Natural Disaster",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                DrawerUserContent(navController, drawerState, coroutine)


            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutine.launch {
                                drawerState.open()
                            }

                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    title = { Text(text = "Disaster Management App") },
                    actions = {
                        IconButton(onClick = {
                            isDarkTheme.value = !isDarkTheme.value
                        }) {
                            Icon(
                                imageVector = if (isDarkTheme.value) Icons.Default.DarkMode else Icons.Default.WbSunny,
                                contentDescription = "Toggle Theme"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BotomNavItems(navController)
            }

        ) { innerPadding ->
            Nav( Modifier.padding(innerPadding), navController = navController)
        }
    }
}

@Composable
private fun BotomNavItems(navController: NavHostController) {
    var selectedBottomItems by rememberSaveable {
        mutableIntStateOf(0)
    }
    val items1 = listOf(
        NavigationBottomTheme(
            Icons.Filled.Home,
            Icons.Outlined.Home,
            route = Screen.AllScreen.route
        ),
        NavigationBottomTheme(
            Icons.Filled.ContactEmergency,
            Icons.Outlined.ContactEmergency,
            route = Screen.EmergencyCallList.route
        ),
        NavigationBottomTheme(
            Icons.Filled.Map,
            Icons.Outlined.Map,
            route = Screen.Map.route
        ),
    )

    NavigationBar {
        items1.forEachIndexed { index, navigationBottomTheme ->
            NavigationBarItem(
                selected = selectedBottomItems == index,
                onClick = {
                    selectedBottomItems = index
                    navController.navigate(navigationBottomTheme.route)
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


@Composable
fun DrawerUserContent(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {


    val items = listOf(
        NavigationDrawerTheme(
            "All",
            Icons.Filled.Home,
            Icons.Outlined.Home,
            route = Screen.AllScreen.route
        ),
        NavigationDrawerTheme(
            "WildFire",
            Icons.Filled.LocalFireDepartment,
            Icons.Outlined.LocalFireDepartment,
            route = Screen.FireScreen.route
        ),
        NavigationDrawerTheme(
            "Flood",
            Icons.Filled.Flood,
            Icons.Outlined.Flood,
            route = Screen.Flood.route
        ),
        NavigationDrawerTheme(
            "Earthquake",
            Icons.Filled.CrisisAlert,
            Icons.Outlined.CrisisAlert,
            route = Screen.Earthquake.route
        ),
        NavigationDrawerTheme(
            "Cyclone",
            Icons.Filled.Cyclone,
            Icons.Outlined.Cyclone,
            route = Screen.Cyclone.route
        ),
        NavigationDrawerTheme(
            "About",
            Icons.Filled.Info,
            Icons.Outlined.Info,
            route = Screen.About.route
        ),
    )

    var SelectionState by rememberSaveable {
        mutableIntStateOf(0)
    }
    items.forEachIndexed { index, navigationTheme ->
        NavigationDrawerItem(

            icon = {
                if (index == SelectionState) {
                    Icon(
                        imageVector = navigationTheme.SelectedItems,
                        contentDescription = "Selected Icons"
                    )
                } else {
                    Icon(
                        imageVector = navigationTheme.UnSelectedItems,
                        contentDescription = "Unselected Icon"
                    )
                }
            },
            label = {
                Text(text = navigationTheme.title)
            },
            selected = SelectionState == index,
            onClick = {
                SelectionState = index
                navController.navigate(navigationTheme.route)
                coroutineScope.launch {
                    drawerState.close()
                }


            },

            )
        if (index == 4) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
        }
    }


}


data class NavigationDrawerTheme(
    val title: String,
    val SelectedItems: ImageVector,
    val UnSelectedItems: ImageVector,
    val route: String,
    val badge: Int? = null,
)

data class NavigationBottomTheme(
    val SelectedItems: ImageVector,
    val UnSelectedItems: ImageVector,
    val route: String,
    val badge: Int? = null,
)

