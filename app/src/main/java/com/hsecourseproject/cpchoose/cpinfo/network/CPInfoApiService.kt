package com.hsecourseproject.cpchoose.cpinfo.network

import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.models.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CPInfoApiService {

    @POST("applyCPRequest/{userEmail}")
    fun applyCPRequest(
        @Path("userEmail") userEmail:String,
        @Body courseProjectDTO: CourseProjectDTO
    ): Call<Boolean>
}