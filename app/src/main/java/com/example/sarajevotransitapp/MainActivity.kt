package com.example.sarajevotransitapp

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MainActivity : AppCompatActivity() {

    private lateinit var placesClient: PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.mainpage)


        // Initialize Places SDK
        Places.initialize(applicationContext, "AIzaSyCn8YM46Qm7hIFVYl9sq_dj8y5HSnjOFbo")
        placesClient = Places.createClient(this)

        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocompleteFragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG
            )
        )
        autocompleteFragment.setCountry("BA") // Restrict to Bosnia and Herzegovina

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                val selectedStreet = place.name
                val selectedLat = place.latLng?.latitude
                val selectedLng = place.latLng?.longitude

                val intent = Intent(this@MainActivity, NajblizeStanice::class.java)
                intent.putExtra("streetName", selectedStreet)
                intent.putExtra("latitude", selectedLat)
                intent.putExtra("longitude", selectedLng)
                startActivity(intent)
            }

            override fun onError(status: Status) {
                // Handle error
            }
        })

        val closestStationsButton = findViewById<ImageButton>(R.id.mapa)
        closestStationsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ClosestStations::class.java)
            startActivity(intent)
        }
        val ticketsButton = findViewById<ImageButton>(R.id.ticketsButton)
        ticketsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, Transport::class.java)
            startActivity(intent)
        }
        val departuresButton = findViewById<ImageButton>(R.id.departuresButton)
        departuresButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RouteTypes::class.java)
            startActivity(intent)
        }

    }

}


