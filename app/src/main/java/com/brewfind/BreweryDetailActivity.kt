package com.brewfind

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat.EXTRA_PEOPLE
import com.brewfind.dto.Brewery
import com.brewfind.dto.MyDatabaseHelper
import kotlinx.android.synthetic.main.brewery_detail_activity.*

class BreweryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.brewery_detail_activity)

        val brewery = intent.getSerializableExtra(EXTRA_PEOPLE) as? Brewery

        id_detail.text = "ID:" + brewery?.id
        breweryName_detail.text = brewery?.name
        address_detail.text = "Address: " + brewery?.street
        city_detail.text = "City: " + brewery?.city
        state_detail.text = "State: " + brewery?.state
        distance_detail.text = "Distace: " + brewery?.distance
        size_detail.text = "Type: " + brewery?.size
        postalCode_detail.text = "Postal Code: " + brewery?.postalCode
        country_detail.text = "Country: " + brewery?.country
        phone_detail.text = "Phone: " + brewery?.phone
        website_detail.text = "Website: " + brewery?.website
        longitude_detail.text = "Longitude: " + brewery?.longitude
        latitude_detail.text = "Latitude: " + brewery?.latitude

        btnVisited.setOnClickListener {
            val dbHelper = MyDatabaseHelper(this, "Brewery.db", 1)
            val db = dbHelper.writableDatabase
            val cursor = db.query("Brewery", null, "id="+brewery?.id,  null, null, null, null)
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "Already added!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            cursor.close()
            val Values1 = ContentValues().apply {
                put("id", brewery?.id)
                put("name", brewery?.name)
                put("street", brewery?.street)
                put("city", brewery?.city)
                put("state", brewery?.state)
                put("distance", brewery?.distance)
                put("size", brewery?.size)
                put("postalCode", brewery?.postalCode)
                put("country", brewery?.country)
                put("phone", brewery?.phone)
                put("website", brewery?.website)
                put("longitude", brewery?.longitude)
                put("latitude", brewery?.latitude)
            }
            db.insert("Brewery", null, Values1)
            Toast.makeText(this, "Add Successfully!", Toast.LENGTH_LONG).show()
        }

    }


}
