package com.example.ui

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

/**
 * Преобразовывает Double в строку формата ### ###,##
 *
 * Пример: 12130,99 -> "12 130,99"
 */

fun formatNumber(number: Double): String {
    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = ' '
        decimalSeparator = ','
    }
    val formatter = DecimalFormat("#,###.##", symbols)
    return formatter.format(number)
}
