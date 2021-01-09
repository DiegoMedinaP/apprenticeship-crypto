package com.example.apprenticeship.data

import com.example.apprenticeship.data.local.LocalCurrencyDataSource

class CurrencySourceMediator(
    private val remote: CurrencyDataSource,
    private val local: LocalCurrencyDataSource
) : CurrencySourceMediatorInterface {
    override fun addDataSource(source: CurrencyDataSource) {
        TODO("Not yet implemented")
    }

    override fun getDataSourceToUse(): CurrencyDataSource {
        return remote
    }


}