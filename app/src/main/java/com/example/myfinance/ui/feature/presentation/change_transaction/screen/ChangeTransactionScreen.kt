package com.example.myfinance.ui.feature.presentation.change_transaction.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinance.R
import com.example.myfinance.app.LocalAssistedChangeTransactionFactory
import com.example.myfinance.app.MainActivity
import com.example.myfinance.ui.common.AppTopBar
import com.example.myfinance.ui.common.DatePickerModal
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState
import com.example.myfinance.ui.common.OpenTimePicker
import com.example.myfinance.ui.common.uiDateFormat
import com.example.myfinance.ui.common.uiTimeFormat
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.account.screen.findActivity
import com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel.ChangeTransactionViewModel
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeTransactionScreen(
    transactionId: Int?,
    isIncome: Boolean,
    returnToPreviousScreen: () -> Unit,
) {

    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity

    val assistedFactory = remember {
        activity.activityComponent.assistedChangeTransactionFactory()
    }

    val viewModel: ChangeTransactionViewModel = remember(isIncome, transactionId) {
        assistedFactory.create(isIncome, transactionId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isDatePickerOpen) {
        DatePickerModal(
            selectedDate = state.date.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli(),
            onDateSelected = viewModel::onDateSelected,
            onDismiss = viewModel::onDatePickerDismiss
        )
    }

    if (state.isTimePickerOpen) {
        OpenTimePicker(
            initialHour = state.time.hour,
            initialMinute = state.time.minute,
            onConfirm = { timeState ->
                viewModel.onTimeSelected(timeState.hour, timeState.minute)
                viewModel.onTimePickerDismiss()
            },
            onDismiss = viewModel::onTimePickerDismiss
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (isIncome) "Мои доходы" else "Мои расходы",
                rightButtonIcon = R.drawable.confirm,
                rightButtonDescription = "Сохранить",
                rightButtonAction = {
                    viewModel.saveChanges()
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
                    account = state.accountName,
                    date = state.date.format(uiDateFormat),
                    time = state.time.format(uiTimeFormat),
                    categories = state.categories,
                    categoriesState = state.categoriesState,
                    getCategories = viewModel::getCategories,
                    selectCategory = viewModel::selectCategory,
                    onDatePickerOpen = viewModel::onDatePickerOpen,
                    onTimePickerOpen = viewModel::onTimePickerOpen,
                    onSumChanged = viewModel::updateSum,
                    onCommentChanged = viewModel::updateComment,
                    modifier = Modifier.padding(innerPadding),
                    categoriesErrorMessage = state.errorMessage
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage ?: "Неизвестная ошибка",
                    onRetry = viewModel::getInitialInformation,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }
    }
}