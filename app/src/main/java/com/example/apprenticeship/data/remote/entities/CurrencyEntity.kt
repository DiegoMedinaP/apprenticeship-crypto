package com.example.apprenticeship.data.remote.entities

import com.google.gson.annotations.SerializedName

data class CurrencyEntity(
    val success: Boolean,
    @SerializedName("payload")
    val currencies: ArrayList<CurrencyInfoEntity>
)

data class CurrencyInfoEntity(
    val book: String?
)
