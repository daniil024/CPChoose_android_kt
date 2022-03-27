package com.hsecourseproject.cpchoose.cplist

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.cplist.network.CPNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CPListViewModel(application: Application) : AndroidViewModel(application), Observable {

    private val _cpData = MutableLiveData<List<CourseProjectDTO>>()

    val cpData: LiveData<List<CourseProjectDTO>>
        get() = _cpData

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
                    val result = response.body()
                    _cpData.value = result!!
                }
            }
        )
    }

    fun onFullListClicked(v: View) {
        Log.i("my_tag1", _cpData.value.toString())
        Log.i("my_tag1", "tttt")
    }

    fun onCreatedListClicked(v: View) {
        Log.i("my_tag1", _cpData.value.toString())

    }

    fun onProposedListClicked(v: View) {
        Log.i("my_tag1", _cpData.value.toString())

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}