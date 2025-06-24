package com.example.myfinance.feature.presentation.transactionsHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.data.api.account.AccountApi
import com.example.myfinance.data.api.transaction.TransactionApi
import com.example.myfinance.data.network.OkHttpClient
import com.example.myfinance.data.network.RetrofitClient
import com.example.myfinance.feature.domain.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

enum class DialogType {
    START_DATE, END_DATE
}

enum class ScreenState {
    SUCCESS, ERROR, LOADING
}

data class TransactionsState(
    val transactions: List<Transaction> = emptyList(),
    val startDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    val endDate: LocalDate = LocalDate.now(),
    val totalSum: Double = 0.0,
    val screenState: ScreenState = ScreenState.LOADING,
    val dialogType: DialogType? = null,
    val error: String? = null
)

class TransactionsHistoryViewModel: ViewModel() {
    private val _state = MutableStateFlow(TransactionsState())
    val state: StateFlow<TransactionsState> = _state

    init {
        getTransactions()
    }

    fun onStartDatePickerOpen() = openDialog(DialogType.START_DATE)

    fun onEndDatePickerOpen() = openDialog(DialogType.END_DATE)

    private fun openDialog(type: DialogType) {
        _state.update { it.copy(dialogType = type) }
    }

    fun onDatePickerDismiss() {
        _state.update { it. copy(dialogType = null) }
    }

    fun onDateSelected(dateInMillis: Long?) {
        if (dateInMillis != null) {
            val newDate = Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.of("UTC")).toLocalDate()

            _state.update { state ->
                val updated = when (_state.value.dialogType) {
                    DialogType.START_DATE ->
                        if (newDate.isAfter(state.endDate)) state
                        else state.copy(startDate = newDate)

                    DialogType.END_DATE ->
                        if (newDate.isBefore(state.startDate)) state
                        else state.copy(endDate = newDate)

                    null -> state
                }
                updated.copy(dialogType = null)
            }

            getTransactions()
        }
    }

    fun getTransactions() {
        viewModelScope.launch {
            _state.update { it.copy(screenState = ScreenState.LOADING) }

            try {
                val okHttpClient = OkHttpClient().getClient()
                val retrofit = RetrofitClient().getClient(okHttpClient)
                val accountApi: AccountApi = retrofit.create(AccountApi::class.java)

                val accountResponse = accountApi.getAllAccounts()

                if (!accountResponse.isSuccessful || accountResponse.body().isNullOrEmpty()) {
                    _state.update {
                        it.copy(
                            error = "Нет доступных аккаунтов",
                            screenState = ScreenState.ERROR
                        ) }
                    return@launch
                }

                val accountId = accountResponse.body()!!.first().id

                val startDate = state.value.startDate.format(DateTimeFormatter.ofPattern("y-MM-dd"))
                val endDate = state.value.endDate.format(DateTimeFormatter.ofPattern("y-MM-dd"))

                val transactionApi: TransactionApi = retrofit.create(TransactionApi::class.java)
                val transactionResponse = transactionApi.getTransactionsForPeriod(
                    accountId = accountId!!,
                    startDate = startDate,
                    endDate = endDate
                )

                if (transactionResponse.isSuccessful) {
                    val transactions = transactionResponse.body()?.filter { it.category!!.isIncome == true }?.map {
                        Transaction(
                            id = it.id ?: 0,
                            category = it.category?.name ?: "",
                            amount = it.amount?.toDouble() ?: 0.0,
                            date = OffsetDateTime.parse(it.transactionDate)
                                .format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"))
                        )
                    }?.sortedByDescending { it.date } ?: emptyList()
                    _state.update { it.copy(transactions, screenState = ScreenState.SUCCESS) }
                } else {
                    _state.update { it.copy(error = "Ошибка транзакций: ${transactionResponse.code()}", screenState = ScreenState.ERROR) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}", screenState = ScreenState.ERROR) }
            }
        }
    }
}