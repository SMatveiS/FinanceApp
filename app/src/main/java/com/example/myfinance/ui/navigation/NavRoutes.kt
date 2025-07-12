package com.example.myfinance.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Хранит маршруты для навигации
 */

sealed class NavRoutes {
    @Serializable object Expenses
    @Serializable object Incomes
    @Serializable object Account


    @Serializable object Transactions
    @Serializable object AccountStatistic
    @Serializable object EditAccount
    @Serializable object Categories
    @Serializable object Settings
    @Serializable object TransactionsHistory

    @Serializable
    data class ChangeTransaction(val transactionId: Int? = null) : NavRoutes()
}


