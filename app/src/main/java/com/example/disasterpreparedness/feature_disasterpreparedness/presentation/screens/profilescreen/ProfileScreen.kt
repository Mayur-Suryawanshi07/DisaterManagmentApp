package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileScreenViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    ProfileCard(modifier = Modifier, navController, profileViewModel)

}