package com.example.sarajevotransitapp.database.dao

import androidx.room.*
import com.example.sarajevotransitapp.database.entities.stops
import com.example.sarajevotransitapp.database.functions.allstations

@Dao
interface StopDao {
    @Insert
    fun insert_stop(entity: stops)

    @Update
    fun update_stop(entity:stops)

    @Delete
    fun delete_stop(entity: stops)

    @Query("Select * From stops")
    fun getAllStops(): List<allstations>
}