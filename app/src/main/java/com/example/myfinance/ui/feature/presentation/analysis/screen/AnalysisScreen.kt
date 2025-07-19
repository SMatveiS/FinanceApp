package com.example.myfinance.ui.feature.presentation.analysis.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.R
import com.example.myfinance.app.MainActivity
import com.example.myfinance.ui.common.AppTopBar
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.account.screen.findActivity
import com.example.myfinance.ui.feature.presentation.analysis.viewmodel.AnalysisViewModel
import com.example.myfinance.ui.common.datepicker.OpenDatePicker

@Composable
fun AnalysisScreen(
    isIncome: Boolean,
    onBackArrowClicked: () -> Unit
) {
    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity

    val assistedFactory = remember {
        activity.activityComponent.assistedAnalysisFactory()
    }

    val viewModel: AnalysisViewModel = remember(isIncome) {
        assistedFactory.create(isIncome)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    state.datePickerDialogType?.let {
        OpenDatePicker(
            dialogType = it,
            startDate = state.startDate,
            endDate = state.endDate,
            onDateSelected = viewModel::onDateSelected,
            onDatePickerDismiss = viewModel::onDatePickerDismiss
        )
    }

    Scaffold (
        topBar = {
            AppTopBar(
                title = "Анализ",
                backgroundColor = MaterialTheme.colorScheme.background,
                leftButtonIcon = R.drawable.back_arrow,
                leftButtonDescription = "Назад",
                leftButtonAction = onBackArrowClicked
            ) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        when (state.screenState) {
            ScreenState.SUCCESS -> {
                AnalysisContent(
                    categoryStatistics = state.categoriesStatistic,
                    currency = state.currency,
                    totalSum = state.totalSum,
                    startDate = state.startDate,
                    endDate = state.endDate,
                    onStartDatePickerOpen = viewModel::onStartDatePickerOpen,
                    onEndDatePickerOpen = viewModel::onEndDatePickerOpen,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage,
                    onRetry = viewModel::getStatistic,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }

    }
}