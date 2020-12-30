package com.example.apprenticeship.data.remote

import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Single

interface RemoteCurrencyDataSource {
    fun getAvailableBooks(): Single<List<Currency>>
    fun getCurrencyTicker(book: String): Single<Ticker>
    fun getCurrencyOrderBook(book: String):Single<OrderBook>
}