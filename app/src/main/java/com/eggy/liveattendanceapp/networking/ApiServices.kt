package com.eggy.liveattendanceapp.networking

object ApiServices
{
    fun getLiveAttendanceServices():LiveAttendanceApiService{

        return RetrofitClient
            .getClient()
            .create(LiveAttendanceApiService::class.java)
    }
}