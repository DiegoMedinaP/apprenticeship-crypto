package com.example.apprenticeship.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.Navegation
import kotlinx.coroutines.launch

class CurrencyViewModel @ViewModelInject constructor(private val repository: CurrencyRepository) :
    BaseViewModel() {

    private val _currencyListEvents = MutableLiveData<Navegation>()
    val currencyListEvents: LiveData<Navegation> get() = _currencyListEvents

    private val _currencyDetailsEvents = MutableLiveData<Navegation>()
    val currencyDetailsEvents: LiveData<Navegation> get() = _currencyDetailsEvents

    init {
        fetchCurrencies()
    }

    private fun fetchCurrencies() {
        _currencyListEvents.value = Navegation.ShowLoading
        viewModelScope.launch {
            try {
                val currencies = repository.getCurrencies()
                _currencyListEvents.postValue(Navegation.ShowResult(currencies))

            } catch (e: Exception) {
                _currencyListEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    private fun fetchTickerAndOrderBookInfo(currency: Currency) {
        _currencyDetailsEvents.value = Navegation.ShowLoading
        viewModelScope.launch {
            try {
                currency.orderBook = repository.getCurrencyOrderBook(currency.book)
                currency.ticker = repository.getCurrencyTicker(currency.book)
                _currencyDetailsEvents.value = Navegation.ShowResult(currency)
            } catch (e: Exception) {
                _currencyDetailsEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    fun setSelectedCurrency(currency: Currency) {
        _currencyDetailsEvents.value = Navegation.ShowResult(currency)
        fetchTickerAndOrderBookInfo(currency)
    }

}