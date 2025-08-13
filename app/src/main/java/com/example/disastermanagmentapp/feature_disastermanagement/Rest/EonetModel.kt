package com.example.disastermanagmentapp.feature_disastermanagement.Rest

data class EonetModel(val events: List<Event>)

data class Event(
    val id: String,
    val title: String,
    val description: String?,
    val link: String,
    val categories: List<Category>,
    val geometries: List<Geometry>
)

data class Category(val id: String, val title: String)

data class Geometry(val date: String, val coordinates: List<Double>)
