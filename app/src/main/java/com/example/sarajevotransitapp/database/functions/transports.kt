package com.example.sarajevotransitapp.database.functions

import com.example.sarajevotransitapp.database.entities.transport



object transports {
    fun transportlist(): transports {
        val tot = listOf(
            transport(
                1,
                "Tramvaj",
                "Sarajevski tramvaji su tramvajski sistem u glavnom gradu Bosne i Hercegovine Sarajevu. Vlasnik tramvaja je Javno komunalno preduzeće – Gradski saobraćaj Sarajevo (GRAS), koje ujedno i organizuje javni promet u Sarajevu."
            ),
            transport(
                2,
                "Trolejbus",
                "Sarajevski trolejbusi su trolejbuski sistem u glavnom gradu Bosne i Hercegovine, Sarajevu. Vlasnik trolejbusa je Javno komunalno preduzeće – Gradski saobraćaj Sarajevo"
            ),
            transport(3, "Komercijala", "31E autobus (Dobrinja  - Vijecnica) - Brza Linija"),
            transport(4, "Autobus", "Razne bus linije gradske i prigradske"),
            transport(5, "Minibus", "Razne kraće linije gradskog i prigradskog saobracaja")
        )
        return transports
    }
}