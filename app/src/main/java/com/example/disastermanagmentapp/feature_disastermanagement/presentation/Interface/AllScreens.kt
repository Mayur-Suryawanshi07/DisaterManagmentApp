package com.example.disastermanagmentapp.feature_disastermanagement.presentation.Interface

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.HomeScreen.HomeScreen

@Composable
fun DisasterListForCategory(categoryName: String) {
    // For now, we'll use the new HomeScreen for all categories
    // TODO: Implement category-specific filtering in the ViewModel
    Scaffold { paddingValues ->
        HomeScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}






