package com.example.myfinance.feature.presentation.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.data.api.category.CategoryApi
import com.example.myfinance.data.network.OkHttpClient
import com.example.myfinance.data.network.RetrofitClient
import com.example.myfinance.feature.domain.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.myfinance.data.api.account.AccountApi
import com.example.myfinance.data.api.transaction.TransactionApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ExpenseViewModel : ViewModel() {
    private val _state = MutableStateFlow<ExpensesState>(ExpensesState.Loading)
    val state: StateFlow<ExpensesState> = _state

    init {
        getExpenses()
    }

    fun getExpenses() {
        viewModelScope.launch {
            _state.value = ExpensesState.Loading

            try {
                val okHttpClient = OkHttpClient().getClient()
                val retrofit = RetrofitClient().getClient(okHttpClient)

                val accountApi: AccountApi = retrofit.create(AccountApi::class.java)
                val accountResponse = accountApi.getAllAccounts()

                if (!accountResponse.isSuccessful || accountResponse.body().isNullOrEmpty()) {
                    _state.value = ExpensesState.Error("Нет доступных аккаунтов")
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

                if (!transactionResponse.isSuccessful) {
                    _state.value = ExpensesState.Error(
                        "Ошибка загрузки транзакций: ${transactionResponse.code()}"
                    )
                    return@launch
                }

                val categoryApi: CategoryApi = retrofit.create(CategoryApi::class.java)
                val categoryResponse = categoryApi.getAllCategories()

                if (!categoryResponse.isSuccessful) {
                    _state.value = ExpensesState.Error(
                        "Ошибка загрузки категорий: ${categoryResponse.code()}"
                    )
                    return@launch
                }

                val expenseCategories = categoryResponse.body()?.filter { it.isIncome == false }
                    ?.associateBy { it.id } ?: emptyMap()

                val transactions = transactionResponse.body() ?: emptyList()

                val expensesByCategory = transactions
                    .filter { transaction ->
                        expenseCategories[transaction.category?.id] != null
                    }
                    .groupBy { it.category!!.id }
                    .mapValues { (_, transactions) ->
                        transactions.sumOf { it.amount!!.toDouble() }
                    }
                    .map { (categoryId, amount) ->
                        val category = expenseCategories[categoryId]!!
                        Category(
                            id = category.id ?: 0,
                            category = category.name ?: "Без названия",
                            emoji = category.emoji ?: "",
                            amount = amount
                        )
                    }

                _state.value = ExpensesState.Success(expensesByCategory)

            } catch (e: Exception) {
                _state.value = ExpensesState.Error(
                    "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}"
                )
            }
        }
    }
}

sealed class ExpensesState {
    object Loading : ExpensesState()
    data class Success(val expenses: List<Category>) : ExpensesState()
    data class Error(val message: String) : ExpensesState()
}