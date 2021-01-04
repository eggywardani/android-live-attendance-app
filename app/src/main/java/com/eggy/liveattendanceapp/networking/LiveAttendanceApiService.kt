package com.eggy.liveattendanceapp.networking

import com.eggy.liveattendanceapp.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LiveAttendanceApiService {
    @Headers("Accept: application/json", "Content-Type:application/json")
    @POST("auth/login")
    fun loginRequest(
        @Body body:String
    ):Call<LoginResponse>




}