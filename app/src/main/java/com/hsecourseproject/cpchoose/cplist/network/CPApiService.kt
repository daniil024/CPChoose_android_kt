package com.hsecourseproject.cpchoose.cplist.network

import com.hsecourseproject.cpchoose.cplist.models.ApprovedCPDTO
import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CPApiService {

    @GET("getCourseProjects")
    fun getCourseProjects(): Call<List<CourseProjectDTO>>

    @GET("getCourseProjectByUserId/{id}")
    fun getCourseProjectByUserId(@Path("id") id: Int): Call<List<CourseProjectDTO>>

    @GET("getProposedCP/{id}")
    fun getProposedCPByProfessorId(@Path("id") id:Int):Call<List<ApprovedCPDTO>>
}