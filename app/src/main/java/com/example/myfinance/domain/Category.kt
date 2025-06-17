package com.example.myfinance.domain

data class Category(
    val id: Int,
    val category: String,
    val amount: Int,
    val emoji: String = "",
    val comment: String = "",
    val isIncome: Boolean = false
)