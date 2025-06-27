package com.example.myfinance.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("userId")
    val userId: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("balance")
    val balance: String = "",
    @SerialName("currency")
    val currency: String = "",
    @SerialName("createdAt")
    val createdAt: String = "",
    @SerialName("updatedAt")
    val updatedAt: String = ""
)
