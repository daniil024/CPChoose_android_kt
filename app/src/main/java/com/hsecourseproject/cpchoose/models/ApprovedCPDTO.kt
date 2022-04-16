package com.hsecourseproject.cpchoose.models

import com.hsecourseproject.cpchoose.models.enums.CPStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApprovedCPDTO(
    @SerialName("id")
    var id: Int?,

    @SerialName("professor")
    var professor: UserDTO?,

    @SerialName("student")
    var student: UserDTO?,

    @SerialName("courseProject")
    var courseProject:CourseProjectDTO?,

    @SerialName("status")
    var status:CPStatus
)
