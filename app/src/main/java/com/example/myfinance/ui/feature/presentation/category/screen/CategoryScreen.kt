package com.example.myfinance.ui.feature.presentation.category.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinance.app.LocalViewModelFactory
import com.example.myfinance.ui.common.AppTopBar
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.category.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen() {

    val viewModel: CategoryViewModel = viewModel(factory = LocalViewModelFactory.current)

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { AppTopBar(title = "Мои статьи") },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            SearchField(
                searchText = state.searchText,
                onSearchTextChanged = viewModel::onSearchTextChanged
            )

            HorizontalDivider()

            when (state.screenState) {
                ScreenState.SUCCESS -> {
                    CategoryContent(
                        categories = state.categories
                    )
                }

                ScreenState.ERROR -> {
                    ErrorState(
                        message = state.errorMessage ?: "Неизвестная ошибка",
                        onRetry = viewModel::getCategories,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
