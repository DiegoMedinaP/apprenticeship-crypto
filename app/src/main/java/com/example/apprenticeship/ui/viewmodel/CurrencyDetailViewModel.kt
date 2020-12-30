package com.example.apprenticeship.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class CurrencyDetailViewModel @ViewModelInject constructor(private val repository: CurrencyRepository): BaseViewModel() {

    private val _currencyEvents = MutableLiveData<CurrencyNavegation>()
    val currencyEvents : LiveData<CurrencyNavegation> get() = _currencyEvents

    init {
        fetchTickerAndOrderBookInfo()
    }

    private fun fetchTickerAndOrderBookInfo() {
        _currencyEvents.value = CurrencyNavegation.ShowLoading
        repository.getCurrencyTicker("btc_mxn")
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                return@flatMap repository.getCurrencyOrderBook("btc_mxn")
            }
            .subscribeBy(
                onSuccess = {
                    _currencyEvents.postValue(CurrencyNavegation.ShowCurrencies(it))
                },
                onError = {
                    _currencyEvents.postValue(CurrencyNavegation.ShowNotFound(it))

                }
            ).addTo(disposable)
    }


    sealed class CurrencyNavegation{
        data class ShowCurrencies(val currencies:OrderBook): CurrencyNavegation()
        data class ShowNotFound(val error: Throwable) : CurrencyNavegation()
        object ShowLoading: CurrencyNavegation()
    }
}