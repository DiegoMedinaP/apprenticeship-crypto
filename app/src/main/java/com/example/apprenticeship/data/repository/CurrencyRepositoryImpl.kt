package com.example.apprenticeship.data.repository

import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import com.example.apprenticeship.utils.Network
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class CurrencyRepositoryImpl @Inject constructor(
        private val remoteDataSource: RemoteCurrencyDataSource,
        private val localDataSource: LocalCurrencyDataSource
        ):CurrencyRepository {

    override fun getCurrencies(): Single<List<Currency>> {
        return localDataSource.getCurrencies().subscribeOn(Schedulers.io())
        /*return remoteDataSource.getAvailableBooks().map{ currencies ->
            currencies.filter { currency->
                currency.book.contains("mxn")
            }
        }*/
    }

    override fun getCurrencyTicker(book:String): Single<Ticker> {
        return remoteDataSource.getCurrencyTicker(book)
    }

    override fun getCurrencyOrderBook(book: String): Single<OrderBook> {
        return remoteDataSource.getCurrencyOrderBook(book)

    }

    override fun saveCurrencyInfo(currency: Currency) {
        TODO("Not yet implemented")
    }

    override suspend fun saveCurrencies(currency: List<Currency>) {
        localDataSource.insertCurrenciesIntoRoom(currency)
    }
}