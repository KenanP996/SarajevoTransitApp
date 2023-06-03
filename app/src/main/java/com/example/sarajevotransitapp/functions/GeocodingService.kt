package com.example.sarajevotransitapp.functions
import com.example.sarajevotransitapp.NajblizeStanice
import com.example.sarajevotransitapp.R
import com.google.android.gms.maps.model.LatLng
import java.net.URLEncoder

class GeocodingService(private val context: NajblizeStanice) {



    suspend fun getLatLongFromAddress(address: String?): LatLng? {
        val addressEncoded = URLEncoder.encode(address, "utf-8")
        val url = "https://maps.googleapis.com/maps/api/geocode/json?address=$addressEncoded&key=${context.getString(R.string.google_maps_key)}"

        return try {
            val response = address?.let { RetrofitInstance.api.getGeoCodeData(it, context.getString(R.string.google_maps_key)) }
            if (response!!.isSuccessful && response.body() != null) {
                val location = response.body()!!.results[0].geometry.location
                LatLng(location.lat, location.lng)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


}


