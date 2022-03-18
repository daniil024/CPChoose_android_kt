package com.hsecourseproject.cpchoose.login.models

import com.hsecourseproject.cpchoose.login.models.enums.UserType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id")
    val id: Int?,

    @SerialName("email")
    val email: String?,

    @SerialName("firstName")
    val firstName: String?,

    @SerialName("lastName")
    val lastName: String?,

    @SerialName("patronymic")
    val patronymic: String?,

    @SerialName("userType")
    val userType: UserType?,

    @SerialName("professor")
    val professor: ProfessorResponse?,

    @SerialName("student")
    val student: StudentResponse?
)
