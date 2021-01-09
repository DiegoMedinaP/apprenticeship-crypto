package com.example.apprenticeship.data.local

import androidx.room.*
import com.example.apprenticeship.data.local.entities.CurrencyRoomEntity
import com.example.apprenticeship.data.local.entities.OrderBookRoomEntity
import com.example.apprenticeship.data.local.entities.TickerRoomEntity

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM Currency")
    suspend fun getAllCurrencies(): List<CurrencyRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertCurrencies(currencies: List<CurrencyRoomEntity>)

    @Query("SELECT * FROM Ticker WHERE book = :book")
    suspend fun getTicker(book: String): TickerRoomEntity

    @Query("SELECT * FROM Ticker")
    suspend fun getTickers(): List<TickerRoomEntity>

    @Query("SELECT * FROM OrderBook WHERE book = :book")
    suspend fun getOrderBook(book: String): OrderBookRoomEntity

    @Delete
    suspend fun deleteCurrency(currency: CurrencyRoomEntity)

    @Update
    suspend fun updateCurrency(currency: CurrencyRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun updateTicker(ticker: TickerRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun updateOrderBook(orderBook: OrderBookRoomEntity)
}