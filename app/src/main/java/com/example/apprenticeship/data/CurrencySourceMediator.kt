package com.example.apprenticeship.data

import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.Currency
import io.reactivex.Single

class CurrencySourceMediator(private val remote:RemoteCurrencyDataSource, private val local:LocalCurrencyDataSource) : CurrencySourceMediatorInterface {
    override fun addDataSource(source: CurrencyDataSource) {
        TODO("Not yet implemented")
    }

    override fun getDataSourceToUse(): CurrencyDataSource {
        return remote
    }


}