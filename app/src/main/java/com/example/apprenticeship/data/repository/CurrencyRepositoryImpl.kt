package com.example.apprenticeship.data.repository

import com.example.apprenticeship.data.mediator.CurrencySourceMediatorInterface
import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val dataSourceMediator: CurrencySourceMediatorInterface
) : CurrencyRepository {

    override suspend fun getCurrencies(): List<Currency> {
        return dataSourceMediator.getDataSourceToUse()!!.getCurrencies()

        /*if (Network.isNetworkConnected) {
            val currencies = remoteDataSource.getCurrencies().filter {
                it.book.contains("mxn")
            }
            saveCurrencies(currencies)
            currencies
        } else {
            localDataSource.getCurrencies()
        }*/
    }

    override suspend fun getCurrencyTicker(book: String): Ticker = withContext(Dispatchers.IO) {
        return@withContext dataSourceMediator.getDataSourceToUse()!!.getCurrencyTicker(book)
        /*if (Network.isNetworkConnected) {
            val ticker = remoteDataSource.getCurrencyTicker(book)
            localDataSource.updateTicker(ticker)
            return@withContext ticker
        } else {
            return@withContext localDataSource.getCurrencyTicker(book)
        }*/
    }

    override suspend fun getCurrencyOrderBook(book: String): OrderBook =
        withContext(Dispatchers.IO) {
            return@withContext dataSourceMediator.getDataSourceToUse()!!.getCurrencyOrderBook(book)
            /*if (Network.isNetworkConnected) {
                val orderBook = remoteDataSource.getCurrencyOrderBook(book)
                localDataSource.updateOrderBook(book, orderBook)
                return@withContext orderBook
            } else {
                return@withContext localDataSource.getCurrencyOrderBook(book)
            }*/
        }

}