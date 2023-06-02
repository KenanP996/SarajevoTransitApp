package com.example.sarajevotransitapp.database.dao

import androidx.room.Dao
import com.example.sarajevotransitapp.database.entities.routestops


@Dao
interface routestopsDAO {
    fun insert_routestops(routestops: routestops)

    fun delete_routestops(routestops: routestops)

    fun  update_routestops(routestops:routestops)

    fun getStopbyOrder (stop_order: Int) : routestops


}