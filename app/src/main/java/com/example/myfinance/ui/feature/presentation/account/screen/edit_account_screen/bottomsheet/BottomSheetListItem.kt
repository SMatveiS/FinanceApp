package com.example.myfinance.ui.feature.presentation.account.screen.edit_account_screen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSheetListItem(
    title: String,
    icon: ImageVector,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    listBackground: Color = MaterialTheme.colorScheme.background,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(listBackground)
            .clickable(true, onClick = onClick)
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.padding(start = 16.dp).size(24.dp)
        )

        Text(
            title,
            fontSize = 16.sp,
            color = textColor
        )
    }
}