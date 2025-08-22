package com.example.disastermanagmentapp.feature_disastermanagement.data.mapper

import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.Item
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterAlert
import java.text.SimpleDateFormat
import java.util.*

object DisasterMapper {

    fun mapToDomain(item: Item): DisasterAlert {

        val rawDate = item.pubDate
        val (date,time)= formatDateTime(rawDate)

        return DisasterAlert(
            id = generateId(item),
            title = item.title?.trim() ?: "No Title",
            link = item.link ?: "",
            description = item.description?:"NA",
            pubDate = item.pubDate ?: "",
            date = date,
            time = time,
            category = extractCategory(item.title)
        )
    }

    fun mapToDomainList(items: List<Item>?): List<DisasterAlert> {
        return items?.map { mapToDomain(it) } ?: emptyList()
    }

    private fun generateId(item: Item): String {
        return item.link?.hashCode()?.toString() ?: item.title?.hashCode()?.toString() ?: UUID.randomUUID().toString()
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

    private fun formatDateTime(pubDate: String?): Pair<String, String> {
        return try {
            val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
            val date = inputFormat.parse(pubDate ?: "")

            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)

            dateFormat.format(date!!) to timeFormat.format(date)
        } catch (e: Exception) {
            "N/A" to "N/A"
        }
    }
}
