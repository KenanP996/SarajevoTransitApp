package com.example.sarajevotransitapp

import android.view.animation.AnimationUtils
import android.widget.ImageView

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    private val splashDelay: Long = 2000 // Adjust the delay duration as needed (e.g., 2000ms = 2 seconds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen)

        // Delay for the specified duration and then start the MainActivity
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Call finish() if you don't want the user to navigate back to the splash screen
        }, splashDelay)
    }
}