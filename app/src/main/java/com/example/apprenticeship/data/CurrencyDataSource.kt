package com.example.apprenticeship.data

import com.example.apprenticeship.domain.Currency
import io.reactivex.Single

interface CurrencyDataSource {
    fun getCurrencies(): Single<List<Currency>>
}