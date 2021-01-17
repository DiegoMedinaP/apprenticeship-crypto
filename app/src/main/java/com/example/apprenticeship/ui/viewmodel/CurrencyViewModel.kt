package com.example.apprenticeship.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.Navegation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel @ViewModelInject constructor(private val repository: CurrencyRepository) :
    BaseViewModel() {

    private val _currencyListEvents = MutableLiveData<Navegation>()
    val currencyListEvents: LiveData<Navegation> get() = _currencyListEvents

    private val _currencyDetailsEvents = MutableLiveData<Navegation>()
    val currencyDetailsEvents: LiveData<Navegation> get() = _currencyDetailsEvents

    private val _currency = MutableLiveData<Currency>()

    init {
        fetchCurrencies()
    }

    fun fetchCurrencies() {
        _currencyListEvents.value = Navegation.ShowLoading
        viewModelScope.launch(Dispatchers.IO){
            try {
                val currencies = repository.getCurrencies()
                _currencyListEvents.postValue(Navegation.ShowResult(currencies))

            } catch (e: Exception) {
                _currencyListEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    fun fetchTickerAndOrderBookInfo() {
        _currencyDetailsEvents.value = Navegation.ShowLoading
        viewModelScope.launch(Dispatchers.IO) {

            try {
                _currency.value?.orderBook = repository.getCurrencyOrderBook(_currency.value!!.book)
                _currency.value?.ticker = repository.getCurrencyTicker(_currency.value!!.book)
                _currencyDetailsEvents.postValue(Navegation.ShowResult(_currency.value))
            } catch (e: Exception) {
                _currencyDetailsEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    fun setSelectedCurrency(currency: Currency) {
        _currencyDetailsEvents.value = Navegation.ShowResult(currency)
        _currency.value = currency
        fetchTickerAndOrderBookInfo()
    }

}