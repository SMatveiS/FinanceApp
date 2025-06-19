package com.example.myfinance.data.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpClient {
    private var okHttpClient: OkHttpClient? = null

    fun getClient(): OkHttpClient {
        if (okHttpClient == null){
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        return okHttpClient!!
    }

}