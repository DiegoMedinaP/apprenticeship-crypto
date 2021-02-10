package com.example.apprenticeship.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.Ohlc
import com.example.apprenticeship.ui.Navegation
import com.example.apprenticeship.utils.convertStringFormatIntoUnixTime
import com.example.apprenticeship.utils.convertUnixTimeIntoStringFormat
import kotlinx.coroutines.launch

class CurrencyViewModel @ViewModelInject constructor(private val repository: CurrencyRepository) :
    BaseViewModel() {

    private val _currencyListEvents = MutableLiveData<Navegation>()
    val currencyListEvents: LiveData<Navegation> get() = _currencyListEvents

    private val _currencyDetailsEvents = MutableLiveData<Navegation>()
    val currencyDetailsEvents: LiveData<Navegation> get() = _currencyDetailsEvents

    private val _currencyChartPointsEvents = MutableLiveData<Navegation>()
    val currencyChartPointsEvents: LiveData<Navegation> get() = _currencyChartPointsEvents

    private val _currency = MutableLiveData<Currency>()

    private val _chartPoints = MutableLiveData<List<Ohlc>>()
    private val _chartPointSelectedDate = MutableLiveData<String>()
    val chartPointSelectedDate: LiveData<String> get() = _chartPointSelectedDate


    init {
        fetchCurrencies()
    }

    fun fetchCurrencies() {
        _currencyListEvents.value = Navegation.ShowLoading
        viewModelScope.launch {
            try {
                val currencies = repository.getCurrencies()
                _currencyListEvents.value = Navegation.ShowResult(currencies)
            } catch (e: Exception) {
                _currencyListEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    fun fetchTickerAndOrderBookInfo() {
        _currencyDetailsEvents.value = Navegation.ShowLoading
        viewModelScope.launch {
            try {
                _currency.value?.orderBook = repository.getCurrencyOrderBook(_currency.value!!.book)
                _currency.value?.ticker = repository.getCurrencyTicker(_currency.value!!.book)
                _currencyDetailsEvents.postValue(Navegation.ShowResult(_currency.value))
                fetchChartPoints()
            } catch (e: Exception) {
                _currencyDetailsEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    private fun fetchChartPoints() {
        _currencyChartPointsEvents.value = Navegation.ShowLoading
        viewModelScope.launch {
            try {
                val startChartPoint =
                    (convertStringFormatIntoUnixTime(_currency.value?.ticker?.created_at).toFloat() - 2592000000).toString()
                val endChartPoint =
                    convertStringFormatIntoUnixTime(_currency.value?.ticker?.created_at)

                _chartPoints.value = repository.getOhlcPoints(
                    _currency.value!!.book,
                    "14400",
                    startChartPoint,
                    endChartPoint
                )
                _currencyChartPointsEvents.postValue(Navegation.ShowResult(_chartPoints.value))
            } catch (e: Exception) {
                _currencyChartPointsEvents.postValue(Navegation.ShowNotFound(e))
            }
        }
    }

    fun setSelectedCurrency(currency: Currency) {
        _currencyDetailsEvents.value = Navegation.ShowResult(currency)
        _currency.value = currency
        fetchTickerAndOrderBookInfo()
    }

    fun setCurrentPoint(position: Float) {
        _chartPointSelectedDate.value = convertUnixTimeIntoStringFormat(
            _chartPoints.value?.get(
                position.toInt() ?: 0
            )?.timeInUnix ?: ""
        )
    }
}
