package com.brewfind.dao

import com.brewfind.dto.Brewery
import  retrofit2.Call
import retrofit2.http.GET

interface BreweryDAO {
    @GET("/breweries")
    fun getAllBreweries(): Call<ArrayList<Brewery>>
}