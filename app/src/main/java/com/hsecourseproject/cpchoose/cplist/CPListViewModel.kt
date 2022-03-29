package com.hsecourseproject.cpchoose.cplist

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.button.MaterialButtonToggleGroup
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.cplist.models.ApprovedCPDTO
import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.cplist.network.CPNetwork
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CPListViewModel(application: Application) : AndroidViewModel(application), Observable {

    private val _cpListData = MutableLiveData<List<CourseProjectDTO>>()

    val cpListData: LiveData<List<CourseProjectDTO>>
        get() = _cpListData


    private val _cpFullListData = MutableLiveData<List<CourseProjectDTO>>()

    val cpFullListData: LiveData<List<CourseProjectDTO>>
        get() = _cpFullListData

    private val _cpCreatedByUserData = MutableLiveData<List<CourseProjectDTO>>()

    val cpCreatedByUserData: LiveData<List<CourseProjectDTO>>
        get() = _cpCreatedByUserData

    private val _cpProposedToUserData = MutableLiveData<List<ApprovedCPDTO>>()

    val cpProposedToUserData: LiveData<List<ApprovedCPDTO>>
        get() = _cpProposedToUserData

    private val _cpFullList = MutableLiveData<Boolean>()

    val cpFullList: LiveData<Boolean>
        get() = _cpFullList

    private val _createdByUser = MutableLiveData<Boolean>()

    val createdByUser: LiveData<Boolean>
        get() = _createdByUser

    private val _proposedToUser = MutableLiveData<Boolean>()

    val proposedToUser: LiveData<Boolean>
        get() = _proposedToUser

    fun getAllAvailableCP() {
        CPNetwork.cpApiService.getCourseProjects().enqueue(
            object : Callback<List<CourseProjectDTO>> {
                override fun onFailure(call: Call<List<CourseProjectDTO>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<CourseProjectDTO>>,
                    response: Response<List<CourseProjectDTO>>
                ) {
                    _cpListData.value = response.body()
                }
            }
        )
    }

    fun getCPCreatedByUser() {
        CPNetwork.cpApiService.getCourseProjectByUserId(UtilsSingleton.INSTANCE.getUserId())
            .enqueue(
                object : Callback<List<CourseProjectDTO>> {
                    override fun onFailure(call: Call<List<CourseProjectDTO>>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<List<CourseProjectDTO>>,
                        response: Response<List<CourseProjectDTO>>
                    ) {
                        _cpListData.value = response.body()
                    }
                }
            )
    }

    fun getCPProposedToProfessor() {
        CPNetwork.cpApiService.getProposedCPByProfessorId(
            UtilsSingleton.INSTANCE.getUserProfessorId()
        ).enqueue(
            object : Callback<List<ApprovedCPDTO>> {
                override fun onFailure(call: Call<List<ApprovedCPDTO>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<ApprovedCPDTO>>,
                    response: Response<List<ApprovedCPDTO>>
                ) {
                    _cpProposedToUserData.value = response.body()
                }
            }
        )
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}