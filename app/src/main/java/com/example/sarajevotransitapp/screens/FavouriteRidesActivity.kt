package com.example.sarajevotransitapp.screens

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity



class FavoriteRidesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.sarajevotransitapp.R.layout.favourite_rides)

        val heartImageView = findViewById<ImageView>(com.example.sarajevotransitapp.R.id.heartImageView)
        val pulseAnimation: Animation = AnimationUtils.loadAnimation(this, com.example.sarajevotransitapp.R.anim.heart_anim)
        heartImageView.startAnimation(pulseAnimation)

        /*// Get references to the favorite ride TextViews
        val favoriteRide1 = findViewById<TextView>(R.id.favorite_ride1)
        val favoriteRide2 = findViewById<TextView>(R.id.favorite_ride2)

        // Set click listeners for the favorite ride TextViews
        favoriteRide1.setOnClickListener {
            Toast.makeText(
                this@FavoriteRidesActivity,
                "Favorite Ride 1 clicked",
                Toast.LENGTH_SHORT
            ).show()
            // Add your desired logic here when Favorite Ride 1 is clicked
        }
        favoriteRide2.setOnClickListener {
            Toast.makeText(
                this@FavoriteRidesActivity,
                "Favorite Ride 2 clicked",
                Toast.LENGTH_SHORT
            ).show()
            // Add your desired logic here when Favorite Ride 2 is clicked
        }*/
    }
}
