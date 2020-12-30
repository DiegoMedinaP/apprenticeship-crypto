package com.example.apprenticeship.data.local

import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.PayloadEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import com.example.apprenticeship.data.remote.CurrencyService
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.*
import io.reactivex.Single
import javax.inject.Inject


class LocalCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService): RemoteCurrencyDataSource {

    override fun getAvailableBooks(): Single<List<Currency>> =
            currencyService.getAvailableBooks()
                    .map(PayloadEntity::toCurrencyListDomain)

    override fun getCurrencyTicker(book:String): Single<Ticker> =
            currencyService.getCurrencyTicker(book).map(TickerEntity::toTickerDomain)

    override fun getCurrencyOrderBook(book: String): Single<OrderBook> {
        return currencyService.getOrderBook(book).map(OrderBookEntity::toOrderBookDomain)
    }

}