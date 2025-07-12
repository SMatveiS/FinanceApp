package com.example.myfinance.ui.common.navbar

/**
 * Элемент навигационной панели
 */

data class NavBarItem<T: Any>(
    val title: String,
    val icon: Int,
    val route: T
)