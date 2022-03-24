package com.hsecourseproject.cpchoose.login.models

import com.hsecourseproject.cpchoose.login.models.enums.UserType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("id")
    val id: Int?,

    @SerialName("email")
    val email: String?,

    @SerialName("emailCode")
    val code: String?,

    @SerialName("firstName")
    val firstName: String?,

    @SerialName("lastName")
    val lastName: String?,

    @SerialName("patronymic")
    val patronymic: String?,

    @SerialName("userType")
    val userType: UserType?,

    @SerialName("professor")
    val professor: ProfessorDTO?,

    @SerialName("student")
    val student: StudentDTO?
)
