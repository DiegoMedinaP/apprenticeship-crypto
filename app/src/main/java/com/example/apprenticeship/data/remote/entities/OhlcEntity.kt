package com.example.apprenticeship.data.remote.entities

import com.google.gson.annotations.SerializedName

data class OhlcEntity(
    val success: Boolean,
    @SerializedName("payload")
    val info: ArrayList<OhlcInfoEntity>
)

data class OhlcInfoEntity(
    val bucket_start_time: String,
    val first_trade_time: String,
    val last_trade_time: String,
    val first_rate: String,
    val last_rate: String,
    val min_rate: String,
    val max_rate: String,
    val trade_count: String,
    val volume: String,
    val vwap: String
)