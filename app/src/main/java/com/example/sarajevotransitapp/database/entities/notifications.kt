package com.example.sarajevotransitapp.database

import androidx.room.*
import com.example.sarajevotransitapp.database.entities.users

@Entity(
    tableName = "notifications",
    foreignKeys = [
        ForeignKey(
            entity = users::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Notification(
    @PrimaryKey val notif_id: Int,
    @ColumnInfo(name = "user_id", index = true)
    val userId: Int,
    val notify_type: String,
    val notif_msg: String,
    val notif_date: String
)
