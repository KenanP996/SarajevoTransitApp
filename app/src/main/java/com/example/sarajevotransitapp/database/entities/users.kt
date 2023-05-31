package com.example.sarajevotransitapp.database.entities

import androidx.room.*

@Entity
data class users(
    @PrimaryKey(autoGenerate = true) val user_id : Int,
    @ColumnInfo(name = "email") val user_email : String,
    @ColumnInfo(name = "user_pw") val user_pwhash : String,
)
