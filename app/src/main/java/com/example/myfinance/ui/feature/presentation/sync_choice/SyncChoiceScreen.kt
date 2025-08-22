package com.example.myfinance.ui.feature.presentation.sync_choice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myfinance.R
import com.example.myfinance.app.MainActivity
import com.example.myfinance.app.findActivity
import com.example.ui.AppTopBar

@Composable
fun SyncChoiceScreen(
    returnToPreviousScreen: () -> Unit
) {

    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity
    val screenComponent = remember {
        activity.activityComponent.screenComponentFactory().create()
    }

    val viewModel = screenComponent.syncChoiceViewModel
    val syncFreq by viewModel.syncFreq.collectAsState()

    var newSyncFreq by remember(syncFreq) { mutableIntStateOf(syncFreq) }

    val frequencyText = when (newSyncFreq) {
        1 -> "каждый час"
        in 2..4 -> "каждые $newSyncFreq часа"
        else -> "каждые $newSyncFreq часов"
    }

    Scaffold (
        topBar = { AppTopBar(
            title = "Синхронизация",
            rightButtonIcon = R.drawable.confirm,
            rightButtonDescription = "Сохранить",
            rightButtonAction = {
                viewModel.setSyncFrequency(newSyncFreq)
                returnToPreviousScreen()
            },
            leftButtonIcon = R.drawable.back_arrow,
            leftButtonDescription = "Назад",
            leftButtonAction = returnToPreviousScreen
        ) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Text(
                text = "Данные будут обновляться $frequencyText",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(24.dp)
            )

            Slider(
                value = newSyncFreq.toFloat(),
                onValueChange = { newValue ->
                    newSyncFreq = newValue.toInt()
                },
                valueRange = 1f..24f,
                steps = 23,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}