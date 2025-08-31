package com.example.disasterpreparedness.feature_disasterpreparedness.data.remote.dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "alert", strict = false)
data class DisasterDetailDto @JvmOverloads constructor(

    @field:Element(name = "identifier", required = false)
    var identifier: String? = null,

    @field:Element(name = "sender", required = false)
    var sender: String? = null,

    @field:Element(name = "sent", required = false)
    var sent: String? = null,

    @field:Element(name = "status", required = false)
    var status: String? = null,

    @field:Element(name = "msgType", required = false)
    var msgType: String? = null,

    @field:Element(name = "scope", required = false)
    var scope: String? = null,

    @field:Element(name = "restriction", required = false)
    var restriction: String? = null,

    @field:Element(name = "addresses", required = false)
    var addresses: String? = null,

    @field:Element(name = "note", required = false)
    var note: String? = null,

    @field:Element(name = "incidents", required = false)
    var incidents: String? = null,

    // multiple info blocks
    @field:ElementList(inline = true, entry = "info", required = false)
    var infoList: List<InfoDto>? = null

)

@Root(name = "info",strict = false)
data class InfoDto @JvmOverloads constructor(

    @field:Element(name = "language", required = false)
    var language: String? = null,

    @field:Element(name = "category", required = false)
    var category: String? = null,

    @field:Element(name = "event", required = false)
    var event: String? = null,

    @field:Element(name = "urgency", required = false)
    var urgency: String? = null,

    @field:Element(name = "severity", required = false)
    var severity: String? = null,

    @field:Element(name = "certainty", required = false)
    var certainty: String? = null,

    @field:Element(name = "headline", required = false)
    var headline: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "instruction", required = false)
    var instruction: String? = null,

    @field:Element(name = "area", required = false)
    var area: AreaDto? = null

)

data class AreaDto @JvmOverloads constructor(

    @field:Element(name = "araeDesc", required = false)
    var areaDesc: String? = null,

)
