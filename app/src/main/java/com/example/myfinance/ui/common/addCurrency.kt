package com.example.myfinance.ui.common

fun String.addCurrency(currency: String): String {
    return "$this ${getCurrencySymbol(currency)}"
}