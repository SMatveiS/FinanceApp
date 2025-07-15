package com.example.myfinance.data.utils

import retrofit2.Response

/**
 * Функциея безопасного похода в сеть
  */

suspend fun <T> safeApiCall(api: suspend () -> Response<T>): Result<T> {
    try {
        val response = api()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return Result.success(body)
            } ?: return Result.failure(Throwable("No response body"))
        } else {
            return Result.failure(Throwable("${response.code()} ${response.message()}"))
        }
    } catch(e: Exception) {
        return Result.failure(Throwable("${e.message}"))
    }
}

