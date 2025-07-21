package com.example.myfinance.data.utils

import com.example.ui.uiDateTimeFormat
import java.time.OffsetDateTime

fun formatDtoDateToUiDate(date: String): String {
    return OffsetDateTime.parse(date).format(uiDateTimeFormat)
}