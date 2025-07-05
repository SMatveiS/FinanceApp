package com.example.myfinance.ui.common

/**
 * Возвращает соответствующий названию символ валюты, либо входную строку, если нет соответствий
 */

fun getCurrencySymbol(currencyCode: String): String {
    return when (currencyCode.uppercase()) {
        "RUB" -> "₽"
        "USD" -> "$"
        "EUR" -> "€"
        else -> currencyCode
    }
}