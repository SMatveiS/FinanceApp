package com.example.myfinance

data class Account(
    val id: Int,
    val balance: Int,
    val currency: String = "₽"
)
