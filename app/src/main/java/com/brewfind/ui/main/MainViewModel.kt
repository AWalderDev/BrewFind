package com.brewfind.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var breweries: MutableLiveData<ArrayList<Brewery>> = MutableLiveData<ArrayList<Brewery>>()

    fun fetchBreweries(breweryName: String) {

    }
}
