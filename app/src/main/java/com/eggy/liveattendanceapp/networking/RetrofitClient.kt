package com.eggy.liveattendanceapp.networking

import com.eggy.liveattendanceapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val REQUEST_TIME_OUT = 30

    fun getClient():Retrofit{
        val gsonBuilder = GsonBuilder().setLenient().create()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}