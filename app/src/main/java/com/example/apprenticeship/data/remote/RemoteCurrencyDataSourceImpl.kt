package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.domain.*
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService,private val localdataSource:LocalCurrencyDataSource) :
    CurrencyDataSource {

    override suspend fun getCurrencies(): List<Currency> {
        val currencies = currencyService.getAvailableBooks().toCurrencyListDomain().filter {
            it.book.contains("mxn")
        }
        localdataSource.insertCurrenciesIntoRoom(currencies)
        return currencies
    }


    override suspend fun getCurrencyTicker(book: String): Ticker {
        val ticker = currencyService.getCurrencyTicker(book).toTickerDomain()
        localdataSource.updateTicker(ticker)
        return ticker
    }

    override suspend fun getCurrencyOrderBook(book: String): OrderBook {
        val orderBook = currencyService.getOrderBook(book).toOrderBookDomain()
        localdataSource.updateOrderBook(book,orderBook)
        return orderBook
    }

}