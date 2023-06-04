package com.example.sarajevotransitapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view for the activity
        setContentView(R.layout.toolbar)

        // Find the toolbar and buttons
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val homeButton = toolbar.findViewById<ImageButton>(R.id.homeButton)
        val ticketsButton = toolbar.findViewById<ImageButton>(R.id.ticketsButton)
        val departuresButton = toolbar.findViewById<ImageButton>(R.id.departuresButton)
        val mapaButton = toolbar.findViewById<ImageButton>(R.id.mapa)

        // Set click listeners for the toolbar buttons
        homeButton.setOnClickListener {
            // Handle home button click
            onHomeButtonClicked()
        }

        ticketsButton.setOnClickListener {
            // Handle tickets button click
            onTicketsButtonClicked()
        }

        departuresButton.setOnClickListener {
            // Handle departures button click
            onDeparturesButtonClicked()
        }


        mapaButton.setOnClickListener {
            // Handle map button click
            onMapButtonClicked()
        }
    }

    // Define the button click listener methods
    private fun onHomeButtonClicked() {
        // Implement the desired action when the home button is clicked
        // For example, navigate to the home activity
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun onTicketsButtonClicked() {
        // Implement the desired action when the tickets button is clicked
        // For example, navigate to the tickets activity
        startActivity(Intent(this, Transport::class.java))
    }

    private fun onDeparturesButtonClicked() {
        // Implement the desired action when the departures button is clicked
        // For example, navigate to the departures activity
        startActivity(Intent(this, RouteActivity::class.java))
    }

    private fun onMapButtonClicked() {
        // Implement the desired action when the map button is clicked
        // For example, navigate to the map activity
        startActivity(Intent(this, ClosestStations::class.java))
    }
}
