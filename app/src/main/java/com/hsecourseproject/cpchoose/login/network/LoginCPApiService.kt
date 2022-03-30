package com.hsecourseproject.cpchoose.login.network

import com.hsecourseproject.cpchoose.models.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginCPApiService {

    @Headers("Content-Type: application/json")
    @POST("saveUser")
    fun saveUser(
        @Body user: UserDTO
    ): Call<UserDTO>

    @POST("checkCode")
    fun checkCode(
        @Body user: UserDTO
    ): Call<Boolean>
}