package com.example.apprenticeship

import com.example.apprenticeship.data.PayloadEntity
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.toCurrencyListDomain
import io.reactivex.Single
import javax.inject.Inject


class LocalCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService): RemoteCurrencyDataSource {

    override fun getAvailableBooks(): Single<List<Currency>> =
            currencyService.getAvailableBooks()
                    .map(PayloadEntity::toCurrencyListDomain)

}