package com.example.myfinance.feature.utils

import android.os.Message

class NetworkError(
    val code: Int? = null,
    override val message: String? = null
): Error(message)