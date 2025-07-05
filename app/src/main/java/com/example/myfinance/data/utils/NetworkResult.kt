package com.example.myfinance.data.utils

import com.example.myfinance.data.utils.NetworkResult.Error
import com.example.myfinance.data.utils.NetworkResult.Success

/**
 * Класс для возвращения результата похода в сеть
 */

sealed class NetworkResult<T>() {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val errorMessage: String?) : NetworkResult<T>()
}

/**
 * extension-функция для NetworkResult, которая в случае ошибки прокидывает её дальше,
 * а иначе позволяет преобразовать данные
 */

suspend fun <T, R> NetworkResult<T>.map(action: suspend (T) -> R): NetworkResult<R> {
    return when (this) {
        is Success -> {
            Success(action(this.data))
        }

        is Error -> Error(this.errorMessage)
    }
}
