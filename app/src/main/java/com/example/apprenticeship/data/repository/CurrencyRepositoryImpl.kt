package com.example.apprenticeship.data.repository

import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
        private val remoteDataSource: RemoteCurrencyDataSource
        //, private val localDataSource:LocalCurrencyDataSource
        ):CurrencyRepository {

    override fun getCurrencies(): Single<List<Currency>> {
        return remoteDataSource.getAvailableBooks().map{ currencies ->
            currencies.filter { currency->
                currency.book.contains("mxn")
            }
        }
    }

    override fun getCurrencyTicker(book:String): Single<Ticker> {
        return remoteDataSource.getCurrencyTicker(book)
    }

    override fun getCurrencyOrderBook(book: String): Single<OrderBook> {
        return remoteDataSource.getCurrencyOrderBook(book)

    }
}