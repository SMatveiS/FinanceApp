package com.example.myfinance.ui.feature.presentation.categories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.category.GetAllCategoriesUseCase
import com.example.domain.usecase.category.GetCategoriesWithSubstringUseCase
import com.example.ui.ScreenState
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

                val categoriesResult =
                    if (searchText == "") getAllCategoriesUseCase()
                    else getCategoriesWithSubstringUseCase(searchText)

                categoriesResult.fold(
                    onSuccess = { categories ->
                        _state.update { it.copy(
                            categories = categories,
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
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = e.message,
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }

    fun onSearchTextChanged(newText: String) {
        _state.update { it.copy(searchText = newText) }

        getCategories()
    }
}