package com.example.myfinance.data.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(okHttpClient: OkHttpClient): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://shmr-finance.ru/api/v1/")
                .client(okHttpClient)
                .addConverterFactory(
                    Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
                )
                .build()
        }

        return retrofit!!
    }
}