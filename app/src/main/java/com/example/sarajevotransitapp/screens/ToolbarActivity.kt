package com.example.sarajevotransitapp.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sarajevotransitapp.R

class ToolbarActivity : AppCompatActivity(), ToolbarClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toolbar)

        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val ticketsButton = findViewById<ImageView>(R.id.ticketsButton)
        val departuresButton = findViewById<ImageView>(R.id.departuresButton)
        val favoriteRidesButton = findViewById<ImageView>(R.id.favoriteRidesButton)
        val infoDeskButton = findViewById<ImageView>(R.id.infoDeskButton)

        homeButton.setOnClickListener { onHomeClicked() }
        ticketsButton.setOnClickListener { onTicketsClicked() }
        departuresButton.setOnClickListener { onDeparturesClicked() }
        favoriteRidesButton.setOnClickListener { onFavoriteRidesClicked() }
        infoDeskButton.setOnClickListener { onInfoDeskClicked() }
    }

    override fun onHomeClicked() {
        // Handle home button click
    }

    override fun onTicketsClicked() {
        // Handle tickets button click
    }

    override fun onDeparturesClicked() {
        // Handle departures button click
    }

    override fun onFavoriteRidesClicked() {
        //val intent = Intent(this, FavouriteRidesActivity::class.java)
        //startActivity(intent)
    }

    override fun onInfoDeskClicked() {
        // Handle info desk button click
    }
}
