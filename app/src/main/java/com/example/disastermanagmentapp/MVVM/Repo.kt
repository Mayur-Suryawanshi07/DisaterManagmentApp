package com.example.disastermanagmentapp.MVVM

import com.example.disastermanagmentapp.Rest.EonetApiService
import android.util.Log
import com.example.disastermanagmentapp.Rest.Event
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



class Repo {
    private val service: EonetApiService

    init {
        val gson = GsonBuilder().create()
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://eonet.gsfc.nasa.gov/api/v2.1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        service = retrofit.create(EonetApiService::class.java)}

    suspend fun getEventsByCategory(categoryName: String): List<Event> {
        return try {
            val response = service.getEvents()
            if (categoryName == "all") {
                response.events
            } else {
                response.events.filter { event ->
                    event.categories.any { category ->
                        category.title.contains(categoryName, ignoreCase = true)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("EonetData", "Error fetching events for $categoryName: ${e.message}", e)
            throw Exception("Failed to load data for $categoryName. Please try again later.")
        }
    }

    suspend fun getDroughtEvents(): List<Event> {
        return getEventsByCategory("drought")
    }

    suspend fun getLandslideEvents(): List<Event> {
        return getEventsByCategory("landslide")
    }

    suspend fun getSnowEvents(): List<Event> {
        return getEventsByCategory("snow")
    }
}