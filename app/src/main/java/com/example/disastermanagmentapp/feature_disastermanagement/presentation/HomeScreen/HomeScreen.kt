package com.example.disastermanagmentapp.feature_disastermanagement.presentation.HomeScreen

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
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.state.DisasterEventUiState
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.viewmodel.DisasterEventViewModel

@Composable
fun HomeScreen(
    viewModel: DisasterEventViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Column(
        modifier = modifier.fillMaxSize(),
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
            is DisasterEventUiState.Loading -> {
                LoadingState()
            }
            is DisasterEventUiState.Success -> {
                val events = (uiState.uiState as DisasterEventUiState.Success).events
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
            is DisasterEventUiState.Error -> {
                val errorMessage = (uiState.uiState as DisasterEventUiState.Error).message
                ErrorState(
                    message = errorMessage,
                    onRetry = viewModel::retry
                )
            }
            is DisasterEventUiState.Empty -> {
                EmptyState()
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
    events: List<com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(events) { event ->
            EventCard(event = event)
        }
    }
}
