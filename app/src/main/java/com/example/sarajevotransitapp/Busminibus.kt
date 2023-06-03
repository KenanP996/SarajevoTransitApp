package com.example.sarajevotransitapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sarajevotransitapp.database.entities.tickets
import com.example.sarajevotransitapp.database.functions.ticket_options

class Busminibus : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var ticketAdapter: ArrayAdapter<String>
    private lateinit var tramTicketTypes: List<tickets>
    private var ticketQuantity: Int = 1
    private var totalTicketPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tram)

        listView = findViewById(R.id.listView)

        tramTicketTypes = getTramTicketTypes()
        val ticketNames = tramTicketTypes.map { "${it.ticket_desc} - ${it.ticket_price}" }
        ticketAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ticketNames)

        listView.adapter = ticketAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedTicket = tramTicketTypes[position]
            val ticketPrice = selectedTicket.ticket_price

            // Perform action with selected ticket price and quantity
            buyTicket(selectedTicket, ticketQuantity)
        }
    }

    private fun getTramTicketTypes(): List<tickets> {
        return ticket_options.ticket_types().filter { it.tot_id == 4 }
    }

    private fun buyTicket(ticket: tickets, quantity: Int) {
        val totalPrice = ticket.ticket_price * quantity
        val message = "Ticket: ${ticket.ticket_desc}\nQuantity: $quantity\nTotal Price: $totalPrice"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        totalTicketPrice += totalPrice
    }

    fun increaseQuantity(view: View) {
        ticketQuantity++
        updateTicketQuantityUI()
    }

    fun decreaseQuantity(view: View) {
        if (ticketQuantity > 1) {
            ticketQuantity--
            updateTicketQuantityUI()
        }
    }

    private fun updateTicketQuantityUI() {
        val ticketQuantityTextView = findViewById<TextView>(R.id.tvTicketQuantity)
        ticketQuantityTextView.text = ticketQuantity.toString()
    }

    fun checkout(view: View) {
        val intent = Intent(this, GooglePayActivity::class.java)
        intent.putExtra("totalTicketPrice", totalTicketPrice)
        startActivity(intent)
    }
}
