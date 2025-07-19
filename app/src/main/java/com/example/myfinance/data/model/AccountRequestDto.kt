package com.example.myfinance.data.model

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