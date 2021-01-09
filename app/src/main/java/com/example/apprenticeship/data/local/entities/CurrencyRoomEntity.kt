package com.example.apprenticeship.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Currency")
data class CurrencyRoomEntity (
    @PrimaryKey
    val book:String,
    var orderBook: String?,
    var ticker: String?
        )