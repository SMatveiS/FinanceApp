package com.example.myfinance.feature.di.modules

import com.example.myfinance.data.model.AccountDto
import com.example.myfinance.data.network.AuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

//@Module
//@InstallIn(SingletonComponent::class)
//object ApiModule {
//
//    @Singleton
//    @Provides
//    fun providesOkHttpClient() =
//        OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor())
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build()
//
//    @Singleton
//    @Provides
//    fun providesRetrofit(okHttpClient: OkHttpClient) =
//        Retrofit.Builder()
//            .baseUrl("https://shmr-finance.ru/api/v1/")
//            .client(okHttpClient)
//            .addConverterFactory(
//                Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
//            )
//            .build()
//
//    @Singleton
//    @Provides
//    fun providesAccountApi(retrofit: Retrofit) =
//        retrofit.create(AccountDto::class.java)
//}