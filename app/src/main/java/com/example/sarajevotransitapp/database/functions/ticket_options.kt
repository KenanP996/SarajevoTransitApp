package com.example.sarajevotransitapp.database.functions

import com.example.sarajevotransitapp.database.entities.tickets

object ticket_options {
    fun ticket_types() : List<tickets> {
        val tickets = listOf(
            tickets(10, 1, "Karta Tramvajska (Odrasli, jedno)", 1.6),
            tickets(11, 1, "Karta Tramvajska (Dječija, jedno)", 1.2),
            tickets(12, 1, "Karta Tramvajska (Dnevna, jedno)", 20.00),
            tickets(13, 1, "Karta Tramvajska (Grupna)", 25.00),
            tickets(14, 1, "Karta Tramvajska (Grupna, Dnevna)", 35.00),
            tickets(20, 2, "Karta Trolejbuska (Odrasli, jedno)", 1.4),
            tickets(21, 2, "Karta Trolejbuska (Dječja, jedno)", 0.8),
            tickets(22, 2, "Karta Trolejbuska (Dnevna)", 20.00),
            tickets(23, 2, "Karta Trolejbuska (Grupna)", 25.00),
            tickets(24, 2, "Karta Trolejbuska (Grupna, dnevna)", 35.00),
            tickets(30, 3, "Karta Komercijala - Linija 31E (DBR-VĆN)", 1.6),
            tickets(40, 4, "Karta Autobuska", 1.6),
            tickets(50, 5, "Karta Minibus", 1.6)
        )
        return tickets
    }
}