package com.hsecourseproject.cpchoose.login

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsecourseproject.cpchoose.login.LoginUtils.Companion.isEmailCorrect
import com.hsecourseproject.cpchoose.login.models.UserResponse
import com.hsecourseproject.cpchoose.login.network.LoginNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragmentViewModel(application: Application) :
    AndroidViewModel(application), Observable {

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputCode = MutableLiveData<String>()

    private val _navigateToFinish = MutableLiveData<Boolean>()

    val navigateToFinish: LiveData<Boolean>
        get() = _navigateToFinish

    private val _errorToastEmail = MutableLiveData<Boolean>()

    val errorToastEmail: LiveData<Boolean>
        get() = _errorToastEmail

    private val _errorToastEmailDomain = MutableLiveData<Boolean>()

    val errorToastEmailDomain: LiveData<Boolean>
        get() = _errorToastEmailDomain

    private val _errorToastCode = MutableLiveData<Boolean>()

    val errorToastCode: LiveData<Boolean>
        get() = _errorToastCode

    fun confirmEmail() {
        // Logic for "Подтвердить почту" button
        if (inputEmail.value == null) {
            _errorToastEmail.value = true
        } else if (!isEmailCorrect(inputEmail.value!!)) {
            _errorToastEmailDomain.value = true
        } else {
            val loginCPApiService = LoginNetwork.loginCPApiService

//            val user = UserResponse(email = inputEmail.value, id = null, firstName = null,
//            lastName = null, patronymic = null, userType = null, professor = null, student = null)
            val user = "\"email\":\"dtsurkan@edu.hse.ru\""
            loginCPApiService.saveUser(user).enqueue(
                object : Callback<UserResponse>{
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.i("User_exc: ", "was an exc")
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        val userRes = response.body()
                        Log.i("USER_RESPONSE:", userRes.toString())
                        _navigateToFinish.value = true
                    }
                }
            )
        }
    }

    fun login() {
        // Logic for "Войти" button
    }

    fun doneToastEmail() {
        _errorToastEmail.value = false
    }

    fun doneToastEmailDomain(){
        _errorToastEmailDomain.value = false
    }

    fun doneToastCode(){
        _errorToastCode.value = false
    }

    fun doneToastFinish(){
        _navigateToFinish.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}