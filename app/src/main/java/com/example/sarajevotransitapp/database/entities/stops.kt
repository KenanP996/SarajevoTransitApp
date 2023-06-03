package com.example.sarajevotransitapp.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stops")
data class stops(
    @PrimaryKey val stop_id: Int,
    @ColumnInfo(name = "stop_name") val stop_name: String,
    @ColumnInfo(name = "stop_lat") val stop_lat: Double,
    @ColumnInfo(name = "stop_lon") val stop_lon: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(stop_id)
        parcel.writeString(stop_name)
        parcel.writeDouble(stop_lat)
        parcel.writeDouble(stop_lon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<stops> {
        override fun createFromParcel(parcel: Parcel): stops {
            return stops(parcel)
        }

        override fun newArray(size: Int): Array<stops?> {
            return arrayOfNulls(size)
        }
    }
}
