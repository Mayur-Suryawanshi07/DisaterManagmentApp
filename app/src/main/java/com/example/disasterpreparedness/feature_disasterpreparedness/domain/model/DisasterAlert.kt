package com.example.disasterpreparedness.feature_disasterpreparedness.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DisasterAlert(
    val id: String,
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val date :String,
    val time :String,
    val category: String? = null,
    val guid: String? = null,  // Add GUID field for potential future use
    val author: String? = null, // Add author field if available in RSS
    val source: String? = null  // Add source field for additional context
)
