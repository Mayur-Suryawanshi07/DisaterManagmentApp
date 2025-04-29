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
import com.example.disastermanagmentapp.Interface.HomeScreen.EventCard
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






