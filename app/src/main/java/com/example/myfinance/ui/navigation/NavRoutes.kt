package com.example.myfinance.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Хранит маршруты для навигации
 */

sealed class NavRoutes {
    @Serializable object Expenses
    @Serializable object Incomes
    @Serializable object Account
    @Serializable object Settings


    @Serializable object Transactions
    @Serializable object AccountStatistic
    @Serializable object EditAccount
    @Serializable object Categories
    @Serializable object TransactionsHistory
    @Serializable object Analysis
    @Serializable object MainSettings
    @Serializable object PickMainColor

    @Serializable
    data class ChangeTransaction(val transactionId: Int? = null) : NavRoutes()
}


