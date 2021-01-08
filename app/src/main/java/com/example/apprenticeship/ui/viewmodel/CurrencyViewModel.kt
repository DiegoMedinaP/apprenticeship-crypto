package com.example.apprenticeship.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.Navegation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyViewModel @ViewModelInject constructor(private val repository: CurrencyRepository) :BaseViewModel() {

    private val _currencyListEvents = MutableLiveData<Navegation>()
    val currencyListEvents : LiveData<Navegation> get() = _currencyListEvents

    private val _currencyEvents = MutableLiveData<Navegation>()
    val currencyEvents : LiveData<Navegation> get() = _currencyEvents

    private val _currency = MutableLiveData<Currency>()
    val currency : LiveData<Currency> get() = _currency

    private var isNetworkAvailable:Boolean = false

    init {
        fetchCurrencyInfo()

    }

    private fun fetchCurrencyInfo(){
        _currencyListEvents.value = Navegation.ShowLoading
        viewModelScope.launch {
            try {
                val currencies = repository.getCurrencies(isNetworkAvailable)
                _currencyListEvents.postValue(Navegation.ShowResult(currencies))

            }catch (e:Exception){
                _currencyListEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    private fun saveCurrencyInLocal(){
        viewModelScope.launch {
            repository.saveCurrencyInfo(_currency.value!!)
        }
    }

    private fun fetchTickerAndOrderBookInfo() {
        _currencyEvents.value = Navegation.ShowLoading
        repository.getCurrencyTicker(currency.value!!.book)
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                _currency.value?.ticker = it
                return@flatMap repository.getCurrencyOrderBook(currency.value!!.book)
            }
            .subscribeBy(
                onSuccess = {
                    _currency.value?.orderBook = it
                    _currencyEvents.postValue(Navegation.ShowResult(_currency.value))
                    saveCurrencyInLocal()
                },
                onError = {
                    _currencyEvents.postValue(Navegation.ShowNotFound(it))
                }
            ).addTo(disposable)
    }

    fun setSelectedCurrency(currency: Currency, isNetworkAvailable:Boolean) {
        _currency.value = currency
        fetchTickerAndOrderBookInfo()
        this.isNetworkAvailable = isNetworkAvailable
    }

}