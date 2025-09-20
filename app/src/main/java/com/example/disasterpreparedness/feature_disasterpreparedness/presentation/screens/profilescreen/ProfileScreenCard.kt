package com.example.disasterpreparedness.feature_disasterpreparedness.presentation.screens.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.disasterpreparedness.R
import com.example.disasterpreparedness.core.navigation.destination.Graph

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    profileViewModel: ProfileScreenViewModel
) {

    val state by profileViewModel.currentUser.collectAsState()

    Box(Modifier.fillMaxSize()) {
        Card(modifier
            .fillMaxWidth()
            .padding(6.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = "Current User",
                    modifier = Modifier.size(120.dp)
                )
                Column {
                    Text(text = "Welcome", style = MaterialTheme.typography.headlineMedium)
                    Text(
                        text = state?.name ?: "NA",
                        style = MaterialTheme.typography.headlineMedium
                    )

                }

                Button(onClick = {
                    profileViewModel.logOut()
                    navController.navigate(Graph.Auth) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }) {
                    Text(text = "LogOut")
                }
            }
        }
    }
}