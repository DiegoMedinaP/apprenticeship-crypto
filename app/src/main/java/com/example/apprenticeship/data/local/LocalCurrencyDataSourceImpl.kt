package com.example.apprenticeship.data.local

import com.example.apprenticeship.domain.*
import javax.inject.Inject


class LocalCurrencyDataSourceImpl @Inject constructor(private val currencyDao: CurrencyDao) :
    LocalCurrencyDataSource {

    override suspend fun getCurrencies(): List<Currency> {
        return currencyDao.getAllCurrencies().map {
            return@map it.toCurrencyDomain()
        }
    }

    override suspend fun getCurrencyTicker(book: String): Ticker {
        return currencyDao.getTicker(book).toTickerDomain()
    }

    override suspend fun getCurrencyOrderBook(book: String): OrderBook {
        return currencyDao.getOrderBook(book).toOrderBookDomain()
    }

    override suspend fun insertCurrenciesIntoRoom(currencies: List<Currency>) {
        currencyDao.insertCurrencies(currencies.map {
            it.toCurrencyRoomEntity()
        })
    }

    override suspend fun updateCurrency(currency: Currency) {
        currencyDao.updateCurrency(currency.toCurrencyRoomEntity())
    }

    override suspend fun updateTicker(ticker: Ticker) {
        currencyDao.updateTicker(ticker.toTickerRoomEntity())
    }

    override suspend fun updateOrderBook(book: String, orderBook: OrderBook) {
        currencyDao.updateOrderBook(orderBook.toOrderBookRoomEntity(book))
    }

}