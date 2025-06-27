package com.example.myfinance.feature.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun formatNumber(number: Double): String {
    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = ' '
        decimalSeparator = ','
    }
    val formatter = DecimalFormat("#,###.##", symbols)
    return formatter.format(number) + " ₽"
}
