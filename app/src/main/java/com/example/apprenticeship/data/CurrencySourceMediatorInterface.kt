package com.example.apprenticeship.data

interface CurrencySourceMediatorInterface {
    fun getDataSourceToUse(): CurrencyDataSource?
}