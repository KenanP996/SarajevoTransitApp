package com.example.sarajevotransitapp.ui.theme

import androidx.lifecycle.ViewModel
import com.example.sarajevotransitapp.database.entities.tickets
import com.example.sarajevotransitapp.database.entities.transport
import com.example.sarajevotransitapp.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderView: ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()


    fun updateType(ticketType: tickets) {
        val previousTicketType = _uiState.value.ticketType
        updateItem(ticketType, previousTicketType)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    private fun updateItem(newItem: tickets, previousItem: tickets?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.ticket_price ?: 0.0
            // subtract previous item price in case an item of this category was already added.
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.ticket_price
            // recalculate tax
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax
            )
        }
    }
}
fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}