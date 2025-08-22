package com.example.myfinance.ui.feature.presentation.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.account.GetAccountUseCase
import com.example.domain.usecase.account.UpdateAccountUseCase
import com.example.domain.usecase.transaction.GetDailyAmounts
import com.example.ui.screenstate.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.MonthDay
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Хранит состояние экрана счёта
 */

class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val getDailyAmounts: GetDailyAmounts
) : ViewModel() {

    private val _state = MutableStateFlow<AccountState>(AccountState())
    val state = _state.asStateFlow()

    init {
        getAccount()
    }

    fun getAccount() {
        viewModelScope.launch {
            _state.update { it.copy(
                screenState = ScreenState.LOADING,
                errorMessage = null
            ) }

            try {
                val accountResult = getAccountUseCase()
                accountResult.fold (
                    onSuccess = { account ->

                        val currentDay = LocalDate.now()

                        val startDate = currentDay.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("y-MM-dd"))
                        val endDate = currentDay.withDayOfMonth(currentDay.lengthOfMonth()).format(DateTimeFormatter.ofPattern("y-MM-dd"))

                        val dailyAmountsResult = getDailyAmounts(startDate, endDate)

                        dailyAmountsResult.fold(
                            onSuccess = { dailyAmounts ->
                                _state.update { it.copy(
                                    account = account,
                                    dailyAmounts = dailyAmounts,
                                    screenState = ScreenState.SUCCESS
                                ) }
                            },

                            onFailure = { error ->
                                _state.update {
                                    it.copy(
                                        errorMessage = error.message,
                                        screenState = ScreenState.ERROR
                                    )
                                }
                            }
                        )
                    },

                    onFailure = { error ->
                        _state.update {
                            it.copy(
                                errorMessage = error.message,
                                screenState = ScreenState.ERROR
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = e.message,
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }

    fun updateName(name: String) {
        _state.update { it.copy(
            account = it.account?.copy(name = name)
        ) }
    }

    fun updateBalance(balance: Double) {
        _state.update { it.copy(
            account = it.account?.copy(balance = balance)
        ) }
    }

    fun updateCurrency(currency: String) {
        _state.update { it.copy(
            account = it.account?.copy(currency = currency)
        ) }
    }

    fun updateAccount() {
        viewModelScope.launch {
            val account = state.value.account ?: return@launch

            try {
                updateAccountUseCase(account)
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Save error: ${e.message}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }
}
