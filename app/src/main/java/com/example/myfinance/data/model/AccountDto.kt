package com.example.myfinance.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("userId")
    val userId: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("balance")
    val balance: String? = null,
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("createdAt")
    val createdAt: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null
    )