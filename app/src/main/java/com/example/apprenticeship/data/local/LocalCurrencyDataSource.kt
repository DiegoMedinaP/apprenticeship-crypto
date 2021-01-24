package com.example.apprenticeship.data.local

import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker

interface LocalCurrencyDataSource {

    suspend fun insertCurrenciesIntoRoom(currencies: List<Currency>)
    suspend fun updateCurrency(currency: Currency)
    suspend fun updateTicker(ticker: Ticker)
    suspend fun updateOrderBook(book: String, orderBook: OrderBook)
}
