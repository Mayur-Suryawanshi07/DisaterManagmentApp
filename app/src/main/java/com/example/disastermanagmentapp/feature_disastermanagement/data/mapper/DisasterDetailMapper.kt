package com.example.disastermanagmentapp.feature_disastermanagement.data.mapper

import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.AreaDto
import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.DisasterDetailDto
import com.example.disastermanagmentapp.feature_disastermanagement.data.remote.dto.InfoDto
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.Area
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterDetailAlert
import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.Info

object DisasterDetailMapper {

    fun DisasterDetailDto.toDomain(): DisasterDetailAlert {
        return DisasterDetailAlert(
            id = identifier.orEmpty(),
            sender = sender.orEmpty(),
            sent = sent.orEmpty(),
            status = status.orEmpty(),
            msgType = msgType.orEmpty(),
            scope = scope.orEmpty(),
            restriction = restriction.orEmpty(),
            addresses = addresses.orEmpty(),
            note = note.orEmpty(),
            incidents = incidents.orEmpty(),
            infoList = infoList?.map { it.toDomain() },
            identifier = identifier.orEmpty()
        )
    }

    fun InfoDto.toDomain(): Info {
        return Info(
            category = category.orEmpty(),
            event = event.orEmpty(),
            urgency = urgency.orEmpty(),
            severity = severity.orEmpty(),
            certainty = certainty.orEmpty(),
            headline = headline.orEmpty(),
            description = description.orEmpty(),
            instruction = instruction.orEmpty(),
            area = area?.toDomain()
        )
    }

    fun AreaDto.toDomain(): Area {
        return Area(
            areaDesc = areaDesc.orEmpty()
        )

    }
}