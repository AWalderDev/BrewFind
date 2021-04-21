package com.brewfind.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.brewfind.Service.BreweryService
import com.brewfind.dto.Brewery
import com.brewfind.dto.MyDatabaseHelper

/**
 * View Model for Brewery view
 */
class MainViewModel : ViewModel() {
    var breweries: MutableLiveData<ArrayList<Brewery>> = MutableLiveData()
    var breweryService: BreweryService = BreweryService()

    init {
        breweries.value = ArrayList()
    }

    /**
     * get all Breweries from page 1 to Page..n
     */
    fun fetchBreweries(owner: LifecycleOwner, page: Int) {
        if (page == 1) {
            breweries.value!!.clear()
        }
        var temp: MutableLiveData<ArrayList<Brewery>> = breweryService.fetchBreweries(page)

        temp.observe(owner, Observer { list ->
            var newList: ArrayList<Brewery> = ArrayList()
            newList.addAll(breweries.value!!)
            newList.addAll(list)
            breweries.value = newList
        })
    }

    /**
     * get all Breweries in the specific state from page 1 to Page..n
     */
    fun fetchBreweriesByState(owner: LifecycleOwner, state: String, page: Int) {
        if (page == 1) {
            breweries.value!!.clear()
        }
        var temp: MutableLiveData<ArrayList<Brewery>> =
            breweryService.fetchBreweriesByState(state, page)
        temp.observe(owner, Observer { list ->
            var newList: ArrayList<Brewery> = ArrayList()
            newList.addAll(breweries.value!!)
            newList.addAll(list)
            breweries.value = newList
        })
    }

    /**
     * get all Breweries in the specific city from page 1 to Page..n
     */
    fun fetchBreweriesByCity(owner: LifecycleOwner, city: String, page: Int) {
        if (page == 1) {
            breweries.value!!.clear()
        }
        var temp: MutableLiveData<ArrayList<Brewery>> =
            breweryService.fetchBreweriesByCity(city, page)
        temp.observe(owner, Observer { list ->
            var newList: ArrayList<Brewery> = ArrayList()
            newList.addAll(breweries.value!!)
            newList.addAll(list)
            breweries.value = newList
        })
    }

    /**
     * get Visited Breweries From database
     */
    fun getVisitedFromDB(context: Context) {
        val dbHelper = MyDatabaseHelper(context!!, "Brewery.db", 1)

        val db = dbHelper.writableDatabase
        val cursor = db.query("Brewery", null, null, null, null, null, null)
        var list: ArrayList<Brewery> = ArrayList<Brewery>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val size = cursor.getString(cursor.getColumnIndex("size"))
                val street = cursor.getString(cursor.getColumnIndex("street"))
                val city = cursor.getString(cursor.getColumnIndex("city"))
                val state = cursor.getString(cursor.getColumnIndex("state"))
                val postalCode = cursor.getString(cursor.getColumnIndex("postalCode"))
                val country = cursor.getString(cursor.getColumnIndex("country"))
                val longitude = cursor.getString(cursor.getColumnIndex("longitude"))
                val latitude = cursor.getString(cursor.getColumnIndex("latitude"))
                val phone = cursor.getString(cursor.getColumnIndex("phone"))
                val website = cursor.getString(cursor.getColumnIndex("website"))
                val distance = cursor.getString(cursor.getColumnIndex("phone"))

                list.add(
                    Brewery(
                        id ?: null.toString(),
                        name ?: null.toString(),
                        size ?: null.toString(),
                        street ?: null.toString(),
                        city ?: null.toString(),
                        state ?: null.toString(),
                        postalCode ?: null.toString(),
                        country ?: null.toString(),
                        longitude ?: null.toString(),
                        latitude ?: null.toString(),
                        phone ?: null.toString(),
                        website ?: null.toString(),
                        distance ?: null.toString()
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        breweries.value = list
    }
}
