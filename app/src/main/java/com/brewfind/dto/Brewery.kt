package com.brewfind.dto

import com.google.gson.annotations.SerializedName

data class Brewery(@SerializedName("Name") var name: String, @SerializedName("brewery_type") var size: String, @SerializedName("street") var street: String) {
    override fun toString(): String {
        return "$name $size $street"
    }
}