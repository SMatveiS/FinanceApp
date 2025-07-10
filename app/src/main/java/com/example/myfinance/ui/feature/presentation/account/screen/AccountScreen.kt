package com.example.myfinance.ui.feature.presentation.account.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinance.R
import com.example.myfinance.app.LocalViewModelFactory
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.account.viewmodel.AccountViewModel
import com.example.myfinance.ui.common.AppTopBar
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState

@Composable
fun AccountScreen(
    onEditAccountClicked: () -> Unit
) {

    val viewModel: AccountViewModel = viewModel(factory = LocalViewModelFactory.current)

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Мой счет",
                rightButtonIcon = R.drawable.edit,
                rightButtonDescription = "Изменить",
                rightButtonAction = onEditAccountClicked
            )
        },

        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        when (state.screenState) {
            ScreenState.SUCCESS -> {
                AccountContent(
                    account = state.account,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage ?: "Неизвестная ошибка",
                    onRetry = viewModel::getAccount,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }
    }
}
