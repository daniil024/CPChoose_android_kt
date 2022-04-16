package com.hsecourseproject.cpchoose.models

import com.hsecourseproject.cpchoose.models.enums.CPMode
import com.hsecourseproject.cpchoose.models.enums.CPStatus
import com.hsecourseproject.cpchoose.models.enums.CPType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseProjectDTO(
    @SerialName("id")
    val id: Int?,

    @SerialName("professorEmail")
    val professorEmail: String?,

    @SerialName("userId")
    val userId: Int?,

    @SerialName("titleRus")
    val titleRus: String?,

    @SerialName("titleEng")
    val titleEng: String?,

    @SerialName("type")
    val type: CPType?,

    @SerialName("mode")
    val mode: CPMode?,

    @SerialName("membersCount")
    val membersCount: Int?,

    @SerialName("projectInitiator")
    val projectInitiator: String?,

    @SerialName("companySubdivision")
    val companySubdivision: String?,

    @SerialName("mentorFullName")
    val mentorFullName: String?,

    @SerialName("annotation")
    val annotation: String?,

    @SerialName("projectGoal")
    val projectGoal: String?,

    @SerialName("projectTasks")
    val projectTasks: String?,

    @SerialName("participantsTasks")
    val participantsTasks: String?,

    @SerialName("projectResults")
    val projectResults: String?,

    @SerialName("additionalInfo")
    val additionalInfo: String?,

    @SerialName("workPlace")
    val workPlace: String?,

    @SerialName("studentsRequirements")
    val studentsRequirements: String?,

    @SerialName("contacts")
    val contacts: String?,

    @SerialName("startDate")
    val startDate: String?,

    @SerialName("finishDate")
    val finishDate: String?,

    @SerialName("selectionForm")
    val selectionForm: String?,

    @SerialName("evaluationCriteria")
    val evaluationCriteria: String?,

    @SerialName("status")
    val status: CPStatus?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CourseProjectDTO

        if (id != other.id) return false
        if (professorEmail != other.professorEmail) return false
        if (userId != other.userId) return false
        if (titleRus != other.titleRus) return false
        if (titleEng != other.titleEng) return false
        if (type != other.type) return false
        if (mode != other.mode) return false
        if (membersCount != other.membersCount) return false
        if (projectInitiator != other.projectInitiator) return false
        if (companySubdivision != other.companySubdivision) return false
        if (mentorFullName != other.mentorFullName) return false
        if (annotation != other.annotation) return false
        if (projectGoal != other.projectGoal) return false
        if (projectTasks != other.projectTasks) return false
        if (participantsTasks != other.participantsTasks) return false
        if (projectResults != other.projectResults) return false
        if (additionalInfo != other.additionalInfo) return false
        if (workPlace != other.workPlace) return false
        if (studentsRequirements != other.studentsRequirements) return false
        if (contacts != other.contacts) return false
        if (startDate != other.startDate) return false
        if (finishDate != other.finishDate) return false
        if (selectionForm != other.selectionForm) return false
        if (evaluationCriteria != other.evaluationCriteria) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (professorEmail?.hashCode() ?: 0)
        result = 31 * result + (userId ?: 0)
        result = 31 * result + (titleRus?.hashCode() ?: 0)
        result = 31 * result + (titleEng?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (mode?.hashCode() ?: 0)
        result = 31 * result + (membersCount ?: 0)
        result = 31 * result + (projectInitiator?.hashCode() ?: 0)
        result = 31 * result + (companySubdivision?.hashCode() ?: 0)
        result = 31 * result + (mentorFullName?.hashCode() ?: 0)
        result = 31 * result + (annotation?.hashCode() ?: 0)
        result = 31 * result + (projectGoal?.hashCode() ?: 0)
        result = 31 * result + (projectTasks?.hashCode() ?: 0)
        result = 31 * result + (participantsTasks?.hashCode() ?: 0)
        result = 31 * result + (projectResults?.hashCode() ?: 0)
        result = 31 * result + (additionalInfo?.hashCode() ?: 0)
        result = 31 * result + (workPlace?.hashCode() ?: 0)
        result = 31 * result + (studentsRequirements?.hashCode() ?: 0)
        result = 31 * result + (contacts?.hashCode() ?: 0)
        result = 31 * result + (startDate?.hashCode() ?: 0)
        result = 31 * result + (finishDate?.hashCode() ?: 0)
        result = 31 * result + (selectionForm?.hashCode() ?: 0)
        result = 31 * result + (evaluationCriteria?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }
}
