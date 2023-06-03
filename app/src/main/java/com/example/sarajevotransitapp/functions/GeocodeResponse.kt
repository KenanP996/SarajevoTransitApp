package com.example.sarajevotransitapp.functions

data class GeocodeResponse(
    val results: List<GeocodeResult>
)

data class GeocodeResult(
    val geometry: GeocodeGeometry
)

data class GeocodeGeometry(
    val location: GeocodeLocation
)

data class GeocodeLocation(
    val lat: Double,
    val lng: Double
)
