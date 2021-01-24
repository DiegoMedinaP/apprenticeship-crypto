package com.example.apprenticeship

import com.example.apprenticeship.data.local.LocalCurrencyDataSource
import com.example.apprenticeship.data.remote.RemoteCurrencyDataSourceImpl
import com.example.apprenticeship.data.remote.entities.CurrencyEntity
import com.example.apprenticeship.data.remote.entities.CurrencyInfoEntity
import com.example.apprenticeship.data.remote.entities.OrderBookEntity
import com.example.apprenticeship.data.remote.entities.OrderBookInfoEntity
import com.example.apprenticeship.data.remote.entities.TickerEntity
import com.example.apprenticeship.data.remote.entities.TickerInfoEntity
import com.example.apprenticeship.data.remote.service.CurrencyService
import com.example.apprenticeship.domain.toCurrencyListDomain
import com.example.apprenticeship.domain.toOrderBookDomain
import com.example.apprenticeship.domain.toTickerDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    @Mock
    private lateinit var service: CurrencyService

    @Mock
    private lateinit var localDataSoruce: LocalCurrencyDataSource

    private lateinit var remoteDataSouce: RemoteCurrencyDataSourceImpl

    @Before
    fun setUp() {
        remoteDataSouce = RemoteCurrencyDataSourceImpl(service, localDataSoruce)
    }

    @Test
    fun `get currencies from remote data base`() {

        // GIVEN
        runBlocking {

            `when`(service.getAvailableBooks())
                .thenReturn(
                    mockedCurrencies
                )

            // WHEN
            assertEquals(
                remoteDataSouce.getCurrencies(),
                mockedCurrencies.toCurrencyListDomain()
            )
        }
    }

    @Test
    fun `get ticker from remote data base`() {

        // GIVEN
        val expectedResult = mockedTicker.toTickerDomain()
        runBlocking {

            `when`(service.getCurrencyTicker("btc_mxn"))
                .thenReturn(
                    mockedTicker
                )

            // WHEN
            assertEquals(remoteDataSouce.getCurrencyTicker("btc_mxn"), expectedResult)
        }
    }

    @Test
    fun `get orderBook from remote data base`() {

        // GIVEN
        val expectedResult = mockedOrderBook.toOrderBookDomain()
        runBlocking {

            `when`(service.getOrderBook("btc_mxn"))
                .thenReturn(
                    mockedOrderBook
                )
            // WHEN
            assertEquals(remoteDataSouce.getCurrencyOrderBook("btc_mxn"), expectedResult)
        }
    }

    private val mockedCurrencies = CurrencyEntity(
        true, arrayListOf(
            CurrencyInfoEntity("btc_mxn"),
            CurrencyInfoEntity("etherum_mxn"),
            CurrencyInfoEntity("xrp_mxn"),
            CurrencyInfoEntity("mana_mxn"),
            CurrencyInfoEntity("golem_mxn")
        )
    )

    private val mockedTicker = TickerEntity(
        true,
        TickerInfoEntity(
            "5.05",
            "6",
            null,
            "btc_mxn",
            null,
            null,
            null,
            null,
            null,
            null
        )
    )

    private val mockedOrderBook = OrderBookEntity(
        true,
        OrderBookInfoEntity(
            null,
            null,
            arrayListOf(),
            arrayListOf()
        )
    )
}
