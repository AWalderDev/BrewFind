package com.brewfind

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.brewfind.dto.MyDatabaseHelper
import com.brewfind.ui.main.MainFragment
import com.brewfind.ui.main.UserSettingFragment
import com.brewfind.ui.main.VisitedFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    // define a LocationManager
    private var locationManager : LocationManager? = null

    companion object {
        @JvmField
        var lon: Double = 0.0
        @JvmField
        var lat: Double = 0.0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyDatabaseHelper(this,"Brewery.db",1)
        setContentView(R.layout.main_activity)

        getLocationPermission(this)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
        } catch(ex: SecurityException) {
            Log.println(Log.ERROR,"myTag", "Security Exception, no location available")
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        btnSearchPage.setOnClickListener{
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
            }
        }

        btnMenuPage.setOnClickListener{
            if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, VisitedFragment.newInstance())
                    .commitNow()
            }
        }

        btnManagePage.setOnClickListener{
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UserSettingFragment.newInstance())
                    .commitNow()
            }
        }

    }

    /**
     *
     */
    private fun getLocationPermission(context: Context) {
        val locMan = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val checkCameraPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val checkCallPhonePermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED || checkCameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                2
            )
        }
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            lon = location.longitude
            lat = location.latitude
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

}
