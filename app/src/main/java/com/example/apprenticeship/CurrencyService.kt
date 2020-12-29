package com.example.apprenticeship

import com.example.apprenticeship.data.OrderBookEntity
import com.example.apprenticeship.data.PayloadEntity
import com.example.apprenticeship.data.TickerEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyService {

    @GET("available_books")
    fun getAvailableBooks(): Single<PayloadEntity>

    @GET("ticker/:{query}")
    fun getCurrencyTicker(@Path("query")query:String): Single<TickerEntity>

    @GET("ticker/:{query}")
    fun getOrderBook(@Path("query")query:String): Single<OrderBookEntity>

}