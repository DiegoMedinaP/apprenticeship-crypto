package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.data.remote.entities.CurrencyEntity
import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import com.example.apprenticeship.domain.*
import io.reactivex.Single
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService): RemoteCurrencyDataSource {

    override suspend fun getCurrencies(): List<Currency> =
        currencyService.getAvailableBooks().toCurrencyListDomain()

    override fun getCurrencyTicker(book: String): Single<Ticker> =
        currencyService.getCurrencyTicker(book).map(TickerEntity::toTickerDomain)


    override fun getCurrencyOrderBook(book: String): Single<OrderBook> =
            currencyService.getOrderBook(book).map(OrderBookEntity::toOrderBookDomain)

}