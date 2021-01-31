package com.example.apprenticeship.data.local

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.domain.*
import javax.inject.Inject

class LocalCurrencyDataSourceImpl @Inject constructor(private val currencyDao: CurrencyDao) :
    CurrencyDataSource {

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

    override suspend fun getOhlc(
        book: String,
        timeBucket: String,
        start: String,
        end: String
    ): List<Ohlc> {
        //TODO change to get data from Room
        return arrayListOf()
    }
}
