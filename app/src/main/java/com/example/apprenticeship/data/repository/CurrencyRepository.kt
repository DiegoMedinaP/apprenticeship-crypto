package com.example.apprenticeship.data.repository

import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.Ohlc
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker

interface CurrencyRepository {

    suspend fun getCurrencies(): List<Currency>
    suspend fun getCurrencyTicker(book: String): Ticker
    suspend fun getCurrencyOrderBook(book: String): OrderBook
    suspend fun getOhlcPoints(book:String,bucketStart:String,start:String,end:String):List<Ohlc>
}
