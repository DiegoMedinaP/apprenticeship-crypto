package com.example.apprenticeship.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ticker")
data class TickerRoomEntity(
    @PrimaryKey
    val book: String,
    val high: String?,
    val last: String?,
    val created_at: String?,
    val volume: String?,
    val vwap: String?,
    val low: String?,
    val ask: String?,
    val bid: String?,
    val change_24: String?
)
