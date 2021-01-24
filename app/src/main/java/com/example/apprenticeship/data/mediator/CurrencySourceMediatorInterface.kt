package com.example.apprenticeship.data.mediator

import com.example.apprenticeship.data.CurrencyDataSource

interface CurrencySourceMediatorInterface {
    fun getDataSourceToUse(): CurrencyDataSource?
}
