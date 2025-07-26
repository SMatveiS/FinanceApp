package com.example.data.model

import com.example.model.Account
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AccountRequestDto(
    @SerialName("name")
    val name: String = "",
    @SerialName("balance")
    val balance: String = "",
    @SerialName("currency")
    val currency: String = ""
)

fun Account.toDto() = AccountRequestDto(
    name = name,
    balance = balance.toString(),
    currency = currency
)