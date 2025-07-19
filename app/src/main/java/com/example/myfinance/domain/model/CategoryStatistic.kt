package com.example.myfinance.domain.model

data class CategoryStatistic(
    val category: Category,
    val amount: Double,
    val proportion: Double
)