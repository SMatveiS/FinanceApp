package com.example.data.di

import com.example.data.remote.account.AccountApi
import com.example.data.remote.category.CategoryApi
import com.example.data.remote.transaction.TransactionApi
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Модуль для подключения к серверу
 */

@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer bOIT5VdAvwCuTdoPkUCBhrzt")
            .build()

        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(authInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://shmr-finance.ru/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(
                Json { explicitNulls = true }.asConverterFactory("application/json; charset=UTF8".toMediaType())
            )
            .build()

    @Singleton
    @Provides
    fun providesAccountApi(retrofit: Retrofit): AccountApi =
        retrofit.create(AccountApi::class.java)

    @Singleton
    @Provides
    fun providesCategoryApi(retrofit: Retrofit): CategoryApi =
        retrofit.create(CategoryApi::class.java)

    @Singleton
    @Provides
    fun providesTransactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)

}
