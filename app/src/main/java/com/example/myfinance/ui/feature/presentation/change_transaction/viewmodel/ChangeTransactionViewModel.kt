package com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.domain.usecase.transaction.AddTransactionUseCase
import com.example.myfinance.domain.usecase.transaction.GetTransactionByIdUseCase
import com.example.myfinance.domain.usecase.transaction.UpdateTransactionUseCase
import com.example.myfinance.ui.feature.presentation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeTransactionViewModel @Inject constructor(
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(ChangeTransactionState())
    val state = _state.asStateFlow()

    val id = 9

    init {
        getTransaction()
    }

    fun getTransaction() {
        viewModelScope.launch {
            _state.update { it.copy(
                screenState = ScreenState.LOADING,
                errorMessage = null
            ) }

            try {
                val transactionResult = getTransactionByIdUseCase(id = id)

                when (transactionResult) {
                    is NetworkResult.Success -> {

                        _state.update { it.copy(
                            transaction = transactionResult.data,
                            screenState = ScreenState.SUCCESS
                        ) }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = transactionResult.errorMessage,
                                screenState = ScreenState.ERROR
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }

    fun addTransaction() {
        viewModelScope.launch {
            val transaction = state.value.transaction ?: return@launch

            try {
                addTransactionUseCase(transaction)
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Ошибка сохранения: ${e.localizedMessage}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }

    fun updateTransaction() {
        viewModelScope.launch {
            val transaction = state.value.transaction ?: return@launch

            try {
                updateTransactionUseCase(transaction)
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Ошибка сохранения: ${e.localizedMessage}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }
}