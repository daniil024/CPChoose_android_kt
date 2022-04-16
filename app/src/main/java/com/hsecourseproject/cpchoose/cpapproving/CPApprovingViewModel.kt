package com.hsecourseproject.cpchoose.cpapproving

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsecourseproject.cpchoose.cpapproving.network.CPApprovingNetwork
import com.hsecourseproject.cpchoose.cpapproving.recycler.ClickListener
import com.hsecourseproject.cpchoose.models.ApprovedCPDTO
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class CPApprovingViewModel(application: Application) : AndroidViewModel(application), Observable,
    ClickListener {

    private val _cpApprovingFullListData = MutableLiveData<List<ApprovedCPDTO>>()

    val cpApprovingFullListData: LiveData<List<ApprovedCPDTO>>
        get() = _cpApprovingFullListData


    override fun onAcceptClicked(approvedCPDTO: ApprovedCPDTO) {
        CPApprovingNetwork.cpApprovingApiService.approveCP(approvedCPDTO).enqueue(
            object : Callback<List<ApprovedCPDTO>> {
                override fun onResponse(call: Call<List<ApprovedCPDTO>>, response: Response<List<ApprovedCPDTO>>) {
                    Toast.makeText(getApplication(), "accept", Toast.LENGTH_LONG).show()
                    _cpApprovingFullListData.value = response.body()
                }

                override fun onFailure(call: Call<List<ApprovedCPDTO>>, t: Throwable) {
                    t.printStackTrace()
                }
            }
        )
    }

    override fun onDeclineClicked(approvedCPDTO: ApprovedCPDTO) {
        CPApprovingNetwork.cpApprovingApiService.declineCP(approvedCPDTO).enqueue(
        object : Callback<List<ApprovedCPDTO>> {
            override fun onResponse(call: Call<List<ApprovedCPDTO>>, response: Response<List<ApprovedCPDTO>>) {
                Toast.makeText(getApplication(), "decline", Toast.LENGTH_LONG).show()
                _cpApprovingFullListData.value = response.body()
            }

            override fun onFailure(call: Call<List<ApprovedCPDTO>>, t: Throwable) {
                t.printStackTrace()
            }
        }
    )
    }

    fun getWaitingForApproving() {
        CPApprovingNetwork.cpApprovingApiService.getWaitingForApprovingCP(UtilsSingleton.INSTANCE.getUserEmail())
            .enqueue(
                object : Callback<List<ApprovedCPDTO>> {
                    override fun onResponse(
                        call: Call<List<ApprovedCPDTO>>,
                        response: Response<List<ApprovedCPDTO>>
                    ) {
                        val text = response.toString()
                        Log.i("my_tag:", text)
                        _cpApprovingFullListData.value = response.body()
                    }

                    override fun onFailure(call: Call<List<ApprovedCPDTO>>, t: Throwable) {
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