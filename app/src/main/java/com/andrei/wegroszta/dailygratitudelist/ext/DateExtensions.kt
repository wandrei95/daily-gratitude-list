package com.andrei.wegroszta.dailygratitudelist.ext

import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "dd.MM.yyyy"

fun Long.toFormattedDate(): String = SimpleDateFormat(DATE_FORMAT, Locale.US).format(this)

