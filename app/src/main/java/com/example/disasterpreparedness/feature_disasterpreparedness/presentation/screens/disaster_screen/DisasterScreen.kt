package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.Info
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyBottomNavBar
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisasterScreen(
    viewModel: DisasterScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val items = listOf(
            "ALL",
            "Weather Alert",
            "Flood Alert",
            "Earthquake",
            "Heat Wave",
            "Cyclone",
            "Landslide"
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp), // Padding for the whole row
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between each Box item
        ) {
            items(items) { categoryName ->
                Box(
                    modifier = Modifier
                        .clickable {
                            viewModel.searchDisasterEvents(categoryName)
                        }
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 8.dp) // Padding inside the Box
                ) {
                    Text(text = categoryName)
                }
            }
        }

        // Content based on UI state
        when (uiState.uiState) {
            is DisasterScreenUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is DisasterScreenUiState.Success -> {
                val events = (uiState.uiState as DisasterScreenUiState.Success).events
                if (events.isEmpty()) {
                    EmptyState()
                } else {
                    DisasterEventList(
                        events = events,
                        onRefresh = viewModel::refreshEvents,
                        isRefreshing = uiState.isRefreshing,
                        navController,
                    )
                }
            }

            is DisasterScreenUiState.Error -> {
                val errorMessage = (uiState.uiState as DisasterScreenUiState.Error).message
                ErrorState(
                    message = errorMessage,
                    onRetry = viewModel::retry
                )
            }

            is DisasterScreenUiState.Empty -> {
                EmptyState()
            }

            is DisasterScreenUiState.SuccessInfo -> TODO()
        }
    }
}


@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No disaster events found",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Try adjusting your search or check back later",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Something went wrong",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}

@Composable
private fun DisasterEventList(
    events: List<DisasterAlert>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    navController: NavHostController,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(events) { event ->
            DisasterScreenEventCard(event = event, navController = navController)
        }
    }
}
