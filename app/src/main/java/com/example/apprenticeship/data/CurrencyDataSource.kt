package com.example.apprenticeship.data

import com.example.apprenticeship.data.remote.entities.OhlcEntity
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.Ohlc
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker

interface CurrencyDataSource {
    suspend fun getCurrencies(): List<Currency>
    suspend fun getCurrencyTicker(book: String): Ticker
    suspend fun getCurrencyOrderBook(book: String): OrderBook
    suspend fun getOhlc(book: String,timeBucket:String,start:String,end:String): List<Ohlc>
}
