package com.example.model

data class CategoryStatisticsInfo(
    val categoryStatistics: List<CategoryStatistic>,
    val totalSum: Double,
    val currency: String
)