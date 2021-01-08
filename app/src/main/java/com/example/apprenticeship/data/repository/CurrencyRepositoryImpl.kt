package com.example.apprenticeship.data.repository

import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteCurrencyDataSource,
    private val localDataSource: LocalCurrencyDataSource
) : CurrencyRepository {

    //val mediator = CurrencySourceMediator()

    override suspend fun getCurrencies(isNetworkAvailable: Boolean): List<Currency> {
        return if (isNetworkAvailable) {
            val currencies = remoteDataSource.getCurrencies().filter {
                it.book.contains("mxn")
            }
            saveCurrencies(currencies)
            currencies
        } else localDataSource.getCurrencies()
    }

    override fun getCurrencyTicker(book: String): Single<Ticker> {
        return remoteDataSource.getCurrencyTicker(book)
    }

    override fun getCurrencyOrderBook(book: String): Single<OrderBook> {
        return remoteDataSource.getCurrencyOrderBook(book)

    }

    override suspend fun saveCurrencyInfo(currency: Currency) {
        localDataSource.updateCurrency(currency)
    }

    override suspend fun saveCurrencies(currency: List<Currency>) {
        localDataSource.insertCurrenciesIntoRoom(currency)
    }

}