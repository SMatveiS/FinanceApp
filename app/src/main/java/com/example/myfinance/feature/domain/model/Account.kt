package com.example.myfinance.feature.domain.model

/**
 * Доменная модель аккаунта
 */

data class Account(
    val id: Int,
    val name: String,
    val balance: Double,
    val currency: String
)
