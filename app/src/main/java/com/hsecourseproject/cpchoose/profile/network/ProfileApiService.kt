package com.hsecourseproject.cpchoose.profile.network

import com.hsecourseproject.cpchoose.models.ProfessorDTO
import com.hsecourseproject.cpchoose.models.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileApiService {
    @GET("/getProfessorData/{userEmail}")
    fun getUser(@Path("userEmail") userEmail: String?): Call<UserDTO>

    @POST("/updateProfessor")
    fun updateProfessor(@Body userDTO: UserDTO?): Call<UserDTO>
}