package com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.account.GetAccountUseCase
import com.example.domain.usecase.category.GetCategoriesByType
import com.example.domain.usecase.transaction.AddTransactionUseCase
import com.example.domain.usecase.transaction.GetTransactionByIdUseCase
import com.example.domain.usecase.transaction.UpdateTransactionUseCase
import com.example.model.Category
import com.example.ui.uiDateTimeFormat
import com.example.myfinance.ui.feature.presentation.ScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class ChangeTransactionViewModel @AssistedInject constructor(
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val getCategoriesByType: GetCategoriesByType,
    private val getAccountUseCase: GetAccountUseCase,
    @Assisted private val isIncome: Boolean,
    @Assisted private val transactionId: Int?,

): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(isIncome: Boolean, transactionId: Int?): ChangeTransactionViewModel
    }

    private val _state = MutableStateFlow(ChangeTransactionState())
    val state = _state.asStateFlow()

    private fun getTransactionDate(date: LocalDate, time: LocalTime) =
        LocalDateTime.of(date, time).format(uiDateTimeFormat)

    init {
        getInitialInformation()
    }

    fun getInitialInformation() {

        if (!isIncome) {
            _state.update {
                it.copy(
                    transaction = it.transaction.copy(
                        category = Category(
                            id = 8,
                            name = "Продукты",
                            emoji = "\uD83C\uDF4E",
                            isIncome = false
                        )
                    )
                )
            }
        }

        viewModelScope.launch {

            _state.update {
                it.copy(
                    screenState = ScreenState.LOADING,
                    errorMessage = null
                )
            }

            try {
                val accountResult = getAccountUseCase()

                 accountResult.fold (

                    onSuccess =  { account ->
                        _state.update {
                            it.copy(
                                transaction = it.transaction.copy(accountId = account.id),
                                accountName = account.name,
                                currency = account.currency
                            )
                        }
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
                _state.update {
                    it.copy(
                        errorMessage = e.message,
                        screenState = ScreenState.ERROR
                    )
                }
            }
        }

        if (transactionId == null)
            _state.update { it.copy(screenState = ScreenState.SUCCESS) }
        else getTransaction()
    }

    private fun getTransaction() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    screenState = ScreenState.LOADING,
                    errorMessage = null
                )
            }

            try {
                val transactionResult = getTransactionByIdUseCase(id = transactionId!!)

                transactionResult.fold(
                    onSuccess = { transaction ->

                        val dateTime = uiDateTimeFormat.parse(transaction.date)

                        _state.update {
                            it.copy(
                                transaction = transaction,
                                date = LocalDate.from(dateTime),
                                time = LocalTime.from(dateTime),
                                screenState = ScreenState.SUCCESS
                            )
                        }
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
                _state.update {
                    it.copy(
                        errorMessage = e.message,
                        screenState = ScreenState.ERROR
                    )
                }
            }
        }
    }

    fun saveChanges() {
        if (transactionId == null) addTransaction()
        else updateTransaction()
    }

    private fun addTransaction() {
        viewModelScope.launch {
            val transaction = state.value.transaction.copy(
                date = getTransactionDate(state.value.date, state.value.time)
            )

            try {
                addTransactionUseCase(transaction)
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Save error: ${e.message}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }

    private fun updateTransaction() {
        viewModelScope.launch {
            val transaction = state.value.transaction.copy(
                date = getTransactionDate(state.value.date, state.value.time)
            )

            try {
                updateTransactionUseCase(transaction)
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Save error: ${e.message}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }

    fun onDatePickerOpen() = _state.update { it.copy(isDatePickerOpen = true) }

    fun onDatePickerDismiss() = _state.update { it.copy(isDatePickerOpen = false) }

    fun onDateSelected(dateInMillis: Long?) {
        if (dateInMillis != null) {
            var newDate = Instant.ofEpochMilli(dateInMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            if (newDate.isAfter(LocalDate.now()))
                newDate = LocalDate.now()

            _state.update {
                it.copy( date = newDate )
            }
        }
    }

    fun onTimePickerOpen() = _state.update { it.copy(isTimePickerOpen = true) }

    fun onTimePickerDismiss() = _state.update { it.copy(isTimePickerOpen = false) }

    fun onTimeSelected(hour: Int, minute: Int) {
        _state.update { state ->
            val newTime = LocalTime.of(hour, minute)
            state.copy(time = newTime)
        }
    }

    fun updateSum(sum: Double) {
        _state.update { it.copy(
            transaction = it.transaction.copy(amount = sum)
        ) }
    }

    fun updateComment(comment: String) {
        _state.update { it.copy(
            transaction = it.transaction.copy(comment = if (comment.trim() == "") null else comment)
        ) }
    }

    fun getCategories() {
        viewModelScope.launch {
            _state.update { it.copy(
                categoriesState = ScreenState.LOADING,
                categoriesErrorMessage = null
            ) }

            try {
                val categoriesResult = getCategoriesByType(isIncome)
                categoriesResult.fold(
                    onSuccess = { categories ->
                        _state.update { it.copy(
                            categories = categories,
                            categoriesState = ScreenState.SUCCESS
                        ) }
                    },

                    onFailure = { error ->
                        _state.update { it.copy(
                            categoriesState = ScreenState.ERROR,
                            categoriesErrorMessage = error.message
                        ) }
                    }
                )
            } catch (e: Exception) {
                _state.update { it.copy(
                    categoriesState = ScreenState.ERROR,
                    categoriesErrorMessage = e.message
                ) }
            }
        }
    }

    fun selectCategory(category: Category) {
        _state.update {
            it.copy( transaction = it.transaction.copy(category = category) )
        }
    }
}