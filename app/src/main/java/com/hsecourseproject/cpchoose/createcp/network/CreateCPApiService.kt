package com.hsecourseproject.cpchoose.createcp.network

import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.models.UserDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CreateCPApiService {

    @GET("/getProfessorData/{userEmail}")
    fun getUser(@Path("userEmail") userEmail: String?): Call<UserDTO>

    @POST("createCourseProject")
    fun createCourseProject(
        @Body courseProjectDTO: CourseProjectDTO
    ): Call<ResponseBody>
}