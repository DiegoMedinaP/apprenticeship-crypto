package com.example.apprenticeship.di

import android.content.Context
import androidx.room.Room
import com.example.apprenticeship.data.local.CurrencyDataBase
import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.data.local.LocalCurrencyDataSourceImpl
import com.example.apprenticeship.data.local.LocalSaveCurrencyImpl
import com.example.apprenticeship.data.mediator.CurrencySourceMediator
import com.example.apprenticeship.data.mediator.LocalCurrencyDataSourceMediator
import com.example.apprenticeship.data.mediator.RemoteCurrencyDataSourceMediator
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSourceImpl
import com.example.apprenticeship.data.remote.service.CurrencyService
import com.example.apprenticeship.data.remote.service.UserAgentInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun httpLogingInterceptorProvider(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    @Provides
    @Singleton
    fun userAgentInterceptor(): UserAgentInterceptor = UserAgentInterceptor()

    @Provides
    @Singleton
    fun okHttpClientProvider(httpInterceptor: HttpLoggingInterceptor, userAgentInterceptor: UserAgentInterceptor) =
        OkHttpClient.Builder()
        .addInterceptor(httpInterceptor)
        .addInterceptor(userAgentInterceptor)
        .build()

    @Provides
    @Singleton
    fun currencyProvider(okHttpClient: OkHttpClient): CurrencyService {
        return Retrofit.Builder()
            .baseUrl("https://api.bitso.com/v3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build().create(CurrencyService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, CurrencyDataBase::class.java, "CurrencyDB").build()

    @Singleton
    @Provides
    fun provideCurrencyDao(db: CurrencyDataBase) = db.currencyDao()

    @Singleton
    @Provides
    fun providesMediatorInstance(remote: RemoteCurrencyDataSourceImpl, local: LocalCurrencyDataSourceImpl) = CurrencySourceMediator(
        listOf(LocalCurrencyDataSourceMediator(local), RemoteCurrencyDataSourceMediator(remote))
    )

    @Singleton
    @Provides
    fun localSaveCurrency(db: CurrencyDataBase): LocalCurrencyDataSource = LocalSaveCurrencyImpl(db.currencyDao())
}
