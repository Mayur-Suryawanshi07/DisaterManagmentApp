package com.example.disasterpreparedness.feature_disasterpreparedness.data.mapper

import com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.dto.DisasterDto
import com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.dto.Item
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterAlert
import java.text.SimpleDateFormat
import java.util.*

/**
 * This class converts raw data from the internet (DTOs) into clean data that our app can use (Domain Models).
 * It's like a translator that takes messy data and makes it neat and organized.
 *
 * Think of it like this:
 * Raw data from API -> DisasterMapper -> Clean data for the app
 *
 * The structure is:
 * DisasterDto (contains) -> Channel (contains) -> List<Item> -> mapped to -> List<DisasterAlert>
 */
object DisasterMapper {

    fun DisasterDto.toDomain(): List<DisasterAlert> {
        return this.channel?.items?.map { item ->
            item.toDomain()
        } ?: emptyList()
    }

    private fun Item.toDomain(): DisasterAlert {

        val uniqueId = createUniqueId(title, link, guid)
        val cleanTitle = this.title?.trim() ?: "No Title Available"
        val cleanLink = this.link ?: ""
        val cleanDescription = this.description ?: "No description available"
        val cleanPubDate = this.pubDate ?: ""
        val (cleanDate, cleanTime) = splitDateAndTime(this.pubDate)
        val disasterCategory = determineDisasterType(this.title)
        val cleanGuid = this.guid ?: ""
        val cleanAuthor = this.author ?: ""
        val cleanSource = this.source ?: ""

        return DisasterAlert(
            id = uniqueId,
            title = cleanTitle,
            link = cleanLink,
            description = cleanDescription,
            pubDate = cleanPubDate,
            date = cleanDate,
            time = cleanTime,
            category = disasterCategory,
            guid = cleanGuid,
            author = cleanAuthor,
            source = cleanSource
        )
    }

    private fun createUniqueId(title: String?, link: String?, guid: String?): String {
        return when {
            !guid.isNullOrBlank() -> guid  // Use GUID if available (most reliable)
            !link.isNullOrBlank() -> link.hashCode().toString()  // Use link to create ID
            !title.isNullOrBlank() -> title.hashCode().toString()  // Use title to create ID
            else -> UUID.randomUUID().toString()  // Create random ID as last resort
        }
    }

    private fun splitDateAndTime(pubDate: String?): Pair<String, String> {
        if (pubDate.isNullOrBlank()) {
            return "N/A" to "N/A"
        }

        return try {

            val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
            val dateFromApi = inputFormat.parse(pubDate)

            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)  // "15 Jan 2024"
            val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)    // "10:30:00"

            val formattedDate = dateFormat.format(dateFromApi!!)
            val formattedTime = timeFormat.format(dateFromApi)

            formattedDate to formattedTime

        } catch (error: Exception) {
            "N/A" to "N/A"
        }
    }

    private fun determineDisasterType(title: String?): String {
        if (title.isNullOrBlank()) {
            return "General Alert"
        }

        val titleLowerCase = title.lowercase()

        return when {

            titleLowerCase.containsAny(listOf("rain", "thunder", "storm", "weather")) -> "Weather Alert"
            titleLowerCase.containsAny(listOf("flood", "river", "water level")) -> "Flood Alert"
            titleLowerCase.containsAny(listOf("earthquake", "tremor")) -> "Earthquake"
            titleLowerCase.containsAny(listOf("heat", "temperature", "hot")) -> "Heat Wave"
            titleLowerCase.containsAny(listOf("cyclone", "hurricane")) -> "Cyclone"
            titleLowerCase.containsAny(listOf("landslide", "mudslide")) -> "Landslide"

            else -> "General Alert"
        }
    }

    private fun String.containsAny(words: List<String>): Boolean {
        return words.any { word -> this.contains(word) }
    }
}
