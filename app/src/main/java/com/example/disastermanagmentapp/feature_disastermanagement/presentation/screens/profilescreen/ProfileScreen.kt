package com.example.disastermanagmentapp.feature_disastermanagement.presentation.screens.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.disastermanagmentapp.R
import com.example.disastermanagmentapp.core.navigation.Routes
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.ProfileData
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.component.MyBottomNavBar
import com.example.disastermanagmentapp.feature_disastermanagement.presentation.component.MyTopAppBar
import com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen.LogInScreenViewModel
import com.example.disastermanagmentapp.feature_login.presentation.auth.loginscreen.LoginUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModelLogin: LogInScreenViewModel = hiltViewModel(),
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Handle logout navigation
    LaunchedEffect(Unit) {
        // Listen for logout state changes
        viewModelLogin.state.collect { state ->
            when (state) {
                is LoginUiState.Unauthorized -> {
                    // Navigate to auth screen when logged out
                    navController.navigate(Routes.Login) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            MyTopAppBar("Profile")
        },
        bottomBar = {
            MyBottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState.uiState) {
                is ProfileScreenUiState.Loading -> {
                    LoadingState()
                }

                is ProfileScreenUiState.Success -> {
                    val profile = (uiState.uiState as ProfileScreenUiState.Success).profile
                    ProfileContent(
                        profile = profile,
                        onLogout = { viewModelLogin.signOut() },
                        navController = navController
                    )
                }

                is ProfileScreenUiState.Error -> {
                    val errorMessage = (uiState.uiState as ProfileScreenUiState.Error).message
                    ErrorState(
                        message = errorMessage,
                        onRetry = viewModel::retry
                    )
                }

                is ProfileScreenUiState.Messsage -> {
                    val message = (uiState.uiState as ProfileScreenUiState.Messsage).message
                    Text(text = message)
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
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
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
private fun ProfileContent(
    profile: ProfileData,
    onLogout: () -> Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Profile Header with Gradient Background
        ProfileHeader(profile = profile)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Profile Information Cards
        ProfileInfoSection(profile = profile)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Action Buttons
        ActionButtonsSection(
            onLogout = onLogout,
            navController = navController
        )
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun ProfileHeader(profile: ProfileData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primaryContainer
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Photo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.disasterlogo),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Profile Name
            Text(
                text = profile.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Profile Email
            Text(
                text = profile.email,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
private fun ProfileInfoSection(profile: ProfileData) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        // Personal Information Card
        ProfileInfoCard(
            title = "Personal Information",
            icon = Icons.Default.Person,
            content = {
                ProfileInfoRow("Full Name", profile.name)
                ProfileInfoRow("Email", profile.email)
                ProfileInfoRow("Phone", profile.phone ?: "Not provided")
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Account Information Card
        ProfileInfoCard(
            title = "Account Information",
            icon = Icons.Default.AccountCircle,
            content = {
                ProfileInfoRow("User ID", profile.userId)
                ProfileInfoRow("Member Since", profile.memberSince)
                ProfileInfoRow("Status", "Active")
            }
        )
    }
}

@Composable
private fun ProfileInfoCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            content()
        }
    }
}

@Composable
private fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun ActionButtonsSection(
    onLogout: () -> Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        // Edit Profile Button
        Button(
            onClick = { /* TODO: Navigate to edit profile */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Edit Profile",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Settings Button
        OutlinedButton(
            onClick = { /* TODO: Navigate to settings */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Settings",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Logout Button
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Logout",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}