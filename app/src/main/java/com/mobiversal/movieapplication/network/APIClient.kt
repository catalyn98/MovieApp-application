package com.mobiversal.movieapplication.network

import com.mobiversal.movieapplication.network.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APIClient private constructor() {
    companion object {
        val instance = APIClient()
    }

    private val loggingInterceptor: LoggingInterceptor by lazy {
        LoggingInterceptor().apply {
            this.setLevel(LoggingInterceptor.Level.BODY)
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}


