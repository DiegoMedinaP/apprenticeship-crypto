package com.example.apprenticeship.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.Navegation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class CurrencyViewModel @ViewModelInject constructor(private val repository: CurrencyRepository) :BaseViewModel() {


    private val _currencyListEvents = MutableLiveData<Navegation>()
    val currencyListEvents : LiveData<Navegation> get() = _currencyListEvents

    private val _currencyEvents = MutableLiveData<Navegation>()
    val currencyEvents : LiveData<Navegation> get() = _currencyEvents

    private val _currency = MutableLiveData<Currency>()
    val currency : LiveData<Currency> get() = _currency

    init {
        fetchCurrencyInfo()
    }


    private fun fetchCurrencyInfo(){
        _currencyListEvents.value = Navegation.ShowLoading
        repository.getCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                onSuccess = {
                            _currencyListEvents.postValue(Navegation.ShowResult(it))
                },
                onError = {
                    _currencyListEvents.postValue(Navegation.ShowNotFound(it))

                }
        ).addTo(disposable)
    }

    fun setSelectedCurrency(currency: Currency) {
        _currency.value = currency
    }

    fun fetchTickerAndOrderBookInfo() {
        _currencyEvents.value = Navegation.ShowLoading
        repository.getCurrencyTicker("btc_mxn")
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                return@flatMap repository.getCurrencyOrderBook("btc_mxn")
            }
            .subscribeBy(
                onSuccess = {
                    _currencyEvents.postValue(Navegation.ShowResult(it))
                },
                onError = {
                    _currencyEvents.postValue(Navegation.ShowNotFound(it))

                }
            ).addTo(disposable)
    }
}