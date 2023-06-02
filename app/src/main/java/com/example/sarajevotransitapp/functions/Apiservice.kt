package com.example.sarajevotransitapp.functions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("maps/api/geocode/json")
    suspend fun getGeoCodeData(
        @Query("address") address: String,
        @Query("key") apiKey: String
    ): Response<GeocodeResponse>
}
