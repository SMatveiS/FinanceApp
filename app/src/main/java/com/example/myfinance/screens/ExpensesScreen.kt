package com.example.myfinance.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.ListItem

@Composable
fun ExpensesScreen() {
    Column {
        ListItem(
            leftTitle = "Всего",
            rightTitle = "436 558 ₽",
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Аренда квартиры",
            rightTitle = "100 000 ₽",
            leftIcon = "\uD83C\uDFE1",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Одежда",
            rightTitle = "100 000 ₽",
            leftIcon = "\uD83D\uDC57",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "На собачку",
            leftSubtitle = "Джек",
            rightTitle = "100 000 ₽",
            leftIcon = "\uD83D\uDC36",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "На собачку",
            leftSubtitle = "Энни",
            rightTitle = "100 000 ₽",
            leftIcon = "\uD83D\uDC36",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Ремонт квартиры",
            rightTitle = "100 000 ₽",
            leftIcon = "РК",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Продукты",
            rightTitle = "100 000 ₽",
            leftIcon = "\uD83C\uDF6D",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Спортзал",
            rightTitle = "100 000 ₽",
            leftIcon = "\uD83C\uDFCB\uFE0F",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Медицина",
            rightTitle = "100 000 ₽",
            leftIcon = "\uD83D\uDC8A",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
        )
        HorizontalDivider()
    }
}

