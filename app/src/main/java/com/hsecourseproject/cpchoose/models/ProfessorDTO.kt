package com.hsecourseproject.cpchoose.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfessorDTO(
    @SerialName("id")
    var id: Int?,

    @SerialName("companyName")
    var companyName: String?,

    @SerialName("cpCount")
    var cpCount: Int?,

    @SerialName("subdivision")
    var subdivision: String?,

    @SerialName("position")
    var position: String?
)
