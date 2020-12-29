package com.example.apprenticeship.data

import com.google.gson.annotations.SerializedName

data class PayloadEntity(
        @SerializedName("payload")
        val currencies:ArrayList<CurrencyEntity>
)

data class CurrencyEntity (
    val book:String?
)