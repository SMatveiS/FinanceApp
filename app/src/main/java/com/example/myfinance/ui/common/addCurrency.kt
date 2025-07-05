package com.example.myfinance.ui.common

/**
 * Добавляет в конец строки символ валюты
 */

fun String.addCurrency(currency: String): String {
    return "$this ${getCurrencySymbol(currency)}"
}