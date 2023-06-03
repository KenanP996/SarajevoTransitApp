package com.example.sarajevotransitapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.sarajevotransitapp.database.entities.stops
import com.example.sarajevotransitapp.database.functions.allstations
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClosestStations : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var googleMap: GoogleMap

    private var stationList: List<Station> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.closest_locations)




        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fetchCurrentLocation()
    }

    private fun fetchCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val userLocation = LatLng(location.latitude, location.longitude)
                findAndDisplayClosestStation(userLocation)
            } ?: run {
                Toast.makeText(
                    this,
                    "Failed to retrieve location. Please make sure your GPS is turned on.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun findAndDisplayClosestStation(userLocation: LatLng) {
        lifecycleScope.launch(Dispatchers.IO) {
            val stopsList: List<stops> = allstations.stoplist()
            stationList = stopsList.map { stop -> Station(stop.stop_name, LatLng(stop.stop_lat, stop.stop_lon)) }

            val closestStation = findClosestStation(userLocation)

            withContext(Dispatchers.Main) {
                if (closestStation != null) {
                    zoomToClosestStation(closestStation)
                    googleMap.addMarker(MarkerOptions().position(closestStation.location).title(closestStation.name))
                } else {
                    Toast.makeText(
                        this@ClosestStations,
                        "No stations found nearby.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun findClosestStation(userLocation: LatLng): Station? {
        var closestStation: Station? = null
        var closestDistance = Float.MAX_VALUE

        for (station in stationList) {
            val stationLocation = station.location
            val distance = FloatArray(1)
            Location.distanceBetween(
                userLocation.latitude, userLocation.longitude,
                stationLocation.latitude, stationLocation.longitude, distance
            )
            if (distance[0] < closestDistance) {
                closestDistance = distance[0]
                closestStation = station
            }
        }

        return closestStation
    }

    private fun zoomToClosestStation(station: Station) {
        val closestStationLocation = station.location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(closestStationLocation, 15f))
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            fetchCurrentLocation()
            return
        }

        googleMap.isMyLocationEnabled = true
    }

    data class Station(val name: String, val location: LatLng)

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }


}
