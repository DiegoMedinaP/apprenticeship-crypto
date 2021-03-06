package com.example.apprenticeship.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun toDateString(stringToFormat: String): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT)
        parser.timeZone = TimeZone.getTimeZone("GMT")
        val parsed: Date? = parser.parse(stringToFormat)
        parsed.toString()
    }catch (e:Exception){
        ""
    }
}

fun convertStringFormatIntoUnixTime(stringToFormat: String?):String{
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT)
        parser.timeZone = TimeZone.getTimeZone("GMT")
        val date: Date? = parser.parse(stringToFormat ?: "")
        date?.time.toString()
    }catch (e: Exception){
        ""
    }

}

fun convertUnixTimeIntoStringFormat(time: String): String{
    return try{
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT)
        parser.timeZone = TimeZone.getTimeZone("GMT")
        val date =Date(time.toLong())
        parser.format(date)
    }
    catch (e:Exception){
        ""
    }
}