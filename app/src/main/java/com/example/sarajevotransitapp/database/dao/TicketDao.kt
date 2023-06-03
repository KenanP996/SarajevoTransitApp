package com.example.sarajevotransitapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.sarajevotransitapp.database.functions.ticket_options

@Dao
interface TicketDao {
    @Query("SELECT * FROM ticket_options WHERE ticket_type = :transportId")
    fun getTicketsForTransport(transportId: Int): LiveData<List<ticket_options>>
}