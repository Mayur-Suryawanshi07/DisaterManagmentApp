package com.example.disastermanagmentapp.feature_disastermanagement.domain.model

data class DisasterDetailAlert(
    val id: String,
    val identifier: String,
    val sender: String,
    val sent: String,
    val status: String,
    val msgType: String,
    val scope: String,
    val restriction: String,
    val addresses: String,
    val note: String,
    val incidents: String,
    val infoList: List<Info>? =null
)

data class Info(
    val language: String,
    val category: String,
    val event: String,
    val urgency: String,
    val severity: String,
    val certainty: String,
    val headline: String,
    val description: String,
    val instruction: String,
    val area: Area?=null
)

data class Area(
    val areaDesc: String
)