package com.hsecourseproject.cpchoose.cplist.network

import com.hsecourseproject.cpchoose.models.ApprovedCPDTO
import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import retrofit2.Call
import retrofit2.http.*

interface CPApiService {

//    @POST("getCourseProjects/{id}")
//    @FormUrlEncoded
//    fun getCourseProjects(
//        @Path("id") id: Int,
//        @FieldMap params: HashMap<String?, String?>
//    ): Call<List<CourseProjectDTO>>

    @GET("getCourseProjects/{id}")
    fun getCourseProjects(
        @Path("id") id: Int,
        @Query("professorSurname") professorSurname: String?,
        @Query("word") word: String?
    ): Call<List<CourseProjectDTO>>

    @GET("getCourseProjectByUserId/{id}")
    fun getCourseProjectByUserId(@Path("id") id: Int): Call<List<CourseProjectDTO>>

    @GET("getProposedCP/{id}")
    fun getProposedCPByProfessorId(@Path("id") id: Int): Call<List<ApprovedCPDTO>>
}