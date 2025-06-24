package com.example.myfinance.feature.domain.model


data class Transaction(
    val id: Int,
    val category: String,
    val amount: Double,
    val date: String,
    val emoji: String = "",
    val comment: String = ""
)