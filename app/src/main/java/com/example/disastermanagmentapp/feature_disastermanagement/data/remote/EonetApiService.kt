package com.example.disastermanagmentapp.feature_disastermanagement.data.remote

import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.model.EonetModel
import retrofit2.http.GET

interface EonetApiService {
    @GET("events")suspend fun getEvents(): EonetModel
}