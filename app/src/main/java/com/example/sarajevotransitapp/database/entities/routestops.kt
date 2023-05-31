package com.example.sarajevotransitapp.database.entities

import com.example.sarajevotransitapp.database.entities.transport
import com.example.sarajevotransitapp.database.entities.stops
import com.example.sarajevotransitapp.database.entities.routes


import androidx.room.*


@Entity(
    tableName = "routestops",
    foreignKeys = [
        ForeignKey(
            entity = routes::class,
            parentColumns = ["route_id"],
            childColumns =  ["route_id"],
            onDelete = ForeignKey.CASCADE
        ),
            ForeignKey(
                entity = stops::class,
                parentColumns = ["stop_id"],
                childColumns =  ["stop_id"],
                onDelete = ForeignKey.CASCADE

        ),
    ]
)




data class routestops(
    @ColumnInfo(name = "route_id") val route_id : Int,
    @ColumnInfo(name = "stop_id") val stop_id : Int,
    @PrimaryKey(autoGenerate = true) val stop_order : Int,
    @ColumnInfo(name = "stop_name") val stop_name : String

)
