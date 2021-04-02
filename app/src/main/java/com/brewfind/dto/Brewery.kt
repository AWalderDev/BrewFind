package com.brewfind.dto

import com.google.gson.annotations.SerializedName

data class Brewery(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("brewery_type") var size: String,
    @SerializedName("street") var street: String,
    @SerializedName("city") var city: String,
    @SerializedName("state") var state: String,
    @SerializedName("postal_code") var postalCode: String,
    @SerializedName("country") var country: String,
    @SerializedName("longitude") var longitude: String,
    @SerializedName("latitude") var latitude: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("website_url") var website: String
    ) {
    override fun toString(): String {
        return "$name $size $street $city $state $postalCode $website"
    }
}