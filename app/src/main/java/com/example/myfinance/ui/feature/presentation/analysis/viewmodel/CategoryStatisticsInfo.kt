package com.example.myfinance.ui.feature.presentation.analysis.viewmodel

import com.example.myfinance.domain.model.CategoryStatistic

data class CategoryStatisticsInfo(
    val categoryStatistics: List<CategoryStatistic>,
    val totalSum: Double,
    val currency: String
)