package com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel

import com.example.model.Category
import com.example.model.Transaction
import com.example.ui.uiDateFormat
import com.example.ui.screenstate.ScreenState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class ChangeTransactionState(
    val transaction: Transaction = Transaction(
        id = 0,
        accountId = 0,
        category = Category(
            id = 1,
            name = "Зарплата",
            emoji = "\uD83D\uDCB0",
            isIncome = true
        ),
        amount = 0.0,
        date = LocalDateTime.now().format(uiDateFormat),
        comment = null
    ),
    val currency: String = "RUB",
    val accountName: String = "Сбербанк",
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val isDatePickerOpen: Boolean = false,
    val isTimePickerOpen: Boolean = false,

    val categories: List<Category> = emptyList(),
    val categoriesState: ScreenState = ScreenState.LOADING,
    val categoriesErrorMessage: String? = null,

    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)