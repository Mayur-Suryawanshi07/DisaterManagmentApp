package com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.disaster_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.component.MyBottomNavBar
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.component.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisasterScreen(
    viewModel: DisasterScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController : NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MyTopAppBar("Disaster List")
        },
        bottomBar = {
            MyBottomNavBar(navController =navController )
        }

    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Search Bar
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::searchDisasterEvents,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search disaster events...") },
                singleLine = true
            )

            // Content based on UI state
            when (uiState.uiState) {
                is DisasterScreenUiState.Loading -> {
                    LoadingState()
                }

                is DisasterScreenUiState.Success -> {
                    val events = (uiState.uiState as DisasterScreenUiState.Success).events
                    if (events.isEmpty()) {
                        EmptyState()
                    } else {
                        DisasterEventList(
                            events = events,
                            onRefresh = viewModel::refreshEvents,
                            isRefreshing = uiState.isRefreshing
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
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
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
    events: List<DisasterEvent>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(events) { event ->
            DisasterScreenEventCard(event = event)
        }
    }
}
