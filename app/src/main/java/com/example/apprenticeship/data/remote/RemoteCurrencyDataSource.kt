package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Single

interface RemoteCurrencyDataSource: CurrencyDataSource {
    suspend fun getCurrencyTicker(book: String): Ticker
    suspend fun getCurrencyOrderBook(book: String): OrderBook
}