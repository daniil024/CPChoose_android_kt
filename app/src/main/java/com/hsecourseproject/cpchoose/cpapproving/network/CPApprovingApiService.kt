package com.hsecourseproject.cpchoose.cpapproving.network

import com.hsecourseproject.cpchoose.models.ApprovedCPDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CPApprovingApiService {

    @GET("getWaitingForApprovingCP/{userEmail}")
    fun getWaitingForApprovingCP(
        @Path("userEmail") userEmail: String?
    ): Call<List<ApprovedCPDTO>>

    @POST("approveCP")
    fun approveCP(
        @Body approvedCPDTO: ApprovedCPDTO
    ): Call<List<ApprovedCPDTO>>

    @POST("declineCP")
    fun declineCP(
        @Body approvedCPDTO: ApprovedCPDTO
    ): Call<List<ApprovedCPDTO>>
}