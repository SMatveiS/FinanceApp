package com.example.myfinance

data class Expense(
    val id: Int,
    val category: String,
    val amount: Int,
    val emoji: String,
    val comment: String = ""
)