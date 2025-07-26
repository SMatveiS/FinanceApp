package com.example.model

import kotlinx.serialization.Serializable

/**
 * Доменная модель аккаунта
 */

@Serializable
data class Account(
    val id: Int,
    val name: String,
    val balance: Double,
    val currency: String,
    val createdAt: String
)
