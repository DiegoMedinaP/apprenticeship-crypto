package com.example.apprenticeship.data.repository

import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.Currency
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

    //override fun getCurrencyTicker(): Single<Currency> {
    //    TODO("Not yet implemented")
    //}
//
    //override fun getCurrencyOrderBook(): Single<Currency> {
    //    TODO("Not yet implemented")
    //}
}