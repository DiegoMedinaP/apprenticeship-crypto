package com.example.apprenticeship

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.MainActivity
import com.example.apprenticeship.ui.adapters.CurrencyAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class CurrencyNavigationTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val mockedCurrencies = arrayListOf(
        Currency("btc"),
        Currency("etherum"),
        Currency("xrp"),
        Currency("mana"),
        Currency("golem"),
    )

    val LIST_ITEM_IN_TEST = 0
    val CURRENCY_IN_TEST = mockedCurrencies[0]

    @Test
    fun test_isRecyclerFragmentVisible() {
        onView(withId(R.id.rvCurrency)).check(matches(isDisplayed()))
    }

    @Test
    fun testCurrencyFragmentsNavigation() {

        onView(withId(R.id.rvCurrency)).perform(
            actionOnItemAtPosition<CurrencyAdapter.ViewHolder>(
                LIST_ITEM_IN_TEST,
                click()
            )
        )

        onView(withId(R.id.tvCurrencyName)).check(matches(withText(CURRENCY_IN_TEST.book)))

    }


}