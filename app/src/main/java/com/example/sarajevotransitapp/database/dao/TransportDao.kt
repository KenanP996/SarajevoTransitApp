package com.example.sarajevotransitapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.sarajevotransitapp.database.entities.transport
import com.example.sarajevotransitapp.database.functions.transports
import com.google.android.datatransport.Transport

@Dao
interface TransportDao {
    @Query("SELECT * FROM type_of_transport")
    fun getAllTransports(): LiveData<List<transport>>

}