package com.example.disastermanagmentapp.feature_disastermanagement.Interface.HomeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Scaffold {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Enable vertical scrolling
        ) {
            Column {
                Text(
                    text = "About This App",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "This Android application provides users with quick access to emergency resources and information related to natural disasters.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                SectionTitle("Key Features")
                FeatureText("Emergency Contacts: The app presents a grid of emergency contacts (e.g., Police, Doctor, Ambulance, Fire Service) allowing users to initiate calls directly from the app. It handles call permissions gracefully.")
                FeatureText("Disaster Information: The application fetches and displays information about natural disasters (specifically droughts, landslides, and snow events) from the EONET API (NASA). It presents this information in a user-friendly format, including event titles, dates, and locations (with city and country names obtained through reverse geocoding). A loading indicator is displayed while data is being retrieved.")
                FeatureText("Settings: Users can toggle between light and darkthemes within the app's settings. This preference is persisted across app launches using DataStore.")
                FeatureText("Theming: The app uses Jetpack Compose and Material 3 to provide a modern and customizable user interface.")

                SectionTitle("Technical Highlights")
                FeatureText("Jetpack Compose: The entire UI is built using Jetpack Compose, Android's modern declarative UI toolkit.")
                FeatureText("Material 3: The app leverages Material 3 components for a consistent and up-to-date look and feel.")
                FeatureText("DataStore: App preferences (dark/light mode) are stored using DataStore, the recommended persistence solution for key-value data.")
                FeatureText("Retrofit: The app uses Retrofit to interact with the EONET API, fetching disaster-related data.")
                FeatureText("Coroutines: Asynchronous operations (API calls, DataStore access) are handled using Kotlin Coroutines to prevent blocking the main thread.")
                FeatureText("Permissions: The app requests and handles the CALL_PHONE permission to enable direct calling functionality.")
                FeatureText("Reverse Geocoding: The app uses the Geocoder class to translate geographic coordinates into human-readable location names (city and country).")
                FeatureText("Error Handling: Basic error handling is implemented to manage potential issues during API calls and location lookups.")

                SectionTitle("Potential Future Enhancements")
                FeatureText("More detailed event information display.")
                FeatureText("Filtering and searching of disaster events.")
                FeatureText("Interactive maps showing event locations.")
                FeatureText("Push notifications for new or updated events.")
                FeatureText("Support for additional disaster categories.")
                FeatureText("Improved error handling and user feedback.")
                FeatureText("Enhanced UI/UX design and animations.")

                Spacer(modifier = Modifier.height(50.dp)) // Add some space at the bottom
            }
        }
    }

}

@Composable
fun FeatureText(s: String) {
    Text(
        text = s,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )

}

@Composable
fun SectionTitle(s: String) {
    Text(
        text = s,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}
