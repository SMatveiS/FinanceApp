package com.example.myfinance.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Верхняя панель
 *
 * title - текст по центру
 *
 * rightButtonIcon - иконка правой кнопки
 *
 *  leftButtonIcon - иконка левой кнопки
 *
 *  rightButtonDescription - описание правой кнопки
 *
 *  leftButtonDescription - описание левой кнопки
 *
 *  rightButtonAction - действие при нажатии на правую кнопку
 *
 *  leftButtonAction - действие при нажатии на левую кнопку
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    rightButtonIcon: Int? = null,
    leftButtonIcon: Int? = null,
    rightButtonDescription: String? = null,
    leftButtonDescription: String? = null,
    rightButtonAction: () -> Unit = {},
    leftButtonAction: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title, fontSize = 22.sp) },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),

        actions = {
            rightButtonIcon?.let {
                IconButton(onClick = rightButtonAction) {
                    Icon(
                        ImageVector.vectorResource(rightButtonIcon),
                        contentDescription = rightButtonDescription,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },

        navigationIcon = {
            leftButtonIcon?.let {
                IconButton(onClick = leftButtonAction) {
                    Icon(
                        ImageVector.vectorResource(leftButtonIcon),
                        contentDescription = leftButtonDescription,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )
}
