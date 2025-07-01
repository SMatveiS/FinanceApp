package com.example.myfinance.data.utils

sealed class NetworkResult<T>() {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error<T>(val errorMessage: String?): NetworkResult<T>()
}
