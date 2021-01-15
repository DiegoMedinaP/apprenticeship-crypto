package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.domain.*
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService) :
    CurrencyDataSource {

    override suspend fun getCurrencies(): List<Currency> =
        currencyService.getAvailableBooks().toCurrencyListDomain().filter {
            it.book.contains("mxn")
        }

    override suspend fun getCurrencyTicker(book: String): Ticker =
        currencyService.getCurrencyTicker(book).toTickerDomain()


    override suspend fun getCurrencyOrderBook(book: String): OrderBook =
        currencyService.getOrderBook(book).toOrderBookDomain()

}