package com.example.myfinance.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myfinance.R

@Composable
fun AppFAB(onClick: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.background,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.plus_for_add_button),
            contentDescription = "Добавить",
            modifier = Modifier.size(15.56.dp)
        )
    }
}