package com.example.apprenticeship

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.data.mediator.CurrencySourceMediatorInterface
import com.example.apprenticeship.data.repository.CurrencyRepository
import com.example.apprenticeship.data.repository.CurrencyRepositoryImpl
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.OrderBook
import com.example.apprenticeship.domain.Ticker
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.`when`
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @Mock
    private lateinit var mediator: CurrencySourceMediatorInterface

    @Mock
    private lateinit var dataSourceMocked: CurrencyDataSource
    private lateinit var repository: CurrencyRepository

    @Before
    fun setUp() {
        repository = CurrencyRepositoryImpl(mediator)
        `when`(mediator.getDataSourceToUse()).thenReturn(dataSourceMocked)
    }

    @Test
    fun `get currencies from data source given by mediator`() {

        //GIVEN
        val expectedResult = mockedCurrencies
        runBlocking {

            `when`(mediator.getDataSourceToUse()?.getCurrencies())
                .thenReturn(
                    expectedResult
                )

            //WHEN
            assertEquals(repository.getCurrencies().size, 5)
            assertEquals(repository.getCurrencies(), expectedResult)
        }
    }

    @Test
    fun `get ticker from data source given by mediator`() {

        //GIVEN
        val expectedResult = mockedTicker
        runBlocking {

            `when`(mediator.getDataSourceToUse()?.getCurrencyTicker("btc_mxn"))
                .thenReturn(
                    expectedResult
                )

            //WHEN
            assertEquals(repository.getCurrencyTicker("btc_mxn"), expectedResult)
        }
    }

    @Test
    fun `get orderBook from data source given by mediator`() {

        //GIVEN
        val expectedResult = mockedOrderBook
        runBlocking {

            `when`(mediator.getDataSourceToUse()?.getCurrencyOrderBook("btc_mxn"))
                .thenReturn(
                    expectedResult
                )

            //WHEN
            assertEquals(repository.getCurrencyOrderBook("btc_mxn"), expectedResult)
        }
    }


    private val mockedCurrencies = arrayListOf(
        Currency("btc"),
        Currency("etherum"),
        Currency("xrp"),
        Currency("mana"),
        Currency("golem"),
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

