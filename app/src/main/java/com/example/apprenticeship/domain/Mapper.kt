package com.example.apprenticeship.domain

import com.example.apprenticeship.data.remote.entities.*

fun CurrencyEntity.toCurrencyDomain()= book?.let { Currency(it) }

fun PayloadEntity.toCurrencyListDomain():List<Currency>{
    val currencies = arrayListOf<Currency>()
    for (currency in this.currencies){
        currency.toCurrencyDomain()?.let { currencies.add(it) }
    }
    return currencies.toList()
}

fun TickerEntity.toTickerDomain()= Ticker(
    this.info.high,
    this.info.last,
    this.info.created_at,
    this.info.book,
    this.info.volume,
    this.info.vwap,
    this.info.low,
    this.info.ask,
    this.info.bid,
    this.info.change_24
)

fun OrderBookEntity.toOrderBookDomain()= OrderBook(
    this.info.updated_at,
    this.info.sequence,
    this.info.bids.toBidsDomain()
)

fun BidEntity.toBidDomain()= Bid(
    book,
    price,
    amount
)

fun ArrayList<BidEntity>.toBidsDomain():ArrayList<Bid>{
    val bids = arrayListOf<Bid>()
    for (bid in this){
        bid.toBidDomain().let { bids.add(it) }
    }
    return bids
}