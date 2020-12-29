package com.example.apprenticeship.data.remote

import com.example.apprenticeship.domain.Currency
import io.reactivex.Single

interface RemoteCurrencyDataSource {
    fun getAvailableBooks(): Single<List<Currency>>
}