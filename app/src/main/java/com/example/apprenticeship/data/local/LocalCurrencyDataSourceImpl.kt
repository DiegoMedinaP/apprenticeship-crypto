package com.example.apprenticeship.data.local

import com.example.apprenticeship.data.local.entities.CurrencyRoomEntity
import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import com.example.apprenticeship.data.remote.CurrencyService
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.data.remote.entities.CurrencyEntity
import com.example.apprenticeship.domain.*
import io.reactivex.Single
import javax.inject.Inject


class LocalCurrencyDataSourceImpl @Inject constructor(private val currencyDao: CurrencyDao): LocalCurrencyDataSource {

    override fun getCurrencies(): Single<List<Currency>> {
        return currencyDao.getAllCurrencies().map(List<CurrencyRoomEntity>::toCurrenciesDomain)
    }

    override suspend fun insertCurrenciesIntoRoom(currencies: List<Currency>) {
        currencyDao.insertCurrencies(currencies.map {
            it.toCurrencyRoomEntity()
        })
    }

    override suspend fun updateCurrency(currency: Currency) {
        currencyDao.updateCurrency(currency.toCurrencyRoomEntity())
    }

}