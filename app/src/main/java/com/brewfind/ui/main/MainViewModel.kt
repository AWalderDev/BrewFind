package com.brewfind.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brewfind.Service.BreweryService
import com.brewfind.dto.Brewery

class MainViewModel : ViewModel() {
    var breweries: MutableLiveData<ArrayList<Brewery>> = MutableLiveData<ArrayList<Brewery>>()
    var breweryService: BreweryService = BreweryService()

    //fetch breweries on initialization
    init {
        fetchBreweries()
    }
    fun fetchBreweries() {
        breweries = breweryService.fetchBreweries()
    }
}
