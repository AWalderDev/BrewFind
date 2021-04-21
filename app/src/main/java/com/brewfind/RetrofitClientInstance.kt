package com.brewfind

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    //declare variables for retrofit instance and base url for json endpoint
    private var retrofit: Retrofit? = null
    private const val base_url = "https://api.openbrewerydb.org/"

    //build retrofit client instance
    val retrofitInstance : Retrofit?
        get() {
            //has this object been created yet?
            if (retrofit == null) {
                //create it
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}