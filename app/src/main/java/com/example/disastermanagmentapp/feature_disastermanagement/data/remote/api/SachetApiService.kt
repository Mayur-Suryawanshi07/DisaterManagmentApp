package com.example.disastermanagmentapp.feature_disastermanagement.data.remote.api

import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.RssFeed
import retrofit2.Response
import retrofit2.http.GET

interface SachetApiService {
    ////https://sachet.ndma.gov.in/cap_public_website/rss/rss_india.xml
    @GET("cap_public_website/rss/rss_india.xml")
    suspend fun getRssFeed(): Response<RssFeed>

    @GET("cap_public_website/rss/rss_india.xml")
    suspend fun getRssFeedDirect(): RssFeed
}