package com.ddd.ansayo.core_model.place

data class PlaceGeometry(
    val location: Geometry
) {
    data class Geometry(
        val lat: Double,
        val lng: Double
    )
}
