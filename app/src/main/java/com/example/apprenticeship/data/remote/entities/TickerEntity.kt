package com.example.apprenticeship.data.remote.entities

import com.google.gson.annotations.SerializedName

data class TickerEntity(
    val success: Boolean,
    @SerializedName("payload")
    val info: TickerInfoEntity
)

data class TickerInfoEntity(
    val high: String?,
    val last: String?,
    val created_at: String?,
    val book: String?,
    val volume: String?,
    val vwap: String?,
    val low: String?,
    val ask: String?,
    val bid: String?,
    val change_24: String?
)
