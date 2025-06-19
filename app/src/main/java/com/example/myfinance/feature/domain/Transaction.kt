package com.example.myfinance.feature.domain

data class Transaction(
    val id: Int,
    val category: String,
    val amount: Int,
    val date: String,
    val emoji: String = "",
    val comment: String = ""
)