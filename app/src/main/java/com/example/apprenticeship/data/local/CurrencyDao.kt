package com.example.apprenticeship.data.local

import androidx.room.*
import com.example.apprenticeship.data.local.entities.CurrencyRoomEntity
import io.reactivex.Single

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM Currency")
    fun getAllCurrencies():Single<List<CurrencyRoomEntity>>

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertCurrency(currency:CurrencyRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertCurrencies(currencies:List<CurrencyRoomEntity>)

    @Delete
    suspend fun deleteCurrency(currency:CurrencyRoomEntity)

    @Update
    suspend fun updateCurrency(currency:CurrencyRoomEntity)
}