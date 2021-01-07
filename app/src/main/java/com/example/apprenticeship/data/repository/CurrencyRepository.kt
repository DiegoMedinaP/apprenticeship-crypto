package com.example.apprenticeship.data.repository

import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyRepository {

    fun getCurrencies(isNetworkAvailable:Boolean): Single<List<Currency>>
    fun getCurrencyTicker(book: String): Single<Ticker>
    fun getCurrencyOrderBook(book: String):Single<OrderBook>

    suspend fun saveCurrencyInfo(currency: Currency)
    suspend fun saveCurrencies(currency: List<Currency>)
}