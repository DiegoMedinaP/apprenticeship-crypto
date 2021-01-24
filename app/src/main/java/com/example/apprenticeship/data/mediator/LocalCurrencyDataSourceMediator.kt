package com.example.apprenticeship.data.mediator

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.utils.Network
import javax.inject.Inject

class LocalCurrencyDataSourceMediator @Inject constructor(
    private val localCurrencyDataSource: CurrencyDataSource
) : CurrencySourceMediatorInterface {

    override fun getDataSourceToUse(): CurrencyDataSource? {
        return if (!Network.isNetworkConnected) {
            localCurrencyDataSource
        } else null
    }
}
