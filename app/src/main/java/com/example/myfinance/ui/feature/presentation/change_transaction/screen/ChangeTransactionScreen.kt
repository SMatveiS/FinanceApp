package com.example.myfinance.ui.feature.presentation.change_transaction.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.R
import com.example.myfinance.ui.common.AppTopBar
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel.ChangeTransactionViewModel

@Composable
fun ChangeTransactionScreen(
    viewModel: ChangeTransactionViewModel = hiltViewModel(),
    returnToPreviousScreen: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Мои расходы/доходы",
                rightButtonIcon = R.drawable.confirm,
                rightButtonDescription = "Сохранить",
                rightButtonAction = {
                    // TODO: add or update transaction
                    returnToPreviousScreen()
                },
                leftButtonIcon = R.drawable.cancel,
                leftButtonDescription = "Отменить",
                leftButtonAction = returnToPreviousScreen
            )
        },

        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        when (state.screenState) {
            ScreenState.SUCCESS -> {
                ChangeTransactionContent(
                    transaction = state.transaction,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage ?: "Неизвестная ошибка",
                    onRetry = viewModel::getTransaction,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }
    }
}