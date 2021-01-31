package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.data.remote.service.CurrencyService
import com.example.apprenticeship.domain.*
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService, private val localDataSource: LocalCurrencyDataSource) :
    CurrencyDataSource {

    override suspend fun getCurrencies(): List<Currency> {
        val currencies = currencyService.getAvailableBooks().toCurrencyListDomain().filter {
            it.book.contains("mxn")
        }
        localDataSource.insertCurrenciesIntoRoom(currencies)
        return currencies
    }

    override suspend fun getCurrencyTicker(book: String): Ticker {
        val ticker = currencyService.getCurrencyTicker(book).toTickerDomain()
        localDataSource.updateTicker(ticker)
        return ticker
    }

    override suspend fun getCurrencyOrderBook(book: String): OrderBook {
        val orderBook = currencyService.getOrderBook(book).toOrderBookDomain()
        localDataSource.updateOrderBook(book, orderBook)
        return orderBook
    }

    override suspend fun getOhlc(book: String,timeBucket:String,start:String,end:String): List<Ohlc> {
        val ohlcPoints = currencyService.getOhlcPoints(book,timeBucket,start,end)
        return ohlcPoints.toOhlcListDomain()
    }
}
