package com.example.disasterpreparedness.feature_disasterpreparedness.data.mapper

import com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.dto.AreaDto
import com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.dto.DisasterDetailDto
import com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.dto.InfoDto
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.Area
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.DisasterDetailAlert
import com.example.disasterpreparedness.feature_disasterpreparedness.domain.model.Info

/**
 * This class converts detailed disaster information from the internet into clean data for our app.
 * It handles more complex disaster alerts with multiple information sections.
 *
 * Think of it like this:
 * Raw detailed data from API -> DisasterDetailMapper -> Clean detailed data for the app
 */
object DisasterDetailMapper {

    fun DisasterDetailDto.toDomain(): DisasterDetailAlert {
        // Clean up all the basic alert information
        val cleanId = identifier ?: ""
        val cleanSender = sender ?: ""
        val cleanSent = sent ?: ""
        val cleanStatus = status ?: ""
        val cleanMsgType = msgType ?: ""
        val cleanScope = scope ?: ""
        val cleanRestriction = restriction ?: ""
        val cleanAddresses = addresses ?: ""
        val cleanNote = note ?: ""
        val cleanIncidents = incidents ?: ""

        val cleanInfoList = infoList?.map { infoDto ->
            infoDto.toDomain()
        }

        return DisasterDetailAlert(
            id = cleanId,
            sender = cleanSender,
            sent = cleanSent,
            status = cleanStatus,
            msgType = cleanMsgType,
            scope = cleanScope,
            restriction = cleanRestriction,
            addresses = cleanAddresses,
            note = cleanNote,
            incidents = cleanIncidents,
            infoList = cleanInfoList,
            identifier = cleanId  // Use the same ID for consistency
        )
    }

    fun InfoDto.toDomain(): Info {

        val cleanCategory = category ?: ""
        val cleanEvent = event ?: ""
        val cleanUrgency = urgency ?: ""
        val cleanSeverity = severity ?: ""
        val cleanCertainty = certainty ?: ""
        val cleanHeadline = headline ?: ""
        val cleanDescription = description ?: ""
        val cleanInstruction = instruction ?: ""
        val cleanArea = area?.toDomain()


        return Info(
            category = cleanCategory,
            event = cleanEvent,
            urgency = cleanUrgency,
            severity = cleanSeverity,
            certainty = cleanCertainty,
            headline = cleanHeadline,
            description = cleanDescription,
            instruction = cleanInstruction,
            area = cleanArea,
            language = language ?: ""
        )
    }

    fun AreaDto.toDomain(): Area {
        val cleanAreaDescription = areaDesc ?: ""
        return Area(areaDesc = cleanAreaDescription)
    }
}
