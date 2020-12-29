package com.example.apprenticeship.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
        val book:String
        ):Parcelable{
    val comercialName :String get() {
        return when{
            book.contains("btc") -> "Bitcoin"
            book.contains("eth") -> "Etherum"
            book.contains("xrp") -> "XRP"
            book.contains("ltc") -> "Litecoin"
            book.contains("bch") -> "Bitcoin Cash"
            book.contains("tusd") -> "TrueUSD"
            book.contains("mana") -> "MANA"
            book.contains("gnt") -> "Golem"
            book.contains("bat") -> "BAT"
            book.contains("dai") -> "DAI"
            else -> ""
        }
    }
}