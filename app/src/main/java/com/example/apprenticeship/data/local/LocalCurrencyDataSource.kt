package com.example.apprenticeship.data.local

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.domain.Currency

interface LocalCurrencyDataSource : CurrencyDataSource {

    suspend fun insertCurrenciesIntoRoom(currencies: List<Currency>)
    suspend fun updateCurrency(currency: Currency)
}