package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.remote.entities.CurrencyEntity
import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import com.example.apprenticeship.domain.*
import io.reactivex.Single
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService): RemoteCurrencyDataSource {

    override fun getAvailableBooks(): Single<List<Currency>> =
            currencyService.getAvailableBooks()
                    .map(CurrencyEntity::toCurrencyListDomain)

    override fun getCurrencyTicker(book: String): Single<Ticker> =
        currencyService.getCurrencyTicker(book).map(TickerEntity::toTickerDomain)


    override fun getCurrencyOrderBook(book: String): Single<OrderBook> =
            currencyService.getOrderBook(book).map(OrderBookEntity::toOrderBookDomain)


}