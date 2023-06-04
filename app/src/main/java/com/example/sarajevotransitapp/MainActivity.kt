package com.example.sarajevotransitapp

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MainActivity : BaseActivity() {

    private lateinit var placesClient: PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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




        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        val ticketsButton = findViewById<ImageButton>(R.id.ticketsButton)
        val departuresButton = findViewById<ImageButton>(R.id.departuresButton)

        homeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            startActivity(intent)
        }

        ticketsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, Transport::class.java)
            startActivity(intent)
        }

        departuresButton.setOnClickListener {
                val intent = Intent(this@MainActivity, RouteTypes::class.java)
                startActivity(intent)
        }

        val closestStationsButton = findViewById<ImageButton>(R.id.mapa)
        closestStationsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ClosestStations::class.java)
            startActivity(intent)
        }



    }

}


