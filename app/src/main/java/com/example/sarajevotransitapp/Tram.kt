package com.example.sarajevotransitapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sarajevotransitapp.database.entities.tickets
import com.example.sarajevotransitapp.database.functions.ticket_options

class Tram : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var ticketAdapter: ArrayAdapter<String>
    private lateinit var tramTicketTypes: List<tickets>
    private var selectedTicket: tickets? = null
    private val selectedTicketsMap: MutableMap<tickets, Int> = mutableMapOf()
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
            selectedTicket = tramTicketTypes[position]
            selectedTicketsMap.putIfAbsent(selectedTicket!!, 0)
            selectTicket(selectedTicket!!)
            // Perform action with selected ticket price and quantity
            // We don't immediately buy the ticket, we wait for user to adjust quantity
        }
    }

    private fun getTramTicketTypes(): List<tickets> {
        return ticket_options.ticket_types().filter { it.tot_id == 1 }
    }

    fun increaseQuantity(view: View) {
        selectedTicket?.let {
            val currentQuantity = selectedTicketsMap.getOrDefault(it, 0)
            selectedTicketsMap[it] = currentQuantity + 1
            totalTicketPrice += it.ticket_price
            updateTotalPriceUI()
        }
    }

    fun decreaseQuantity(view: View) {
        selectedTicket?.let {
            val currentQuantity = selectedTicketsMap.getOrDefault(it, 0)
            if (currentQuantity > 0) {
                selectedTicketsMap[it] = currentQuantity - 1
                totalTicketPrice -= it.ticket_price
                updateTotalPriceUI()
            }
        }
    }

    private fun updateTotalPriceUI() {
        val totalPriceTextView = findViewById<TextView>(R.id.tvTotalPrice)
        totalPriceTextView.text = getString(R.string.total_price, totalTicketPrice)
    }

    fun checkout(view: View) {
        val intent = Intent(this, GooglePayActivity::class.java)
        intent.putExtra("totalTicketPrice", totalTicketPrice)
        startActivity(intent)
    }
    private fun selectTicket(ticket: tickets) {
        val selectedTicketTextView = findViewById<TextView>(R.id.tvSelectedTicket)
        selectedTicketTextView.text = "Selected ticket: ${ticket.ticket_desc}"
    }
}
