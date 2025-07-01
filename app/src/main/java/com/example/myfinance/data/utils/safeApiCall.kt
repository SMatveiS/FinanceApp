package com.example.myfinance.data.utils

import retrofit2.Response

/**
 * Абстрактный класс с функцией безопасного похода в сеть
 *
 * Возвращает NetworkResult.Error(errorMessage) в случае ошибки, иначе - NetworkResult.Success(data)
 */

suspend fun <T> safeApiCall(api: suspend () -> Response<T>): NetworkResult<T> {
    try {
        val response = api()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return NetworkResult.Success(body)
            } ?: return NetworkResult.Error(errorMessage = "Ошибка: пустое тело ответа")
        } else {
            return NetworkResult.Error(errorMessage = "Ошибка: ${response.code()} ${response.message()}")
        }
    } catch(e: Exception) {
        return NetworkResult.Error(errorMessage = "Ошибка: ${e.message}")
    }
}

