package com.example.apprenticeship.data.repository

import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyRepository {

    fun getCurrencies(): Single<List<Currency>>
    fun getCurrencyTicker(book: String): Single<Ticker>
    fun getCurrencyOrderBook(book: String):Single<OrderBook>
}