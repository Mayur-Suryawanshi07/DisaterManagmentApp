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

    Scaffold(
        topBar = {
            MyTopAppBar("Disaster Details")
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState.uiState) {
                is DisasterDetailUiState.Loading -> {
                    LoadingState()
                }

                is DisasterDetailUiState.Success -> {
                    val disaster = (uiState.uiState as DisasterDetailUiState.Success).disaster
                    DisasterDetailContent(disaster = disaster)
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

@Composable
private fun DisasterDetailContent(disaster: DisasterDetailAlert) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Basic disaster information
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Disaster Alert Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                DetailRow("Identifier", disaster.identifier)
                DetailRow("Sender", disaster.sender)
                DetailRow("Sent", disaster.sent)
                DetailRow("Status", disaster.status)
                DetailRow("Message Type", disaster.msgType)
                DetailRow("Scope", disaster.scope)
                DetailRow("Restriction", disaster.restriction)
                DetailRow("Addresses", disaster.addresses)
                DetailRow("Note", disaster.note)
                DetailRow("Incidents", disaster.incidents)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Information sections
        disaster.infoList?.forEach { info ->
            InfoCard(info = info)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun InfoCard(info: Info) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = info.event,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow("Category", info.category)
            DetailRow("Urgency", info.urgency)
            DetailRow("Severity", info.severity)
            DetailRow("Certainty", info.certainty)

            if (!info.headline.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Headline",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = info.headline,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (!info.description.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = info.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (!info.instruction.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Instructions",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = info.instruction,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Area information
            info.area?.let { area ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Affected Area",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = area.areaDesc,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun DisasterRssContent(disaster: DisasterAlert) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Basic disaster information
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Disaster Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                DetailRow("Title", disaster.title)
                DetailRow("Category", disaster.category ?: "General Alert")
                DetailRow("Date", disaster.date)
                DetailRow("Time", disaster.time)
                DetailRow("Publication Date", disaster.pubDate)
                
                if (!disaster.guid.isNullOrBlank()) {
                    DetailRow("GUID", disaster.guid)
                }
                
                if (!disaster.author.isNullOrBlank()) {
                    DetailRow("Author", disaster.author)
                }
                
                if (!disaster.source.isNullOrBlank()) {
                    DetailRow("Source", disaster.source)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description card
        if (!disaster.description.isNullOrBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = disaster.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Link card
        if (!disaster.link.isNullOrBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Additional Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "For more details, visit the official source:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = disaster.link,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    if (value.isNotBlank()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(2f)
            )
        }
    }
}
