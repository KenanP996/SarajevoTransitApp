package com.example.sarajevotransitapp.database.entities

import androidx.room.*

@Entity(tableName="type_of_transport")
data class transport(
    @PrimaryKey(autoGenerate = true) val tot_id : Int,
    @ColumnInfo(name = "Transport name") val transport_name : String,
    @ColumnInfo(name = "Transport desc") val transport_desc : String
    )
