package com.example.disastermanagmentapp.Rest

import retrofit2.http.GET

interface EonetApiService {
    @GET("events")suspend fun getEvents(): EonetModel
}