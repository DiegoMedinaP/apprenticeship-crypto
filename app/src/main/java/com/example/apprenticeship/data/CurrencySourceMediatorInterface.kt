package com.example.apprenticeship.data

interface CurrencySourceMediatorInterface {
    fun addDataSource(source : CurrencyDataSource)
    fun getDataSourceToUse(): CurrencyDataSource
}