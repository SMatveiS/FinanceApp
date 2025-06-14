package com.example.myfinance.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfinance.ListItem
import com.example.myfinance.R

@Composable
fun SettingsScreen(){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(16.dp)
        ) {
            Text(
                "Темная тема",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Switch(
                checked = false,
                onCheckedChange = {}
            )
        }
        HorizontalDivider()
        ListItem(
            leftTitle = "Основной цвет",
            rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Звуки",
            rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Хаптики",
            rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Код пароль",
            rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Синхронизация",
            rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Язык",
            rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "О программе",
            rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
            listHeight = 56
        )
        HorizontalDivider()
    }
}