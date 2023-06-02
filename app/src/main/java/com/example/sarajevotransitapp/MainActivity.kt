package com.example.sarajevotransitapp
import NajblizeStanice
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)
        MobileAds.initialize(this) {}

        val goButton: Button = findViewById(R.id.goButton)

        goButton.setOnClickListener {
            val locationEditText: EditText = findViewById(R.id.locationEditText)
            val destinationEditText: EditText = findViewById(R.id.destinationEditText)

            val location = locationEditText.text.toString()
            val destination = destinationEditText.text.toString()

            val intent = Intent(this, NajblizeStanice::class.java)
            intent.putExtra("location", location)
            intent.putExtra("destination", destination)
            startActivity(intent)

            Toast.makeText(this, "Looking for closest station", Toast.LENGTH_SHORT).show() // Handle button click event here
            // Implement the logic to retrieve user's geolocation, find the closest station, etc.
        }
    }
}





















