package com.example.apprenticeship

import com.example.apprenticeship.data.CurrencyDataSource
import com.example.apprenticeship.data.mediator.CurrencySourceMediator
import com.example.apprenticeship.data.mediator.CurrencySourceMediatorInterface
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MediatorTest {

    @Mock
    private lateinit var dataSourceHandler1Mocked: CurrencySourceMediatorInterface
    @Mock
    private lateinit var dataSourceHandler2Mocked: CurrencySourceMediatorInterface
    @Mock
    private lateinit var dataSourceHandler3Mocked: CurrencySourceMediatorInterface
    @Mock
    private lateinit var dataSourceMocked: CurrencyDataSource

    private lateinit var mediator: CurrencySourceMediator

    @Before
    fun setUp() {
        mediator = CurrencySourceMediator(
            arrayListOf(
                dataSourceHandler1Mocked,
                dataSourceHandler2Mocked,
                dataSourceHandler3Mocked
            )
        )
    }

    @Test
    fun `return dataSourceHandler1 to use`() {
        `when`(dataSourceHandler2Mocked.getDataSourceToUse())
            .thenReturn(null)

        `when`(dataSourceHandler3Mocked.getDataSourceToUse())
            .thenReturn(null)

        `when`(dataSourceHandler1Mocked.getDataSourceToUse())
            .thenReturn(dataSourceMocked)

        assertEquals(mediator.getDataSourceToUse(), dataSourceMocked)
    }

    @Test
    fun `return dataSourceHandler2 to use`() {
        `when`(dataSourceHandler2Mocked.getDataSourceToUse())
            .thenReturn(dataSourceMocked)

        assertEquals(mediator.getDataSourceToUse(), dataSourceMocked)
    }

    @Test
    fun `return dataSourceHandler3 to use`() {
        `when`(dataSourceHandler1Mocked.getDataSourceToUse())
            .thenReturn(dataSourceMocked)

        assertEquals(mediator.getDataSourceToUse(), dataSourceMocked)
    }
}
