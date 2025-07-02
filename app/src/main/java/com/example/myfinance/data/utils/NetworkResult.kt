package com.example.myfinance.data.utils

import com.example.myfinance.data.utils.NetworkResult.Error
import com.example.myfinance.data.utils.NetworkResult.Success

sealed class NetworkResult<T>() {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val errorMessage: String?) : NetworkResult<T>()
}


suspend fun <T, R> NetworkResult<T>.map(action: suspend (T) -> R): NetworkResult<R> {
    return when (this) {
        is Success -> {
            Success(action(this.data))
        }

        is Error -> Error(this.errorMessage)
    }
}
