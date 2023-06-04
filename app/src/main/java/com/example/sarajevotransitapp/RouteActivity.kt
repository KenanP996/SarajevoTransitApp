package com.example.sarajevotransitapp


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.sarajevotransitapp.database.entities.routes
import com.example.sarajevotransitapp.database.functions.routestops
import com.example.sarajevotransitapp.database.functions.allstations

class RouteActivity : BaseActivity() {

    private val REQUEST_CODE_MAP = 1 // Request code for starting the MapActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_route)

        val selectedRoute = intent?.getParcelableExtra<routes>("route")

        if (selectedRoute != null) {
            val routeId = selectedRoute.route_id

            val routeStops = routestops.routestops().filter { it.route_id == routeId }

            for (stop in routeStops) {
                val stopId = stop.stop_id

                val stopObject = allstations.stoplist().find { it.stop_id == stopId }

                if (stopObject != null) {
                    val stopName = stopObject.stop_name
                    val latitude = stopObject.stop_lat
                    val longitude = stopObject.stop_lon

                    val mapIntent = Intent(this, MapActivity::class.java).apply {
                        putExtra("stopName", stopName)
                        putExtra("latitude", latitude)
                        putExtra("longitude", longitude)
                        putExtra("routeId", routeId) // Pass the routeId to the MapActivity
                    }

                    // Start the MapActivity
                    startActivity(mapIntent)


                }
            }
        }
    }


}
