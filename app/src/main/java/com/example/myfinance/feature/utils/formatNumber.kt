package com.example.myfinance.feature.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun formatNumber(number: Int): String {
    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = ' '
    }
    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(number) + " ₽"
}