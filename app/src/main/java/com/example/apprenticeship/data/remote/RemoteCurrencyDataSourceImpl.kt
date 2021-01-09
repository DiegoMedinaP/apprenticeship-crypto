package com.example.apprenticeship.data.remote

import com.example.apprenticeship.domain.*
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService) :
    RemoteCurrencyDataSource {

    override suspend fun getCurrencies(): List<Currency> =
        currencyService.getAvailableBooks().toCurrencyListDomain()

    override suspend fun getCurrencyTicker(book: String): Ticker =
        currencyService.getCurrencyTicker(book).toTickerDomain()


    override suspend fun getCurrencyOrderBook(book: String): OrderBook =
        currencyService.getOrderBook(book).toOrderBookDomain()

}