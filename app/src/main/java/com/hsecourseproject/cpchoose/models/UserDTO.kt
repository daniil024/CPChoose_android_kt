package com.hsecourseproject.cpchoose.models

import com.hsecourseproject.cpchoose.models.enums.UserType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("id")
    var id: Int?,

    @SerialName("email")
    var email: String?,

    @SerialName("emailCode")
    var code: String?,

    @SerialName("firstName")
    var firstName: String?,

    @SerialName("lastName")
    var lastName: String?,

    @SerialName("patronymic")
    var patronymic: String?,

    @SerialName("userType")
    var userType: UserType?,

    @SerialName("professor")
    var professor: ProfessorDTO?,

    @SerialName("student")
    var student: StudentDTO?
)
