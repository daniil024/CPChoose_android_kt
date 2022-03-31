package com.hsecourseproject.cpchoose.createcp

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hsecourseproject.cpchoose.createcp.network.CreateCPNetwork
import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.models.enums.CPMode
import com.hsecourseproject.cpchoose.models.enums.CPType
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateCPViewModel(application: Application) : AndroidViewModel(application), Observable {

    @Bindable
    var titleRus = MutableLiveData<String>()

    @Bindable
    var titleEng = MutableLiveData<String>()

    @Bindable
    var projectType = MutableLiveData<String>()

    @Bindable
    var projectMode = MutableLiveData<String>()

    @Bindable
    var membersCount = MutableLiveData<String>()

    @Bindable
    var initiator = MutableLiveData<String>()

    @Bindable
    var companySubdivision = MutableLiveData<String>()

    @Bindable
    var mentor = MutableLiveData<String>()

    @Bindable
    var cpAnnotation = MutableLiveData<String>()

    @Bindable
    var goal = MutableLiveData<String>()

    @Bindable
    var tasks = MutableLiveData<String>()

    @Bindable
    var participantsTasks = MutableLiveData<String>()

    @Bindable
    var results = MutableLiveData<String>()

    @Bindable
    var additionalInfo = MutableLiveData<String>()

    @Bindable
    var workplace = MutableLiveData<String>()

    @Bindable
    var studentsRequirements = MutableLiveData<String>()

    @Bindable
    var contacts = MutableLiveData<String>()

    @Bindable
    var selectionForm = MutableLiveData<String>()

    @Bindable
    var evaluationCriteria = MutableLiveData<String>()

    var courseProjectDTO: CourseProjectDTO? = null

    private fun verificateCPFormData(): Boolean {
        // TODO: check that all required fields are filled
        return false
    }

    private fun createCP() {
        // TODO: add binding for spinners
        // TODO: create here CP object and save it to courseProjectDTO
    }

    fun sendCP() {
        val createCpApiService = CreateCPNetwork.createCPApiService
        createCpApiService.createCourseProject(
            CourseProjectDTO(
                null,
                UtilsSingleton.INSTANCE.getUserId(),
                titleRus = titleRus.value,
                titleEng = titleEng.value,
                type = CPType.PROGRAM,
                mode = CPMode.COMMAND,
                membersCount = membersCount.value?.toInt() ?: 0,
                null,
                null,
                "Сосновский Г.М.",
                annotation = "Just some default annotation!",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
            )
        ).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Toast.makeText(getApplication(), "CP was sent", Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}