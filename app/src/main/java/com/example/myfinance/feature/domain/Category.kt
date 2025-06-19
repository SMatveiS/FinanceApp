package com.example.myfinance.feature.domain

data class Category(
    val id: Int,
    val category: String,
    val amount: Int,
    val emoji: String = "",
    val comment: String = "",
    val isIncome: Boolean = false
)