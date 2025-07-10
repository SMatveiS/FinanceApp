package com.example.myfinance.ui.feature.presentation.category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.domain.usecase.category.GetAllCategoriesUseCase
import com.example.myfinance.domain.usecase.category.GetCategoriesWithSubstringUseCase
import com.example.myfinance.ui.feature.presentation.ScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Хранит состояние экрана статей
 */

class CategoryViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getCategoriesWithSubstringUseCase: GetCategoriesWithSubstringUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CategoryState>(CategoryState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        getCategories()
    }

    fun getCategories() {

        searchJob?.cancel()
        searchJob = viewModelScope.launch {

            delay(300)

            _state.update { it.copy(
                screenState = ScreenState.LOADING,
                errorMessage = null
            ) }

            try {
                val searchText = state.value.searchText.trim()

                val categories =
                    if (searchText == "") getAllCategoriesUseCase()
                    else getCategoriesWithSubstringUseCase(searchText)

                when (categories) {
                    is NetworkResult.Success -> {
                        _state.update { it.copy(
                            categories = categories.data,
                            screenState = ScreenState.SUCCESS
                        ) }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = categories.errorMessage,
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

    fun onSearchTextChanged(newText: String) {
        _state.update { it.copy(searchText = newText) }

        getCategories()
    }

//    fun getCategoriesWithSubstring() {
//        if (state.value.searchText.trim() == "") {
//            getCategories()
//        } else {
//            viewModelScope.launch {
//                _state.update { it.copy(
//                    screenState = ScreenState.LOADING,
//                    errorMessage = null
//                ) }
//
//                try {
//                    val categories = getCategoriesWithSubstringUseCase(state.value.searchText)
//                    when (categories) {
//                        is NetworkResult.Success -> {
//                            _state.update { it.copy(
//                                categories = categories.data,
//                                screenState = ScreenState.SUCCESS
//                            ) }
//                        }
//
//                        is NetworkResult.Error -> {
//                            _state.update {
//                                it.copy(
//                                    errorMessage = categories.errorMessage,
//                                    screenState = ScreenState.ERROR
//                                )
//                            }
//                        }
//                    }
//                } catch (e: Exception) {
//                    _state.update { it.copy(
//                        errorMessage = "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}",
//                        screenState = ScreenState.ERROR
//                    ) }
//                }
//            }
//        }
//    }
}