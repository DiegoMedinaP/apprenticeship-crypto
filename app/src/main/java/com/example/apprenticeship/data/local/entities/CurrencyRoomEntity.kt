package com.example.apprenticeship.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker


@Entity(tableName = "Currency")
data class CurrencyRoomEntity (
    @PrimaryKey
    val book:String,
    //var orderBook: OrderBook?,
    //var ticker: Ticker?
        )