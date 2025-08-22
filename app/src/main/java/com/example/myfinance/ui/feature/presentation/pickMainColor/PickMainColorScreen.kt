package com.example.myfinance.ui.feature.presentation.pickMainColor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.app.MainActivity
import com.example.myfinance.app.findActivity
import com.example.ui.AppListItem
import com.example.ui.AppTopBar
import com.example.ui.theme.Blue
import com.example.ui.theme.Green
import com.example.ui.theme.LightPurple

@Composable
fun PickMainColorScreen(
    returnToPreviousScreen: () -> Unit
) {

    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity
    val screenComponent = remember {
        activity.activityComponent.screenComponentFactory().create()
    }

    val viewModel = screenComponent.pickMainColorViewModel

    Scaffold (
        topBar = { AppTopBar(
            title = "Основной цвет",
            leftButtonIcon = R.drawable.back_arrow,
            leftButtonDescription = "Назад",
            leftButtonAction = returnToPreviousScreen
        ) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            AppListItem(
                leftTitle = "Зелёный",
                rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
                itemBackground = Green,
                clickable = true,
                onClick = viewModel::setGreenMainColor
            )
            HorizontalDivider()

            AppListItem(
                leftTitle = "Голубой",
                rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
                itemBackground = Blue,
                clickable = true,
                onClick = viewModel::setBlueMainColor
            )
            HorizontalDivider()

            AppListItem(
                leftTitle = "Фиолетовый",
                rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
                itemBackground = LightPurple,
                clickable = true,
                onClick = viewModel::setPurpleMainColor
            )
            HorizontalDivider()
        }
    }
}