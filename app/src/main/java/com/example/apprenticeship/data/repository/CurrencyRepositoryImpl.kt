package com.example.apprenticeship.data.repository

import com.example.apprenticeship.data.mediator.CurrencySourceMediatorInterface
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.Ohlc
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val dataSourceMediator: CurrencySourceMediatorInterface
) : CurrencyRepository {

    override suspend fun getCurrencies(): List<Currency> =
        dataSourceMediator.getDataSourceToUse()?.getCurrencies() ?: listOf()

    override suspend fun getCurrencyTicker(book: String) =
        dataSourceMediator.getDataSourceToUse()!!.getCurrencyTicker(book)

    override suspend fun getCurrencyOrderBook(book: String) =
        dataSourceMediator.getDataSourceToUse()!!.getCurrencyOrderBook(book)

    override suspend fun getOhlcPoints(
        book: String,
        bucketStart: String,
        start: String,
        end: String
    ): List<Ohlc> =
        dataSourceMediator.getDataSourceToUse()!!.getOhlc(book, bucketStart, start, end)


}
