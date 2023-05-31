package com.example.sarajevotransitapp.database

import androidx.room.*
import com.example.sarajevotransitapp.database.entities.users
import java.util.Date

@Entity(tableName = "payments", foreignKeys = [
    ForeignKey(
        entity = users::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
     )
    ]
    )

data class Payments(
    @PrimaryKey val payment_id: Int,
    val user_id: Int,
    val payment_date : Date,
    val payment_amount : Double,
    val payment_status : Boolean,
    val payment_gateway : String,
    val transaction_id : Int
)