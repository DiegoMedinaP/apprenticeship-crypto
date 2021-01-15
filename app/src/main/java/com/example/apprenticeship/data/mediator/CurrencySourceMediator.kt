package com.example.apprenticeship.data.mediator

import com.example.apprenticeship.data.CurrencyDataSource
import javax.inject.Inject

class CurrencySourceMediator @Inject constructor(
    val handlers: List<CurrencySourceMediatorInterface>
) : CurrencySourceMediatorInterface {

    override fun getDataSourceToUse(): CurrencyDataSource? {
        for(handler in handlers){
            handler.getDataSourceToUse()?.let {
                return handler.getDataSourceToUse()
            }
        }
        return null
    }
}