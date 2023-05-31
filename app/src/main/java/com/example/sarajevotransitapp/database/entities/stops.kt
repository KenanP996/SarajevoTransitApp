package com.example.sarajevotransitapp.database.entities

import androidx.room.*

@Entity(tableName = "stops")
data class stops(
    @PrimaryKey val stop_id: Int,
    @ColumnInfo(name = "stop_name") val stop_name: String,
    @ColumnInfo(name = "stop_lat") val stop_lat: Double,
    @ColumnInfo(name = "stop_lon") val stop_lon: Double
)
