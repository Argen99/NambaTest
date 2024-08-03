package com.example.nambatest.core.extensions

import com.example.nambatest.core.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.formatToDateString(pattern: String = Constants.DATE_TIME_FORMAT_PATTERN): String {
    val date = Date(this * 1000)
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.format(date)
}