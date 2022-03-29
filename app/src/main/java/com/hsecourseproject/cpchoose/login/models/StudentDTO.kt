package com.hsecourseproject.cpchoose.login.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentDTO(
    @SerialName("id")
    val id: Int?,

    @SerialName("faculty")
    val faculty: String?,

    @SerialName("speciality")
    val speciality: String?,

    @SerialName("yearOfStudy")
    val yearOfStudy: Int?,
)
