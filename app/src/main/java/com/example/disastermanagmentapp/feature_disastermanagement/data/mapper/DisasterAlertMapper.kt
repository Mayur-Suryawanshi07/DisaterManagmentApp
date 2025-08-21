package com.example.disastermanagmentapp.feature_disastermanagement.data.mapper

import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.Item
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterAlert
import java.text.SimpleDateFormat
import java.util.*

object DisasterAlertMapper {

    private val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)

    fun mapToDomain(item: Item): DisasterAlert {
        return DisasterAlert(
            id = generateId(item),
            title = item.title?.trim() ?: "No Title",
            link = item.link ?: "",
            description = cleanDescription(item.description ?: "No Description"),
            pubDate = item.pubDate ?: "",
            category = extractCategory(item.title)
        )
    }

    fun mapToDomainList(items: List<Item>?): List<DisasterAlert> {
        return items?.map { mapToDomain(it) } ?: emptyList()
    }

    private fun generateId(item: Item): String {
        return item.link?.hashCode()?.toString() ?: item.title?.hashCode()?.toString() ?: UUID.randomUUID().toString()
    }

    private fun cleanDescription(description: String): String {
        return description
            .replace(Regex("<[^>]*>"), "") // Remove HTML tags
            .replace(Regex("&[a-zA-Z0-9#]+;"), " ") // Replace HTML entities with space
            .trim()
    }

    private fun extractCategory(title: String?): String? {
        if (title.isNullOrBlank()) return null

        val titleLower = title.lowercase()

        return when {
            titleLower.contains("rain") || titleLower.contains("thunder") ||
            titleLower.contains("storm") || titleLower.contains("weather") -> "Weather Alert"

            titleLower.contains("flood") || titleLower.contains("river") ||
            titleLower.contains("water level") -> "Flood Alert"

            titleLower.contains("earthquake") || titleLower.contains("tremor") -> "Earthquake"

            titleLower.contains("heat") || titleLower.contains("temperature") ||
            titleLower.contains("hot") -> "Heat Wave"

            titleLower.contains("cyclone") || titleLower.contains("hurricane") -> "Cyclone"

            titleLower.contains("landslide") || titleLower.contains("mudslide") -> "Landslide"

            else -> "General Alert"
        }
    }
}
