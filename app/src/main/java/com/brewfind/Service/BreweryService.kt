package com.brewfind.Service

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brewfind.MainActivity
import com.brewfind.RetrofitClientInstance
import com.brewfind.dao.BreweryDAO
import com.brewfind.dto.Brewery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreweryService {

    internal fun fetchBreweries(page: Int): MutableLiveData<ArrayList<Brewery>> {
        var breweries = MutableLiveData<ArrayList<Brewery>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(BreweryDAO::class.java)
        val call = service?.getBreweriesBypage(page)

        call?.enqueue(object : Callback<ArrayList<Brewery>> {

            override fun onResponse(
                call: Call<ArrayList<Brewery>>,
                response: Response<ArrayList<Brewery>>
            ) {
                var result = floatArrayOf(1F)
                if (response.body() != null && response.body()!!.size > 0) {
                    for (s in response.body()!!) {
                        if (s.latitude == null || s.longitude == null) {
                            s.distance = "Unknown"
                            continue
                        }
                        Location.distanceBetween(
                            MainActivity.lat,
                            MainActivity.lon,
                            s.latitude.toDouble(),
                            s.longitude.toDouble(),
                            result
                        )
                        s.distance = "" + (result[0] / 1000).toInt() + "Km"
                    }
                }
                breweries.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Brewery>>, t: Throwable) {
                Log.println(Log.ERROR, "rest", "rest error" + t.localizedMessage);
            }

        })

        return breweries
    }

    internal fun fetchBreweriesByCity(
        chosenCity: String,
        page: Int
    ): MutableLiveData<ArrayList<Brewery>> {
        var breweries = MutableLiveData<ArrayList<Brewery>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(BreweryDAO::class.java)
        val call = service?.getBreweriesByCity(chosenCity, page)

        call?.enqueue(object : Callback<ArrayList<Brewery>> {

            override fun onResponse(
                call: Call<ArrayList<Brewery>>,
                response: Response<ArrayList<Brewery>>
            ) {
                var result = floatArrayOf(1F)
                if (response.body() != null && response.body()!!.size > 0) {
                    for (s in response.body()!!) {
                        // Log.println(Log.ERROR, "ss", s.toString());
                        if (s.latitude == null || s.longitude == null) {
                            s.distance = "Unknown"
                            continue
                        }
                        Location.distanceBetween(
                            MainActivity.lat,
                            MainActivity.lon,
                            s.latitude.toDouble(),
                            s.longitude.toDouble(),
                            result
                        )
                        s.distance = "" + (result[0] / 1000).toInt() + "Km"
                        Log.println(Log.ERROR, "ss", s.toString());
                    }
                }
                breweries.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Brewery>>, t: Throwable) {
                Log.println(Log.ERROR, "rest", "rest error" + t.localizedMessage);
            }

        })

        return breweries
    }

    internal fun fetchBreweriesByState(chosenState: String,page :Int): MutableLiveData<ArrayList<Brewery>> {
        var breweries = MutableLiveData<ArrayList<Brewery>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(BreweryDAO::class.java)
        val call = service?.getBreweriesByState(chosenState,page)

        call?.enqueue(object : Callback<ArrayList<Brewery>> {

            override fun onResponse(
                call: Call<ArrayList<Brewery>>,
                response: Response<ArrayList<Brewery>>
            ) {
                var result = floatArrayOf(1F)
                if (response.body() != null && response.body()!!.size > 0) {
                    for (s in response.body()!!) {
                        if (s.latitude == null || s.longitude == null) {
                            s.distance = "Unknown"
                            continue
                        }
                        Location.distanceBetween(
                            MainActivity.lat,
                            MainActivity.lon,
                            s.latitude.toDouble(),
                            s.longitude.toDouble(),
                            result
                        )
                        s.distance = "" + (result[0] / 1000).toInt() + "Km"
                        Log.println(Log.ERROR, "ss", s.toString());
                    }
                }
                breweries.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Brewery>>, t: Throwable) {
                Log.println(Log.ERROR, "rest", "rest error" + t.localizedMessage);
            }

        })

        return breweries
    }


    fun fetchBreweriesByName(name: String,page :Int): MutableLiveData<ArrayList<Brewery>> {
        val breweries = MutableLiveData<ArrayList<Brewery>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(BreweryDAO::class.java)
        val call = service?.getBreweriesByName(name,page);
        
        call?.enqueue(object : Callback<ArrayList<Brewery>> {

            override fun onResponse(
                call: Call<ArrayList<Brewery>>,
                response: Response<ArrayList<Brewery>>
            ) {
                var result = floatArrayOf(1F)
                if (response.body() != null && response.body()!!.size > 0) {
                    for (s in response.body()!!) {
                        // Log.println(Log.ERROR, "ss", s.toString());
                        if (s.latitude == null || s.longitude == null||"null".equals(s.latitude)||"null".equals(s.longitude)) {
                            s.distance = "Unknown"
                            continue
                        }
                        Location.distanceBetween(
                            MainActivity.lat,
                            MainActivity.lon,
                            s.latitude.toDouble(),
                            s.longitude.toDouble(),
                            result
                        )
                        s.distance = "" + (result[0] / 1000).toInt() + "Km"
                        Log.println(Log.ERROR, "ss", s.toString());
                    }
                }
                breweries.value = response.body()
                Log.println(Log.ERROR, "ss", breweries.value?.size.toString());
            }

            override fun onFailure(call: Call<ArrayList<Brewery>>, t: Throwable) {
                Log.println(Log.ERROR, "rest", "rest error" + t.localizedMessage);
            }

        })

        return breweries
    }

}