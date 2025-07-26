package com.example.data.utils

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun formatUiDateToDtoDate(date: String): String {
    val localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"))

    val zonedDateTime = localDateTime.atZone(ZoneOffset.UTC)

    return zonedDateTime.format(DateTimeFormatter.ISO_INSTANT)
}