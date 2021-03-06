package com.example.apprenticeship.domain

import com.example.apprenticeship.data.local.entities.CurrencyRoomEntity
import com.example.apprenticeship.data.local.entities.OrderBookRoomEntity
import com.example.apprenticeship.data.local.entities.TickerRoomEntity
import com.example.apprenticeship.data.remote.entities.*
import com.google.gson.Gson

fun CurrencyInfoEntity.toCurrencyDomain() = book?.let { Currency(it) }

fun CurrencyEntity.toCurrencyListDomain(): List<Currency> {
    val currencies = arrayListOf<Currency>()
    for (currency in this.currencies) {
        currency.toCurrencyDomain()?.let { currencies.add(it) }
    }
    return currencies.toList()
}

fun TickerEntity.toTickerDomain() = Ticker(
    this.info.high ?: "",
    this.info.last ?: "",
    this.info.created_at ?: "",
    this.info.book ?: "",
    this.info.volume ?: "",
    this.info.vwap ?: "",
    this.info.low ?: "",
    this.info.ask ?: "",
    this.info.bid ?: "",
    this.info.change_24 ?: ""
)

fun OrderBookEntity.toOrderBookDomain() = OrderBook(
    this.info.updated_at,
    this.info.sequence,
    this.info.bids.toBidsAskDomain(),
    this.info.asks.toBidsAskDomain()
)

fun BidAskEntity.toBidDomain() = BidAskModel(
    book,
    price,
    amount
)

fun ArrayList<BidAskEntity>.toBidsAskDomain(): ArrayList<BidAskModel> {
    val bids = arrayListOf<BidAskModel>()
    for (bid in this) {
        bid.toBidDomain().let { bids.add(it) }
    }
    return bids
}

fun List<CurrencyRoomEntity>.toCurrenciesDomain(): ArrayList<Currency> {
    val currencies = arrayListOf<Currency>()
    for (currency in this) {
        currency.toCurrencyDomain().let { currencies.add(it) }
    }
    return currencies
}

fun CurrencyRoomEntity.toCurrencyDomain(): Currency {
    val cur = Currency(
        book,
        Gson().fromJson(orderBook, OrderBook::class.java),
        Gson().fromJson(ticker, Ticker::class.java)
    )
    return cur
}

fun Currency.toCurrencyRoomEntity() = CurrencyRoomEntity(
    book,
    Gson().toJson(orderBook),
    Gson().toJson(ticker)
)

fun TickerRoomEntity.toTickerDomain(): Ticker {

    return Ticker(
        high ?: "",
        last ?: "",
        created_at ?: "",
        book,
        volume ?: "",
        vwap ?: "",
        low ?: "",
        ask ?: "",
        bid ?: "",
        change_24 ?: ""
    )
}

fun OrderBookRoomEntity.toOrderBookDomain(): OrderBook {
    return Gson().fromJson(this.info, OrderBook::class.java)
}

fun Ticker.toTickerRoomEntity() = TickerRoomEntity(
    book!!,
    high,
    last,
    created_at,
    volume,
    vwap,
    low,
    ask,
    bid,
    change_24
)

fun OrderBook.toOrderBookRoomEntity(book: String) = OrderBookRoomEntity(
    book,
    Gson().toJson(this)
)

fun OhlcInfoEntity.toOhlcDomain() = Ohlc(
    bucket_start_time,
    vwap
)

fun OhlcEntity.toOhlcListDomain(): List<Ohlc> {
    val ohlcPoints = arrayListOf<Ohlc>()
    for (ohlc in this.info) {
        ohlcPoints.add(ohlc.toOhlcDomain())
    }
    return ohlcPoints
}
