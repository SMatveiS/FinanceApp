package com.example.ui

import java.time.format.DateTimeFormatter
import java.util.Locale

val uiDateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")
val uiDateFormat = DateTimeFormatter.ofPattern("d MMMM y", Locale("ru"))
val uiTimeFormat = DateTimeFormatter.ofPattern("HH:mm")