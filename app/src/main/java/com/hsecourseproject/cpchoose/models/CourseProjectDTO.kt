package com.hsecourseproject.cpchoose.models

import com.hsecourseproject.cpchoose.models.enums.CPMode
import com.hsecourseproject.cpchoose.models.enums.CPStatus
import com.hsecourseproject.cpchoose.models.enums.CPType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseProjectDTO(
    @SerialName("id")
    val id:Int?,

    @SerialName("userId")
    val userId:Int?,

    @SerialName("titleRus")
    val titleRus:String?,

    @SerialName("titleEng")
    val titleEng:String?,

    @SerialName("type")
    val type: CPType?,

    @SerialName("mode")
    val mode: CPMode?,

    @SerialName("membersCount")
    val membersCount:Int?,

    @SerialName("projectInitiator")
    val projectInitiator:String?,

    @SerialName("companySubdivision")
    val companySubdivision:String?,

    @SerialName("mentorFullName")
    val mentorFullName:String?,

    @SerialName("annotation")
    val annotation:String?,

    @SerialName("projectGoal")
    val projectGoal:String?,

    @SerialName("projectTasks")
    val projectTasks:String?,

    @SerialName("participantsTasks")
    val participantsTasks:String?,

    @SerialName("projectResults")
    val projectResults:String?,

    @SerialName("additionalInfo")
    val additionalInfo:String?,

    @SerialName("workPlace")
    val workPlace:String?,

    @SerialName("studentsRequirements")
    val studentsRequirements:String?,

    @SerialName("contacts")
    val contacts:String?,

    @SerialName("startDate")
    val startDate:String?,

    @SerialName("finishDate")
    val finishDate:String?,

    @SerialName("selectionForm")
    val selectionForm:String?,

    @SerialName("evaluationCriteria")
    val evaluationCriteria:String?,

    @SerialName("status")
    val status: CPStatus?,
)
