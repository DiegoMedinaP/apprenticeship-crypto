package com.example.apprenticeship.data.repository

import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import com.example.apprenticeship.utils.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteCurrencyDataSource,
    private val localDataSource: LocalCurrencyDataSource
) : CurrencyRepository {

    override suspend fun getCurrencies(): List<Currency> {
        return if (Network.isNetworkConnected) {
            val currencies = remoteDataSource.getCurrencies().filter {
                it.book.contains("mxn")
            }
            saveCurrencies(currencies)
            currencies
        } else {
            localDataSource.getCurrencies()
        }
    }

    override suspend fun getCurrencyTicker(book: String): Ticker = withContext(Dispatchers.IO) {
        val ticker = remoteDataSource.getCurrencyTicker(book)
        val currency = Currency(book)
        currency.ticker = ticker
        saveCurrencyInfo(currency)
        return@withContext ticker
    }

    override suspend fun getCurrencyOrderBook(book: String): OrderBook = withContext(Dispatchers.IO){
        val orderBook = remoteDataSource.getCurrencyOrderBook(book)
        val currency = Currency(book)
        currency.orderBook = orderBook
        saveCurrencyInfo(currency)
        return@withContext orderBook
    }

    override suspend fun saveCurrencyInfo(currency: Currency) {
        localDataSource.updateCurrency(currency)
    }

    override suspend fun saveCurrencies(currency: List<Currency>) {
        localDataSource.insertCurrenciesIntoRoom(currency)
    }

}