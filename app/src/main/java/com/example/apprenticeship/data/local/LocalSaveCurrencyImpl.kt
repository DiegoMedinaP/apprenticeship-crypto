package com.example.apprenticeship.data.local

import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import com.example.apprenticeship.domain.toCurrencyRoomEntity
import com.example.apprenticeship.domain.toOrderBookRoomEntity
import com.example.apprenticeship.domain.toTickerRoomEntity
import javax.inject.Inject

class LocalSaveCurrencyImpl @Inject constructor(private val currencyDao: CurrencyDao) :
    LocalCurrencyDataSource {
    override suspend fun insertCurrenciesIntoRoom(currencies: List<Currency>) {
        currencyDao.insertCurrencies(currencies.map {
            it.toCurrencyRoomEntity()
        })
    }

    override suspend fun updateCurrency(currency: Currency) {
        currencyDao.updateCurrency(currency.toCurrencyRoomEntity())
    }

    override suspend fun updateTicker(ticker: Ticker) {
        currencyDao.insertTicker(ticker.toTickerRoomEntity())
    }

    override suspend fun updateOrderBook(book: String, orderBook: OrderBook) {
        currencyDao.insertOrderBook(orderBook.toOrderBookRoomEntity(book))
    }
}
