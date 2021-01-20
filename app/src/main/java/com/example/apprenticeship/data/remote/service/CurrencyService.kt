package com.example.apprenticeship.data.remote.service

import com.example.apprenticeship.data.remote.entities.CurrencyEntity
import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("available_books")
    suspend fun getAvailableBooks(): CurrencyEntity

    @GET("ticker")
    suspend fun getCurrencyTicker(@Query("book")book:String): TickerEntity

    @GET("order_book")
    suspend fun getOrderBook(@Query("book")book:String): OrderBookEntity

}