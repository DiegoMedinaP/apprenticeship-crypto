package com.example.apprenticeship.data.local

import com.example.apprenticeship.data.local.entities.CurrencyRoomEntity
import com.example.apprenticeship.data.remote.entities.CurrencyEntity
import com.example.apprenticeship.domain.Currency
import io.reactivex.Single

interface LocalCurrencyDataSource {

    fun getCurrencies(): Single<List<Currency>>
    suspend fun insertCurrenciesIntoRoom(currencies: List<Currency>)
    suspend fun updateCurrency(currency: Currency)
}