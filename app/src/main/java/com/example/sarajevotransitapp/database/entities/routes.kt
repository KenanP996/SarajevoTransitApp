package com.example.sarajevotransitapp.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.example.sarajevotransitapp.database.entities.transport


@Entity(
    tableName = "routes",
    foreignKeys = [
        ForeignKey(
            entity = transport::class,
            parentColumns = ["tot_id"],
            childColumns = ["route_type"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class routes(
    @PrimaryKey val route_id: Int,
    val route_name: String,
    @ColumnInfo(name = "route_type", index = true)
    val tot_id: Int,
    val route_color : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(route_id)
        parcel.writeString(route_name)
        parcel.writeInt(tot_id)
        parcel.writeString(route_color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<routes> {
        override fun createFromParcel(parcel: Parcel): routes {
            return routes(parcel)
        }

        override fun newArray(size: Int): Array<routes?> {
            return arrayOfNulls(size)
        }
    }
}
