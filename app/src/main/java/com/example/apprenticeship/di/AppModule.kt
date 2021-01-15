package com.example.apprenticeship.di

import android.content.Context
import androidx.room.Room
import com.example.apprenticeship.data.CurrencySourceMediator
import com.example.apprenticeship.data.local.CurrencyDataBase
import com.example.apprenticeship.data.local.LocalCurrencyDataSourceImpl
import com.example.apprenticeship.data.mediator.LocalCurrencyDataSourceMediator
import com.example.apprenticeship.data.mediator.RemoteCurrencyDataSourceMediator
import com.example.apprenticeship.data.remote.CurrencyService
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSourceImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun currencyProvider(): CurrencyService {
        return Retrofit.Builder()
            .baseUrl("https://api.bitso.com/v3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(CurrencyService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, CurrencyDataBase::class.java, "CurrencyDB").build()

    @Singleton
    @Provides
    fun provideCurrencyDao(db:CurrencyDataBase) = db.currencyDao()

    @Singleton
    @Provides
    fun providesMediatorInstance(remote: RemoteCurrencyDataSourceImpl, local: LocalCurrencyDataSourceImpl) = CurrencySourceMediator(
        listOf(LocalCurrencyDataSourceMediator(local), RemoteCurrencyDataSourceMediator(remote))
    )

}