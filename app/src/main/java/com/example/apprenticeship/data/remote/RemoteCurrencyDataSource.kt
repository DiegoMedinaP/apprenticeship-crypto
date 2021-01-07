package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Single

interface RemoteCurrencyDataSource: CurrencyDataSource {
    fun getCurrencyTicker(book: String): Single<Ticker>
    fun getCurrencyOrderBook(book: String): Single<OrderBook>
}