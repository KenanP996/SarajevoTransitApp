package com.example.sarajevotransitapp.database.dao

import androidx.room.*
import com.example.sarajevotransitapp.database.entities.stops
import com.example.sarajevotransitapp.database.functions.allstations

@Dao
interface StopDao {
    @Insert
    fun insert(entity: stops)

    @Update
    fun update(entity:stops)

    @Delete
    fun dekete(entity: stops)

    @Query("Select * From stops")
    fun getAllStops(): List<allstations>
}