package com.brewfind.Service

import androidx.lifecycle.MutableLiveData
import com.brewfind.RetrofitClientInstance
import com.brewfind.dao.BreweryDAO
import com.brewfind.dto.Brewery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreweryService {
    internal fun fetchBreweries() : MutableLiveData<ArrayList<Brewery>> {
        var _breweries = MutableLiveData<ArrayList<Brewery>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(BreweryDAO::class.java)
        val call = service?.getAllBreweries()

        call?.enqueue(object: Callback<ArrayList<Brewery>> {

            override fun onResponse(
                call: Call<ArrayList<Brewery>>,
                response: Response<ArrayList<Brewery>>
            ) {
                _breweries.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Brewery>>, t: Throwable) {

            }

        })

        return _breweries
    }

    fun fetchBreweriesByArea(chosenStreet: String) : MutableLiveData<ArrayList<Brewery>> {
        var _breweries = MutableLiveData<ArrayList<Brewery>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(BreweryDAO::class.java)
        val call = service?.getAllBreweries()
        //TODO Pass chosenStreet to API call
        call?.enqueue(object: Callback<ArrayList<Brewery>> {

            override fun onResponse(
                call: Call<ArrayList<Brewery>>,
                response: Response<ArrayList<Brewery>>
            ) {
                _breweries.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Brewery>>, t: Throwable) {

            }

        })

        return _breweries
    }

}