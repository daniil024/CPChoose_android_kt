package com.hsecourseproject.cpchoose.cplist.network

import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO
import retrofit2.Call
import retrofit2.http.GET

interface CPApiService {

    @GET("getCourseProjects")
    fun getCourseProjects(): Call<List<CourseProjectDTO>>
}