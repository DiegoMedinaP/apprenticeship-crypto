package com.example.apprenticeship.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.domain.Currency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel @ViewModelInject constructor(private val repository: CurrencyRepository) :BaseViewModel() {


    private val _currencyEvents = MutableLiveData<CurrencyNavegation>()
    val currencyEvents : LiveData<CurrencyNavegation> get() = _currencyEvents

    init {
        fetchCurrencyInfo()
    }


    private fun fetchCurrencyInfo(){
        _currencyEvents.value = CurrencyNavegation.ShowLoading
        repository.getCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
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
        data class ShowCurrencies(val currencies:List<Currency>): CurrencyNavegation()
        data class ShowNotFound(val error: Throwable) : CurrencyNavegation()
        object ShowLoading: CurrencyNavegation()
    }
}