package com.hsecourseproject.cpchoose.cpinfo

import android.app.Application
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsecourseproject.cpchoose.cpinfo.network.CPInfoNetwork
import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CPInfoViewModel(application: Application) : AndroidViewModel(application), Observable {

    var cpId = MutableLiveData<Int?>()

    var userId = MutableLiveData<Int?>()

    @Bindable
    var professorEmail = MutableLiveData<String?>()

    @Bindable
    var titleRus = MutableLiveData<String?>()

    @Bindable
    var titleEng = MutableLiveData<String?>()

    @Bindable
    var projectType = MutableLiveData<String?>()

    @Bindable
    var projectMode = MutableLiveData<String?>()

    @Bindable
    var membersCount = MutableLiveData<String?>()

    @Bindable
    var initiator = MutableLiveData<String?>()

    @Bindable
    var companySubdivision = MutableLiveData<String?>()

    @Bindable
    var mentor = MutableLiveData<String?>()

    @Bindable
    var cpAnnotation = MutableLiveData<String?>()

    @Bindable
    var goal = MutableLiveData<String?>()

    @Bindable
    var tasks = MutableLiveData<String?>()

    @Bindable
    var participantsTasks = MutableLiveData<String?>()

    @Bindable
    var results = MutableLiveData<String?>()

    @Bindable
    var additionalInfo = MutableLiveData<String?>()

    @Bindable
    var workplace = MutableLiveData<String?>()

    @Bindable
    var studentsRequirements = MutableLiveData<String?>()

    @Bindable
    var contacts = MutableLiveData<String?>()

    @Bindable
    var selectionForm = MutableLiveData<String?>()

    @Bindable
    var evaluationCriteria = MutableLiveData<String?>()

    @Bindable
    var startDate = MutableLiveData<String?>()

    @Bindable
    var finishDate = MutableLiveData<String?>()

    var courseProjectDTO: CourseProjectDTO? = null


    fun initFields(cp: CourseProjectDTO) {
        courseProjectDTO = cp
        cpId.value = cp.id
        professorEmail.value = cp.professorEmail
        userId.value = cp.userId
        titleRus.value = cp.titleRus
        titleEng.value = cp.titleEng
        projectType.value = cp.type?.type
        projectMode.value = cp.mode?.mode
        membersCount.value = cp.membersCount?.toString()
        initiator.value = cp.projectInitiator
        companySubdivision.value = cp.companySubdivision
        mentor.value = cp.mentorFullName
        cpAnnotation.value = cp.annotation
        goal.value = cp.projectGoal
        tasks.value = cp.projectTasks
        participantsTasks.value = cp.participantsTasks
        results.value = cp.projectResults
        additionalInfo.value = cp.additionalInfo
        workplace.value = cp.workPlace
        studentsRequirements.value = cp.studentsRequirements
        contacts.value = cp.contacts
        startDate.value = cp.startDate
        finishDate.value = cp.finishDate
        selectionForm.value = cp.selectionForm
        evaluationCriteria.value = cp.evaluationCriteria
    }

    fun applyRequest() {
        // TODO: add navigation to cpFullList
        // TODO: bind fields of courseProjectDTO instead of each field separately

        CPInfoNetwork.cpInfoApiService.applyCPRequest(
            UtilsSingleton.INSTANCE.getUserEmail()!!,
            courseProjectDTO!!,
        ).enqueue(
            object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    if (response.body()!!) {
                        Toast.makeText(getApplication(), "Request was sent!", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(getApplication(), "Request wasn't sent!", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    t.printStackTrace()
                }
            }
        )
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}