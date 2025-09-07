package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterDetailAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.Info
import com.example.disasterpreparedness.feature_disasterpreparedness.presentation.component.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisasterDetailScreen(
    viewModel: DisasterDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (uiState.uiState) {
            is DisasterDetailUiState.Loading -> {
                LoadingState()
            }

            is DisasterDetailUiState.Success -> {
                val disaster = (uiState.uiState as DisasterDetailUiState.Success).disaster
                DisasterDetailContentScreen(disaster = disaster)
            }

            is DisasterDetailUiState.SuccessRss -> {
                val disaster = (uiState.uiState as DisasterDetailUiState.SuccessRss).disaster
                DisasterRssContent(disaster = disaster)
            }

            is DisasterDetailUiState.Error -> {
                val errorMessage = (uiState.uiState as DisasterDetailUiState.Error).message
                ErrorState(
                    message = errorMessage,
                    onRetry = viewModel::retry
                )
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading disaster details...",
                style = MaterialTheme.typography.bodyMedium
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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
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