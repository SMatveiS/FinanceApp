package com.example.myfinance.feature.domain

data class Account(
    val id: Int,
    val balance: Int,
    val currency: String = "₽"
)
