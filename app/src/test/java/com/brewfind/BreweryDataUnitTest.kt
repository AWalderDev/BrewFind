package com.brewfind

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.brewfind.ui.main.MainFragment
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
    fun searchForBreweriesPage1_returns20() {
        givenAFeedOfBreweryDataAreAvailable()
        whenFetchBreweries_page1()
        thenResultContains20()
    }

    private fun givenAFeedOfBreweryDataAreAvailable() {
        mvm = MainViewModel()
    }

    private fun whenFetchBreweries_page1() {
        mvm.fetchBreweries(MainFragment.newInstance(),1)
    }

    private fun thenResultContains20() {
        mvm.breweries.observe(MainFragment.newInstance(), Observer{
            assertNotNull(it)
            assertEquals(20, it.size)
        })
    }

    @Test
    fun searchForCityKnox_returnsKnox() {
        givenAFeedOfBreweryDataAreAvailable()
        whenISearchForKnox()
        thenIGetKnoxResults()
    }

    private fun whenISearchForKnox() {
        mvm.fetchBreweriesByCity(MainFragment.newInstance(),"Knox",1)
    }

    private fun thenIGetKnoxResults() {
        mvm.breweries.observe(MainFragment.newInstance(), Observer{it->
            assertNotNull(it)
            assertTrue(it.size>1)
            for (s in it)
            {
                assertEquals(true, s.city.contains("Knox"))
            }
        })
    }

    @Test
    fun searchForStateOregon_returnsOregon() {
        givenAFeedOfBreweryDataAreAvailable()
        whenISearchForOregon()
        thenIGetOregonResults()
    }

    private fun whenISearchForOregon() {
        mvm.fetchBreweriesByState(MainFragment.newInstance(),"Oregon",1)
    }

    private fun thenIGetOregonResults() {
        mvm.breweries.observe(MainFragment.newInstance(), Observer{it->
            assertNotNull(it)
            assertTrue(it.size>1)
            for (s in it)
            {
                assertEquals(true, s.city.contains("Oregon"))
            }
        })
    }

    @Test
    fun searchFor2Pages_returns40() {
        givenAFeedOfBreweryDataAreAvailable()
        whenISearchForpage1()
        whenISearchForpage2()
        thenIGetResults40()
    }

    private fun whenISearchForpage1() {
        mvm.fetchBreweriesByState(MainFragment.newInstance(),"Oregon",1)
    }
    private fun whenISearchForpage2() {
        mvm.fetchBreweriesByState(MainFragment.newInstance(),"Oregon",2)
    }

    private fun thenIGetResults40() {
        mvm.breweries.observe(MainFragment.newInstance(), Observer{it->
            assertNotNull(it)
            assertTrue(it.size==40)
        })
    }

    @Test
    fun searchForGarbage_returnsNothing() {
        givenAFeedOfBreweryDataAreAvailable()
        whenISearchForGarbage()
        thenIGetZeroResults()
    }

    private fun whenISearchForGarbage() {
        mvm.fetchBreweriesByState(MainFragment.newInstance(),"dasdasda",1)
    }

    private fun thenIGetZeroResults() {
        mvm.breweries.observeForever {
            assertEquals(0, it.size)
        }
    }


}
