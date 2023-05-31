package com.example.sarajevotransitapp.database.entities

import androidx.room.*

@Entity(tableName = "admins")
data class admins(
    @PrimaryKey(autoGenerate = true) val admin_id : Int,
    @ColumnInfo(name = "admin email") val admin_email : String,
    @ColumnInfo(name = "admin password") val admin_pw : String
)
