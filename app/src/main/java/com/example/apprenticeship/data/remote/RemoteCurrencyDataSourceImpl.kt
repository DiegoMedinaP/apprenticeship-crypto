package com.example.apprenticeship.data.remote

import com.example.apprenticeship.CurrencyService
import com.example.apprenticeship.data.PayloadEntity
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.toCurrencyListDomain
import io.reactivex.Single
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService): RemoteCurrencyDataSource {

    override fun getAvailableBooks(): Single<List<Currency>> =
            currencyService.getAvailableBooks()
                    .map(PayloadEntity::toCurrencyListDomain)



}