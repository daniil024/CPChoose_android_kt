package com.hsecourseproject.cpchoose.createcp

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsecourseproject.cpchoose.createcp.network.CreateCPNetwork
import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.models.UserDTO
import com.hsecourseproject.cpchoose.models.enums.CPMode
import com.hsecourseproject.cpchoose.models.enums.CPType
import com.hsecourseproject.cpchoose.models.enums.UserType
import com.hsecourseproject.cpchoose.profile.network.ProfileNetwork
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*

class CreateCPViewModel(application: Application) : AndroidViewModel(application), Observable {

    @Bindable
    var professorEmail = MutableLiveData<String?>()

    @Bindable
    var titleRus = MutableLiveData<String?>()

    @Bindable
    var titleEng = MutableLiveData<String?>()

    @Bindable
    var projectType = MutableLiveData<Int?>()

    @Bindable
    var projectMode = MutableLiveData<Int?>()

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

    var startDate = MutableLiveData<String>()

    var finishDate = MutableLiveData<String>()

    private val _navigateToList = MutableLiveData<Boolean>()

    val navigateToList: LiveData<Boolean>
        get() = _navigateToList


    fun fillProfessorData() {
        if (UtilsSingleton.INSTANCE.getUserType() == UserType.STUDENT) {
            CreateCPNetwork.createCPApiService.getUser(UtilsSingleton.INSTANCE.getUserEmail())
                .enqueue(
                    object : Callback<UserDTO> {
                        override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                            t.printStackTrace()
                        }

                        override fun onResponse(
                            call: Call<UserDTO>,
                            response: Response<UserDTO>
                        ) {
                            val user = response.body()
                            professorEmail.value = user?.email
                            mentor.value =
                                user?.firstName + " " + user?.lastName + " " + user?.patronymic
                            initiator.value = user?.professor?.companyName
                            companySubdivision.value = user?.professor?.subdivision
                        }
                    }
                )
        }
    }

    private fun verificateCPFormData(): Boolean {
        // TODO: check that all required fields are filled
        // Check membersCount and projectType together
        return false
    }

    private fun createCP(): CourseProjectDTO {
        return CourseProjectDTO(
            id = null,
            userId = UtilsSingleton.INSTANCE.getUserId(),
            professorEmail = professorEmail.value,
            titleRus = titleRus.value,
            titleEng = titleEng.value,
            type = UtilsSingleton.INSTANCE.getCPType(projectType.value ?: 0),
            mode = UtilsSingleton.INSTANCE.getCPMode(projectMode.value ?: 0),
            membersCount = membersCount.value?.toInt() ?: 1,
            projectInitiator = initiator.value,
            companySubdivision = companySubdivision.value,
            mentorFullName = mentor.value,
            annotation = cpAnnotation.value,
            projectGoal = goal.value,
            projectTasks = tasks.value,
            participantsTasks = participantsTasks.value,
            projectResults = results.value,
            additionalInfo = additionalInfo.value,
            workPlace = workplace.value,
            studentsRequirements = studentsRequirements.value,
            contacts = contacts.value,
            startDate = startDate.value,
            finishDate = finishDate.value,
            selectionForm = selectionForm.value,
            evaluationCriteria = evaluationCriteria.value,
            status = null,
        )
    }

    fun sendCP() {
        val createCpApiService = CreateCPNetwork.createCPApiService
        createCpApiService.createCourseProject(createCP()).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Toast.makeText(getApplication(), "CP was sent", Toast.LENGTH_LONG).show()
                    _navigateToList.value = true
                }
            }
        )
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    fun onStartDateChanged(v: DatePicker, y: Int, m: Int, d: Int) {
        // TODO: set initial value for datepicker
        startDate.value = "$y-$m-$d"
    }

    fun onFinishDateChanged(v: DatePicker, y: Int, m: Int, d: Int) {
        // TODO: set initial value for datepicker
        finishDate.value = "$y-$m-$d"
    }
}