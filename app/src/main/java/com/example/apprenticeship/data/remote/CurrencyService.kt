package com.example.apprenticeship.data.remote

import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.PayloadEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyService {

    @GET("available_books")
    fun getAvailableBooks(): Single<PayloadEntity>

    @GET("ticker")
    fun getCurrencyTicker(@Query("book")book:String): Single<TickerEntity>

    @GET("order_book")
    fun getOrderBook(@Query("book")book:String): Single<OrderBookEntity>

}