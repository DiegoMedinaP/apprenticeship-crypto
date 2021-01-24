package com.example.apprenticeship.data.local

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import com.example.apprenticeship.domain.toCurrencyDomain
import com.example.apprenticeship.domain.toOrderBookDomain
import com.example.apprenticeship.domain.toTickerDomain
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
}
