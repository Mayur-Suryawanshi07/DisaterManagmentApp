package com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.disaster_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.disastermanagmentapp.core.navigation.Routes
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterAlert

@Composable
fun DisasterScreenEventCard(
    event: DisasterAlert,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                // Navigate to disaster detail screen with the event ID
                navController.navigate(Routes.DisasterDetail(disasterId = event.id))
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "Category:", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = event.category.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Date: ${event.date}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Time: ${event.time ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}