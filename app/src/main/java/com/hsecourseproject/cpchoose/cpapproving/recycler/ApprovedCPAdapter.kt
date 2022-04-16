package com.hsecourseproject.cpchoose.cpapproving.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.models.*
import com.hsecourseproject.cpchoose.models.enums.CPMode
import com.hsecourseproject.cpchoose.models.enums.CPStatus
import com.hsecourseproject.cpchoose.models.enums.CPType
import com.hsecourseproject.cpchoose.models.enums.UserType
import com.hsecourseproject.cpchoose.utils.UtilsSingleton

class ApprovedCPAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<ApprovedCPViewHolder>() {

    private var approvedCP: List<ApprovedCPDTO> =
        listOf(
//            ApprovedCPDTO(
//                4, UserDTO(
//                    77,
//                    "email",
//                    "code",
//                    "firstName",
//                    "lastName",
//                    "patronymic",
//                    UserType.PROFESSOR,
//                    ProfessorDTO(5, "companyName", 6, "subdivision", "position"),
//                    StudentDTO(
//                        7,
//                        "faculty",
//                        "speciality",
//                        99,
//                        4,
//                        "firstName",
//                        "lastName",
//                        "patronymic"
//                    ),
//                ),
//                UserDTO(
//                    77,
//                    "email",
//                    "code",
//                    "firstName",
//                    "lastName",
//                    "patronymic",
//                    UserType.PROFESSOR,
//                    ProfessorDTO(5, "companyName", 6, "subdivision", "position"),
//                    StudentDTO(
//                        7,
//                        "faculty",
//                        "speciality",
//                        99,
//                        4,
//                        "firstaName",
//                        "lastName",
//                        "patronymic"
//                    ),
//                ),
//                CourseProjectDTO(
//                    id = 4,
//                    userId = UtilsSingleton.INSTANCE.getUserId(),
//                    professorEmail = "professorEmail",
//                    titleRus = "titleRus",
//                    titleEng = "titleEng",
//                    type = CPType.PROGRAM,
//                    mode = CPMode.COMMAND,
//                    membersCount = 1,
//                    projectInitiator = "initiator.value",
//                    companySubdivision = "companySubdivision.value",
//                    mentorFullName = "mentor.value",
//                    annotation = "cpAnnotation.value",
//                    projectGoal = "goal.value",
//                    projectTasks = "tasks.value",
//                    participantsTasks = "participantsTasks.value",
//                    projectResults = "results.value",
//                    additionalInfo = "additionalInfo.value",
//                    workPlace = "workplace.value",
//                    studentsRequirements = "studentsRequirements.value",
//                    contacts = "contacts.value",
//                    startDate = "startDate.value",
//                    finishDate = "finishDate.value",
//                    selectionForm = "selectionForm.value",
//                    evaluationCriteria = "evaluationCriteria.value",
//                    status = CPStatus.APPROVED,
//                )
//            )
        )

    fun bindApprovedCourseProjects(newApprovedCP: List<ApprovedCPDTO>) {
        approvedCP = newApprovedCP
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApprovedCPViewHolder {
        return ApprovedCPViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cp_approving_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ApprovedCPViewHolder, position: Int) {
        holder.onBind(approvedCP[position], clickListener)
    }

    override fun getItemCount(): Int = approvedCP.size
}

interface ClickListener {
    fun onAcceptClicked(approvedCPDTO: ApprovedCPDTO)
    fun onDeclineClicked(approvedCPDTO: ApprovedCPDTO)
}