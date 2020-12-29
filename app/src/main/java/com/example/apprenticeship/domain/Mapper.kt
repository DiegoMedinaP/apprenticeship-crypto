package com.example.apprenticeship.domain

import com.example.apprenticeship.data.CurrencyEntity
import com.example.apprenticeship.data.PayloadEntity

fun CurrencyEntity.toCurrencyDomain()= book?.let { Currency(it) }

fun PayloadEntity.toCurrencyListDomain():List<Currency>{
    val currencies = arrayListOf<Currency>()
    for (currency in this.currencies){
        currency.toCurrencyDomain()?.let { currencies.add(it) }
    }
    return currencies.toList()
}