package com.example.sarajevotransitapp.model

import com.example.sarajevotransitapp.database.entities.tickets
import com.example.sarajevotransitapp.database.entities.transport

data class OrderUiState(
    val vehicle: transport? = null,
    val ticketType: tickets? = null,
    val itemTotalPrice: Double = 0.0,
    val orderTax: Double = 0.0,
    val orderTotalPrice: Double = 0.0
)
