package com.example.sarajevotransitapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sarajevotransitapp.database.functions.transport
import kotlinx.coroutines.selects.select

class Transport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport)

        val listView: ListView = findViewById(R.id.listView)

        val transportList = transport.transportlist()
        val transportNames = transportList.map { "${it.transport_name} - ${it.transport_desc}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transportNames)

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedTransport = transportList[position]
            if (selectedTransport.tot_id == 1) {
                // Open Tram activity
                val intent = Intent(this, Tram::class.java)
                startActivity(intent)
            } else if (selectedTransport.tot_id == 2) {
                // Open Trolleybus activity
                val intent = Intent(this, Trolleybus::class.java)
                startActivity(intent)
            } else if (selectedTransport.tot_id == 3) {
                // Open Komercijala activity
                val intent = Intent(this, Komercijala::class.java)
                startActivity(intent)
            } else if (selectedTransport.tot_id == 4) {
                // Open Komercijala activity
                val intent = Intent(this, Busminibus::class.java)
                startActivity(intent)
            } else if (selectedTransport.tot_id == 5) {
                // Open Komercijala activity
                val intent = Intent(this, Busminibus::class.java)
                startActivity(intent)

            // Handle other transports accordingly
                Toast.makeText(
                    this,
                    "Selected transport: ${selectedTransport.transport_name}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}

