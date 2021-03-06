package com.brewfind

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.brewfind.ui.main.MainViewModel
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BreweryDataUnitTest {

    @get:Rule
    var rule:TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun searchForStreetside_returnsStreetside() {
        givenAFeedOfBreweryDataAreAvailable()
        whenSearchForStreetside()
        thenResultContainsStreetside()
    }

    private fun givenAFeedOfBreweryDataAreAvailable() {
        mvm = MainViewModel()
    }

    private fun whenSearchForStreetside() {
        mvm.fetchBreweriesByName("Streetside")
    }

    private fun thenResultContainsStreetside() {
        var streetsideFound = false;
        mvm.breweries.observeForever{
            //here is where we do the observing
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach {
                if (it.name == "Streetside Brewery" && it.size == "micro" && it.street == "4003 Eastern Ave") {
                    streetsideFound = true
                }
            }
        }
        assertTrue(streetsideFound)
    }

    fun searchForGarbage_returnsNothing() {
        givenAFeedOfBreweryDataAreAvailable()
        whenISearchForGarbage()
        thenIGetZeroResults()
    }

    private fun whenISearchForGarbage() {
        mvm.fetchBreweriesByName("huahhudnwjnajkbqwe")
    }

    private fun thenIGetZeroResults() {
        mvm.breweries.observeForever {
            assertEquals(0, it.size)
        }
    }


}
