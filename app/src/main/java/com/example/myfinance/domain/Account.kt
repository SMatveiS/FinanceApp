package com.example.myfinance.domain

data class Account(
    val id: Int,
    val balance: Int,
    val currency: String = "₽"
)
