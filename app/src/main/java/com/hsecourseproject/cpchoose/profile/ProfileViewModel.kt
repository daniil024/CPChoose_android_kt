package com.hsecourseproject.cpchoose.profile

import android.app.Application
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hsecourseproject.cpchoose.models.ProfessorDTO
import com.hsecourseproject.cpchoose.models.UserDTO
import com.hsecourseproject.cpchoose.profile.network.ProfileNetwork
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(application: Application) : AndroidViewModel(application), Observable {

    @Bindable
    var firstName = MutableLiveData<String?>()

    @Bindable
    var lastName = MutableLiveData<String?>()

    @Bindable
    var patronymic = MutableLiveData<String?>()

    @Bindable
    var companyName = MutableLiveData<String?>()

    @Bindable
    var subdivision = MutableLiveData<String?>()

    @Bindable
    var position = MutableLiveData<String?>()

    // Add field for edit texts and when updateDataBtn is clicked rewrite Professor instance here
    // Initialize when screen is opened
    var userDTO: UserDTO? = null

    fun updateProfessorData() {
        userDTO?.firstName = firstName.value
        userDTO?.lastName = lastName.value
        userDTO?.patronymic = patronymic.value
        userDTO?.professor?.companyName = companyName.value
        userDTO?.professor?.subdivision = subdivision.value
        userDTO?.professor?.position = position.value

        ProfileNetwork.profileApiService.updateProfessor(userDTO).enqueue(
            object : Callback<UserDTO> {
                override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                    Toast.makeText(getApplication(), "User data was updated", Toast.LENGTH_LONG)
                        .show()
                }
            }
        )
    }

    fun getProfessor() {
        ProfileNetwork.profileApiService.getUser(UtilsSingleton.INSTANCE.getUserEmail()).enqueue(
            object : Callback<UserDTO> {
                override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<UserDTO>,
                    response: Response<UserDTO>
                ) {
                    userDTO = response.body()
                    fillData(userDTO)
                }
            }
        )
    }

    fun fillData(userDTO: UserDTO?) {
        firstName.value = userDTO?.firstName
        lastName.value = userDTO?.lastName
        patronymic.value = userDTO?.patronymic
        companyName.value = userDTO?.professor?.companyName
        subdivision.value = userDTO?.professor?.subdivision
        position.value = userDTO?.professor?.position
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}