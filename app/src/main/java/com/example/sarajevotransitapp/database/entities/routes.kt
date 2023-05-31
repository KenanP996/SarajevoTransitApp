package com.example.sarajevotransitapp.database.entities

import androidx.room.*
import com.example.sarajevotransitapp.database.entities.transport


@Entity(
    tableName = "routes",
    foreignKeys = [
        ForeignKey(
            entity = transport::class,
            parentColumns = ["transporttype_id"],
            childColumns = ["route_type"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class routes(
    @PrimaryKey val route_id: Int,
    val route_name: String,
    @ColumnInfo(name = "route_type", index = true)
    val transporttype_id: Int,
    val route_color : String
)
