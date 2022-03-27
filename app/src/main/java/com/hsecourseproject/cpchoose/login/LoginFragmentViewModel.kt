package com.hsecourseproject.cpchoose.login

import android.app.Application
import android.content.Context
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.login.LoginUtils.Companion.isEmailCorrect
import com.hsecourseproject.cpchoose.login.models.UserDTO
import com.hsecourseproject.cpchoose.login.models.enums.UserType
import com.hsecourseproject.cpchoose.login.network.LoginNetwork
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragmentViewModel(application: Application) :
    AndroidViewModel(application), Observable {

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputCode = MutableLiveData<String>()

    var userType = UserType.STUDENT

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


    private val _studentButtonClicked = MutableLiveData<Boolean>()

    val studentButtonClicked: LiveData<Boolean>
        get() = _studentButtonClicked

    private val _professorButtonClicked = MutableLiveData<Boolean>()

    val professorButtonClicked: LiveData<Boolean>
        get() = _professorButtonClicked


    fun confirmEmail() {
        if (inputEmail.value == null) {
            _errorToastEmail.value = true
        } else if (!isEmailCorrect(inputEmail.value!!)) {
            _errorToastEmailDomain.value = true
        } else {
            val loginCPApiService = LoginNetwork.loginCPApiService

            val user = UserDTO(
                email = inputEmail.value,
                id = null,
                firstName = null,
                code = null,
                lastName = null,
                patronymic = null,
                userType = userType,
                professor = null,
                student = null
            )
            _navigateToFinish.value = true

            loginCPApiService.saveUser(user).enqueue(
                object : Callback<UserDTO> {
                    override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<UserDTO>,
                        response: Response<UserDTO>
                    ) {
                        val userResponse = response.body()

                        UtilsSingleton.init(getApplication())

                        val sharedPreference = getApplication<Application>().getSharedPreferences(
                            getApplication<Application>().getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE
                        )

                        val editor = sharedPreference.edit()
                        val userEmail =
                            getApplication<Application>().resources.getString(R.string.user_email)
                        editor.putString(userEmail, userResponse?.email)
                        val userType =
                            getApplication<Application>().resources.getString(R.string.user_type)
                        editor.putString(userType, userResponse?.userType?.name)
                        editor.apply()

                        UtilsSingleton.INSTANCE.writeUserId(userResponse?.id ?: 0)
                    }
                }
            )
        }
    }

    fun onFocusChange(v: View, hasFocus: Boolean) {
        val hintText: String
        if (hasFocus) {
            hintText = if (v.id == R.id.emailEditText)
                getApplication<Application>().resources.getString(R.string.input_email_hint_focused)
            else getApplication<Application>().resources.getString(R.string.input_code_hint_focused)

            (v.parent.parent as TextInputLayout).hint = hintText
        } else {
            hintText = if (v.id == R.id.emailEditText)
                getApplication<Application>().resources.getString(R.string.input_email_hint_unfocused)
            else getApplication<Application>().resources.getString(R.string.input_code_hint_unfocused)
            if (inputEmail.value.isNullOrEmpty() && v.id == R.id.emailEditText) {
                (v.parent.parent as TextInputLayout).hint = hintText
            } else if (inputCode.value.isNullOrEmpty() && v.id == R.id.codeEditText) {
                (v.parent.parent as TextInputLayout).hint = hintText
            }
        }
    }

    fun login() {
        val loginCPApiService = LoginNetwork.loginCPApiService
        val user = UserDTO(
            email = inputEmail.value, id = null, firstName = null,
            code = inputCode.value, lastName = null, patronymic = null,
            userType = null, professor = null, student = null
        )

        loginCPApiService.checkCode(user).enqueue(
            object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    val isCodeRight = response.body()
                    if (isCodeRight!!) {
                        val sharedPreference = getApplication<Application>().getSharedPreferences(
                            getApplication<Application>().getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE
                        )
                        val editor = sharedPreference.edit()
                        val loggedTag =
                            getApplication<Application>().resources.getString(R.string.is_logged_in)
                        editor.putBoolean(loggedTag, isCodeRight)
                        editor.apply()
                    } else {
                        _errorToastCode.value = true
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    t.printStackTrace()
                }

            }
        )
    }

    fun professorClicked(v: View) {
        _professorButtonClicked.value = true
        userType = UserType.PROFESSOR
    }

    fun studentClicked(v: View) {
        _studentButtonClicked.value = true
        userType = UserType.STUDENT
    }

    fun doneToastEmail() {
        _errorToastEmail.value = false
    }

    fun doneToastEmailDomain() {
        _errorToastEmailDomain.value = false
    }

    fun doneToastCode() {
        _errorToastCode.value = false
    }

    fun doneToastFinish() {
        _navigateToFinish.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}