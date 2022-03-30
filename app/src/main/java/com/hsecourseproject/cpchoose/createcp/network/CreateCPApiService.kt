package com.hsecourseproject.cpchoose.createcp.network

import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateCPApiService {

    @POST("sendToCPPR")
    fun sendToCPPR(
        @Body courseProjectDTO: CourseProjectDTO
    ): Call<ResponseBody>
}