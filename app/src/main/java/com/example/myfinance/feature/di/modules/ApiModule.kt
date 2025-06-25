package com.example.myfinance.feature.di.modules

import com.example.myfinance.data.api.account.AccountApi
import com.example.myfinance.data.api.category.CategoryApi
import com.example.myfinance.data.api.transaction.TransactionApi
import com.example.myfinance.data.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()


    @Singleton
    @Provides
    fun providesOkHttpClient(authInterceptor: AuthInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://shmr-finance.ru/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(
                Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
            )
            .build()

    @Singleton
    @Provides
    fun providesAccountApi(retrofit: Retrofit) =
        retrofit.create(AccountApi::class.java)

    @Singleton
    @Provides
    fun providesCategoryApi(retrofit: Retrofit) =
        retrofit.create(CategoryApi::class.java)

    @Singleton
    @Provides
    fun providesTransactionApi(retrofit: Retrofit) =
        retrofit.create(TransactionApi::class.java)
}