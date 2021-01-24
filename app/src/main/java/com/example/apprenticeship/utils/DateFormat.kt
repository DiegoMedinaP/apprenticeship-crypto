package com.example.apprenticeship.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.toDateString(stringToFormat: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT)
    parser.timeZone = TimeZone.getTimeZone("GMT")
    val parsed: Date? = parser.parse(stringToFormat)
    return parsed.toString()
}
