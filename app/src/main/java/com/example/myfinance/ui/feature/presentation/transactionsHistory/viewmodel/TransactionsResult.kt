package com.example.myfinance.ui.feature.presentation.transactionsHistory.viewmodel

import com.example.myfinance.domain.model.Transaction

/**
 * Хранит список транзакций и их сумму
 */

data class TransactionsResult(
    val transactions: List<Transaction>,
    val transactionsSum: Double,
    val currency: String
)