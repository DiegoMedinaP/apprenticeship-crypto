package com.example.apprenticeship.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OrderBook")
data class OrderBookRoomEntity(
    @PrimaryKey
    val book: String,
    val info: String?
)
