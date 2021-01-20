package com.example.apprenticeship

import com.example.apprenticeship.data.local.CurrencyDao
import com.example.apprenticeship.data.local.LocalCurrencyDataSourceImpl
import com.example.apprenticeship.data.local.entities.CurrencyRoomEntity
import com.example.apprenticeship.domain.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock

class LocalDataSourceTest {

    @Mock
    private lateinit var currencyDao: CurrencyDao

    private lateinit var localDataSource: LocalCurrencyDataSourceImpl

    @Before
    fun setUp() {
        localDataSource = LocalCurrencyDataSourceImpl(currencyDao)
    }

    @Test
    fun `get currencies from local data base`() {

        //GIVEN
        runBlocking {

            BDDMockito.`when`(currencyDao.getAllCurrencies())
                .thenReturn(
                    mockedCurrenciesRoom
                )

            //WHEN
            Assert.assertEquals(
                localDataSource.getCurrencies(),
                mockedCurrenciesRoom.toCurrenciesDomain()
            )
        }
    }

    @Test
    fun `get ticker from local data base`() {

        //GIVEN
        val expectedResult = mockedTicker
        runBlocking {

            BDDMockito.`when`(currencyDao.getTicker("btc_mxn"))
                .thenReturn(
                    mockedTicker.toTickerRoomEntity()
                )

            //WHEN
            Assert.assertEquals(localDataSource.getCurrencyTicker("btc_mxn"), expectedResult)
        }
    }

    @Test
    fun `get orderBook from local data base`() {

        //GIVEN
        val expectedResult = mockedOrderBook
        runBlocking {

            BDDMockito.`when`(currencyDao.getOrderBook("btc_mxn"))
                .thenReturn(
                    expectedResult.toOrderBookRoomEntity("btc_mxn")
                )
            //WHEN
            Assert.assertEquals(localDataSource.getCurrencyOrderBook("btc_mxn"), expectedResult)
        }
    }

    private val mockedCurrenciesRoom = arrayListOf(
        CurrencyRoomEntity("btc", null, null),
        CurrencyRoomEntity("etherum", null, null),
        CurrencyRoomEntity("xrp", null, null),
        CurrencyRoomEntity("mana", null, null),
        CurrencyRoomEntity("golem", null, null),
    )

    private val mockedTicker = Ticker(
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

    private val mockedOrderBook = OrderBook(
        null,
        null,
        arrayListOf(),
        arrayListOf()
    )
}