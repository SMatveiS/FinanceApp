package com.example.myfinance.ui.navigation

/**
 * Хранит маршруты для навигации
 */

sealed class NavRoutes(val route: String) {
    object Expenses : NavRoutes("expenses")
    object Incomes : NavRoutes("incomes")
    object Account : NavRoutes("account")
    object EditAccount : NavRoutes("editAccount")
    object Articles : NavRoutes("articles")
    object Settings : NavRoutes("settings")
    object TransactionsHistory : NavRoutes("history")
}
