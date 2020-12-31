package com.example.apprenticeship.ui

sealed class Navegation{
    data class ShowResult<out T>(val result:T): Navegation()
    data class ShowNotFound(val error: Throwable) : Navegation()
    object ShowLoading: Navegation()
}
