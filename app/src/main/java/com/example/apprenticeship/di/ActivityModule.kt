package com.example.apprenticeship.di

import com.example.apprenticeship.LocalCurrencyDataSourceImpl
import com.example.apprenticeship.data.LocalCurrencyDataSource
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSource
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSourceImpl
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.data.repository.CurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindCurrencyRepoImpl(repoImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    abstract fun bindRemoteCurrencyDataSourceImpl(dataImpl: RemoteCurrencyDataSourceImpl): RemoteCurrencyDataSource

    //@Binds
    //abstract fun bindLocalCurrencyDataSourceImpl(dataImpl: LocalCurrencyDataSourceImpl): LocalCurrencyDataSource
}