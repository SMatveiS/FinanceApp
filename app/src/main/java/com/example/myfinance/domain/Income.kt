package com.example.myfinance.domain

data class Income(
    val id: Int,
    val category: String,
    val amount: Int,
    val comment: String = ""
)
