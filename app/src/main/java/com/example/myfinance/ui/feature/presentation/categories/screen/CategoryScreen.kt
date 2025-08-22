package com.example.myfinance.ui.feature.presentation.categories.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.app.MainActivity
import com.example.myfinance.app.findActivity
import com.example.ui.AppTopBar
import com.example.ui.screenstate.ErrorState
import com.example.ui.screenstate.LoadingState
import com.example.ui.screenstate.ScreenState

@Composable
fun CategoryScreen() {

    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity
    val screenComponent = remember {
        activity.activityComponent.screenComponentFactory().create()
    }

    val viewModel = screenComponent.categoryViewModel

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
                        message = state.errorMessage,
                        onRetry = viewModel::getCategories,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
