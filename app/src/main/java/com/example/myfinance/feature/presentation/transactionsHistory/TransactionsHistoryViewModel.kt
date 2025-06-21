package com.example.myfinance.feature.presentation.transactionsHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.data.api.account.AccountApi
import com.example.myfinance.data.api.category.CategoryApi
import com.example.myfinance.data.api.transaction.TransactionApi
import com.example.myfinance.data.network.OkHttpClient
import com.example.myfinance.data.network.RetrofitClient
import com.example.myfinance.feature.domain.model.Category
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.presentation.expenses.ExpensesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TransactionsHistoryViewModel: ViewModel() {
    private val _state = MutableStateFlow<TransactionsState>(TransactionsState.Loading)
    val state: StateFlow<TransactionsState> = _state

    init {
        getTransactions()
    }

    fun getTransactions() {
        viewModelScope.launch {
            _state.value = TransactionsState.Loading

            try {
                val okHttpClient = OkHttpClient().getClient()
                val retrofit = RetrofitClient().getClient(okHttpClient)
                val accountApi: AccountApi = retrofit.create(AccountApi::class.java)

                val accountResponse = accountApi.getAllAccounts()

                if (!accountResponse.isSuccessful || accountResponse.body().isNullOrEmpty()) {
                    _state.value = TransactionsState.Error("Нет доступных аккаунтов")
                    return@launch
                }

                val accountId = accountResponse.body()!!.first().id

                val today = LocalDate.now()
                val firstDayOfMonth = today.withDayOfMonth(1)
                val dateFormatter = DateTimeFormatter.ISO_DATE

                val startDate = firstDayOfMonth.format(dateFormatter)
                val endDate = today.format(dateFormatter)

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
                            amount = it.amount ?: 0,
                            date = it.transactionDate ?: endDate
                        )
                    }?.sortedByDescending { it.date } ?: emptyList()
                    _state.value = TransactionsState.Success(transactions)
                } else {
                    _state.value = TransactionsState.Error(
                        "Ошибка транзакций: ${transactionResponse.code()}"
                    )
                }
            } catch (e: Exception) {
                _state.value = TransactionsState.Error(
                    "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}"
                )
            }
        }
    }
}

sealed class TransactionsState {
    object Loading : TransactionsState()
    data class Success(val transactions: List<Transaction>) : TransactionsState()
    data class Error(val message: String) : TransactionsState()
}