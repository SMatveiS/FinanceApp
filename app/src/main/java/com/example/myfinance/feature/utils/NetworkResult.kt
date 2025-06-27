package com.example.myfinance.feature.utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T?): NetworkResult<T>(data)
    class Error<T>(errorMessage: String?): NetworkResult<T>(errorMessage = errorMessage)
    class Loading<T>(): NetworkResult<T>()
}