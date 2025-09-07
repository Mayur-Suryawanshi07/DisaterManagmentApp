package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.disaster_detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterDetailAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.Info

@Composable
fun DisasterDetailContentScreen(disaster: DisasterDetailAlert) {
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

@Composable
fun DisasterRssContent(disaster: DisasterAlert) {
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