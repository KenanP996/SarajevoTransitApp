package com.example.sarajevotransitapp.database.functions

import com.example.sarajevotransitapp.database.entities.transport



object transports {
    fun transportlist(): List<transport> {
        val tot = listOf(
            transport(
                1,
                "Tramvaj",
                "\nVlasnik tramvaja je Javno komunalno preduzeće – Gradski saobraćaj Sarajevo (GRAS)."
            ),
            transport(
                2,
                "Trolejbus",
                " \nVlasnik trolejbusa je Javno komunalno preduzeće – Gradski saobraćaj Sarajevo (GRAS)"
            ),
            transport(3, "Komercijala", "\n31E autobus (Dobrinja  - Vijecnica) - Brza Linija"),
            transport(4, "Autobus", "\nRazne bus linije gradske i prigradske"),
            transport(5, "Minibus", "\nRazne kraće linije gradskog i prigradskog saobracaja")
        )
        return tot
    }
}