package com.hsecourseproject.cpchoose.models

import kotlinx.serialization.SerialName

data class ApprovedCPDTO(
    @SerialName("id")
    val id: Int?,

    @SerialName("professor_id")
    val professor: ProfessorDTO?,

    @SerialName("student_id")
    val student: Set<StudentDTO>?,

    @SerialName("course_project_id")
    val courseProject:CourseProjectDTO?
)
