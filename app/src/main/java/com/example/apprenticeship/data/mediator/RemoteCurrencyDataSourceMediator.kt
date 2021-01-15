package com.example.apprenticeship.data.mediator

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.data.CurrencySourceMediatorInterface
import com.example.apprenticeship.utils.Network
import javax.inject.Inject

class RemoteCurrencyDataSourceMediator @Inject constructor(
    private val remoteCurrencyDataSource: CurrencyDataSource
) : CurrencySourceMediatorInterface {

    override fun getDataSourceToUse(): CurrencyDataSource? {
        return if (Network.isNetworkConnected) {
            remoteCurrencyDataSource
        } else null
    }
}