package com.brewfind.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 */
data class Brewery (
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
    @SerializedName("website_url") var website: String,
     var distance: String
    ) :Serializable {

    /**
     *
     */
    override fun toString(): String {
        return "Brewery(id='$id', name='$name', size='$size', street='$street', city='$city', state='$state', postalCode='$postalCode', country='$country', longitude='$longitude', latitude='$latitude', phone='$phone', website='$website', distance='$distance')"
    }


}