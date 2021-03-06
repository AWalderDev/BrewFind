package com.brewfind.dto

import com.google.gson.annotations.SerializedName

data class Brewery(@SerializedName("name") var name: String,
                   @SerializedName("brewery_type") var size: String,
                   @SerializedName("street") var street: String,
                   @SerializedName("city") var city: String,
                   @SerializedName("state") var state: String){
    override fun toString(): String {
        return "$name $size $street"
    }
}