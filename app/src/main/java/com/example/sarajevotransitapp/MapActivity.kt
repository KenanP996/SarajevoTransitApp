package com.example.sarajevotransitapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.example.sarajevotransitapp.database.entities.stops
import com.example.sarajevotransitapp.database.functions.routestops
import com.example.sarajevotransitapp.database.functions.allstations
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap
    private var filteredStops = listOf<stops>()

    companion object {
        private const val DEFAULT_ZOOM = 15f
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val routeId = intent?.getIntExtra("routeId", -1)

        if (routeId != null && routeId != -1) {
            val routeStops = routestops.routestops().filter { it.route_id == routeId }

            val stopIdsInRoute = routeStops.map { it.stop_id }

            filteredStops = allstations.stoplist().filter { it.stop_id in stopIdsInRoute }

            val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        // Add markers for the stops on the map
        for (stop in filteredStops) {
            val stopName = stop.stop_name
            val latitude = stop.stop_lat
            val longitude = stop.stop_lon
            val location = LatLng(latitude, longitude)
            map.addMarker(MarkerOptions().position(location).title(stopName))
        }

        // Move the camera to the first stop if available
        if (filteredStops.isNotEmpty()) {
            val firstStop = filteredStops.first()
            val firstLocation = LatLng(firstStop.stop_lat, firstStop.stop_lon)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, DEFAULT_ZOOM))
        }

        // Check location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // Enable the current location button and set its click listener
            map.isMyLocationEnabled = true
            map.setOnMyLocationButtonClickListener {
                // Get the user's current location and move the camera there
                getCurrentLocation()
                true
            }
            getCurrentLocation()
        } else {
            // Request location permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)

                    val closestStop = filteredStops.minByOrNull { stop ->
                        val stopLatLng = LatLng(stop.stop_lat, stop.stop_lon)
                        val results = FloatArray(1)
                        Location.distanceBetween(
                            currentLatLng.latitude, currentLatLng.longitude,
                            stopLatLng.latitude, stopLatLng.longitude, results
                        )
                        results[0]
                    }

                    if (closestStop != null) {
                        val closestStopLatLng = LatLng(closestStop.stop_lat, closestStop.stop_lon)
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(closestStopLatLng, DEFAULT_ZOOM))
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Enable the current location button and set its click listener
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    getCurrentLocation()
                    return
                }
                map.isMyLocationEnabled = true
                map.setOnMyLocationButtonClickListener {
                    // Get the user's current location and move the camera there
                    getCurrentLocation()
                    true
                }
            }
        }


    }

}
