package com.example.apprenticeship.data.remote.service

import com.example.apprenticeship.data.remote.entities.CurrencyEntity
import com.example.apprenticeship.data.remote.entities.OhlcEntity
import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("available_books")
    suspend fun getAvailableBooks(): CurrencyEntity

    @GET("ticker")
    suspend fun getCurrencyTicker(@Query("book") book: String): TickerEntity

    @GET("order_book")
    suspend fun getOrderBook(@Query("book") book: String): OrderBookEntity

    @GET("ohlc")
    suspend fun getOhlcPoints(
        @Query("book") book: String,
        @Query("time_bucket") timeBucket: String,
        @Query("start") startPoint: String,
        @Query("end") endPoint: String
    ): OhlcEntity
}
