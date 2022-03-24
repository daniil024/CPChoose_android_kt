package com.hsecourseproject.cpchoose.login.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfessorDTO(
    @SerialName("id")
    val id: Int,

    @SerialName("cpCount")
    val cpCount: Int,

    @SerialName("subdivision")
    val subdivision: String,

    @SerialName("position")
    val position: String
)
