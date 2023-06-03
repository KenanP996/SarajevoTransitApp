package com.example.sarajevotransitapp.model

import java.text.NumberFormat

sealed class TicketItem(
    open val name: String,
    open val description: String,
    open val price: Double
) {
    /**
     * Getter method for price.
     * Includes formatting.
     */
    data class VehicleItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : TicketItem(name, description, price)

    data class QuantityItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : TicketItem(name, description, price)

    data class TypeItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : TicketItem(name, description, price)

    /**
     * Getter method for price.
     * Includes formatting.
     */
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}
