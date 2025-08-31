package com.example.disasterpreparedness.feature_disasterpreparedness.domain.model

data class ProfileData(
    val userId: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val memberSince: String,
    val profilePhotoUrl: String? = null
)
