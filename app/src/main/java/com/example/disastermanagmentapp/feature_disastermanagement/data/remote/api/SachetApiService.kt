package com.example.disastermanagmentapp.feature_disastermanagement.data.remote.api

import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.DisasterDetailDto
import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.DisasterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SachetApiService {
    ////https://sachet.ndma.gov.in/cap_public_website/rss/rss_india.xml
    @GET("cap_public_website/rss/rss_india.xml")
    suspend fun getRssFeed(): Response<DisasterDto>

    @GET("cap_public_website/FetchXMLFile")
    suspend fun getRssFeedDetail(
        @Query("identifier") identifier: String
    ): Response<DisasterDetailDto>

}
//https://sachet.ndma.gov.in/cap_public_website/FetchXMLFile?identifier=1755884473517027