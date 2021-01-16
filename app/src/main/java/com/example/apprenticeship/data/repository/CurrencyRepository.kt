package com.example.apprenticeship.data.repository

import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyRepository {

    suspend fun getCurrencies(): List<Currency>
    suspend fun getCurrencyTicker(book: String): Ticker
    suspend fun getCurrencyOrderBook(book: String):OrderBook
}