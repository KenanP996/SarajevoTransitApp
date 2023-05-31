package com.example.sarajevotransitapp.database.entities

import androidx.room.*
import com.example.sarajevotransitapp.database.entities.transport

@Entity(tableName = "tickets",
    foreignKeys = [
        ForeignKey(
            entity = transport::class,
            parentColumns = ["transporttype_id"],
            childColumns = ["ticket_type"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class tickets(
    @PrimaryKey(autoGenerate = true) val ticket_id : Int,
    @ColumnInfo(name = "ticket_type", index=true)
    val transporttype_id : Int,
    val ticket_desc : String,
    val ticket_price : Double
    )