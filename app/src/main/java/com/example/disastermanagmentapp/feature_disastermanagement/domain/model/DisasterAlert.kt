package com.example.disastermanagmentapp.feature_disastermanagement.domain.model

data class DisasterAlert(
    val id: String,
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val category: String? = null
)