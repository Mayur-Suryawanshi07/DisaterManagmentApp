package com.example.disastermanagmentapp.feature_disastermanagement.Rest

import retrofit2.http.GET

interface EonetApiService {
    @GET("events")suspend fun getEvents(): EonetModel
}