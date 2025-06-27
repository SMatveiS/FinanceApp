package com.example.myfinance.feature.di.module

import com.example.myfinance.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", BuildConfig.TOKEN)
            .build()

        return chain.proceed(newRequest)
    }
}
