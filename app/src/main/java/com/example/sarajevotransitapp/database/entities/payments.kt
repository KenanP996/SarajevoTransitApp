package com.example.sarajevotransitapp.database

import androidx.room.*
import com.example.sarajevotransitapp.database.entities.tickets
import com.example.sarajevotransitapp.database.entities.users
import java.util.Date

@Entity(tableName = "payments", foreignKeys = [
    ForeignKey(
        entity = tickets::class,
        parentColumns = ["ticket_type"],
        childColumns = ["ticket_type"],
        onDelete = ForeignKey.CASCADE
     )
    ]
    )

data class Payments(
    @PrimaryKey(autoGenerate = true) val payment_id: Int,
    val user_id: Int,
    val payment_date : Date,
    val payment_amount : Double,
    val ticket_type : Int,
    val payment_status : Boolean,
    val payment_gateway : String,
    val transaction_id : Int
)