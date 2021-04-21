package com.brewfind.dao

import com.brewfind.dto.Brewery
import  retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * BreweryDAO interface for restFul request
 */
interface BreweryDAO {
    /**
     * get Breweries By page
     */
    @GET("/breweries")
    fun getBreweriesBypage(@Query("page") page: Int): Call<ArrayList<Brewery>>

    /**
     *  get Breweries By city
     */
    @GET("/breweries")
    fun getBreweriesByCity(@Query("by_city") city: String,@Query("page") page: Int): Call<ArrayList<Brewery>>

    /**
     * get Breweries By State
     */
    @GET("/breweries")
    fun getBreweriesByState(@Query("by_state") state: String,@Query("page") page: Int): Call<ArrayList<Brewery>>

    /**
     * get Breweries By name
     */
    @GET("/breweries")
    fun getBreweriesByName(@Query("by_name") name: String,@Query("page") page: Int): Call<ArrayList<Brewery>>

}