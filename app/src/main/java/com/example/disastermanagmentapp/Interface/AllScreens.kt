package com.example.disastermanagmentapp.Interface

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract.Contacts
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disastermanagmentapp.Rest.EonetApiService
import com.example.disastermanagmentapp.Rest.Event
import com.example.disastermanagmentapp.R
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Composable
fun DisasterListForCategory(categoryName: String) {
    var events by remember { mutableStateOf(emptyList<Event>()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Use a key that changes only when the composable is re-entered
    val key = remember { categoryName }

    LaunchedEffect(key) {
        isLoading = true
        errorMessage = null
        try {
            val gson = GsonBuilder().create()

            // Create an OkHttpClient with increased timeouts
            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://eonet.gsfc.nasa.gov/api/v2.1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

            val service = retrofit.create(EonetApiService::class.java)
            val response = service.getEvents()
            events = if (categoryName == "all") {
                response.events // Show all events for "AllScreen"
            } else {
                response.events.filter {
                    it.categories.any { category -> category.title.contains(categoryName, ignoreCase = true) }
                }
            }
        } catch (e: Exception) {
            Log.e("EonetData", "Error fetching events for $categoryName: ${e.message}", e)
            errorMessage = "Failed to load data for $categoryName. Please try again later."
        } finally {
            isLoading = false
        }
    }

    Scaffold(
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            horizontalAlignment =Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            if (isLoading) {
                Text("Loading...", modifier = Modifier.padding(16.dp))
                CircularProgressIndicator()
            } else if (errorMessage != null) {
                Text(errorMessage!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            }else if (events.isEmpty()){
                Text("No events found for $categoryName", modifier = Modifier.padding(16.dp))
            }
            else {
                LazyColumn(modifier = Modifier
                    .padding(14.dp)
                    .padding(top = 50.dp)) {
                    items(events) { event ->
                        EventCard(event)
                    }
                }
            }
        }
    }
}

@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.title, style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "Category:", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = event.categories.joinToString { it.title },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Date:  ${event.geometries.firstOrNull()?.date?.substringBefore("T") ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Time:  ${event.geometries.firstOrNull()?.date?.substringBefore("T") ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

//@Composable
//fun SettingScreen(modifier: Modifier = Modifier) {
//    Box(modifier =modifier ){
//        Row (modifier = Modifier.fillMaxSize(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically){
//            Text(text = "Settings Screen")
//        }
//
//    }
//
//}
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


@Preview(showSystemUi = true)
@Composable
fun EmergencyCallList(modifier: Modifier = Modifier) {
    val items = listOf(
        EmergencyItems(R.drawable.police, "Call Police"),
        EmergencyItems(R.drawable.docter, "Call Doctor"),
        EmergencyItems(R.drawable.ambulance, "Call Ambulance"),
        EmergencyItems(R.drawable.fire, "Call Fire Service"),
        EmergencyItems(R.drawable.weater_forecast, "Check Weather"),
        EmergencyItems(R.drawable.first_aid, "First Aid Instruction"),
        EmergencyItems(R.drawable.tips_logo, "Safety tips"),
        EmergencyItems(R.drawable.national_agency_logo, "National Disaster Management Authority"),
        EmergencyItems(R.drawable.report_logo2, "Report A Disaster")

    )

    Scaffold (modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp)
    ){
        val state = rememberLazyGridState()
        Column(
            modifier = Modifier.padding(bottom = 80.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "Emergency Call List",
                color = Color.Red,
                fontSize = 20.sp,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            )
            LazyVerticalGrid(
                state=state,
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)

            ) {
                itemsIndexed(items) { _, item ->
                    EmergencyItemCard(item.Img, item.Name) // Call the composable function
                }
            }
        }
    }
}

@Composable
fun EmergencyItemCard(img: Int, name: String) { // Composable function for each item
    val context= LocalContext.current
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp),
        onClick = {
            when (name) {
                "Call Police", "Call Ambulance", "Call Fire Service" ->
                        makeCall(context, getPhoneNumber(name))
                "Medical tips" ->{
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.ready.gov/home-fires"))
                    context.startActivity(intent)

                }
                "Check Weather" ->{
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/search?q=current+weather&oq=current+weather+&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBBzI1NGowajeoAg-wAgE&client=ms-android-xiaomi-rvo2b&sourceid=chrome-mobile&ie=UTF-8"))
                    context.startActivity(intent)

                }
                "First Aid Instruction" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.verywellhealth.com/basic-first-aid-procedures-1298578"))
                    context.startActivity(intent)

                }
                "Safety tips" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ddma.delhi.gov.in/ddma/safety-tips-1"))
                    context.startActivity(intent)
                }
                "Call Doctor"->{
                    val getNumber=Intent(Intent.ACTION_VIEW, Uri.parse(Contacts.CONTENT_URI.toString()))
                    context.startActivity(getNumber)
                }
                "Report A Disaster" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ndma.gov.in/Response/Emergency-Operations-Center"))
                    context.startActivity(intent)
                }
                "National Disaster Management Authority" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ndma.gov.in/"))
                    context.startActivity(intent)
                }

            }

        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun getPhoneNumber(name: String): String =
    when (name) {
        "Call Police" -> "100" // Replace with actual number
        "Call Doctor" -> " "
        "Call Ambulance" -> "102"
        "Call Fire Service" -> "101"
        else -> "" // Or throw IllegalArgumentException("Invalid emergency contact: $name")
    }

private fun makeCall(context: Context, phoneNumber: String) {
    if (phoneNumber.isNotEmpty()) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        context.startActivity(intent)
    }
}

data class EmergencyItems(
    val Img: Int,
    val Name: String
)
