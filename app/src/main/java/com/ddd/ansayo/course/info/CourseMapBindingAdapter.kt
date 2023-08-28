package com.ddd.ansayo.course.info

import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.ddd.ansayo.R
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.presentation.viewmodel.course.CourseInfoViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PolylineOverlay

object CourseMapBindingAdapter {
    lateinit var southEast: LatLng
    var rad: Double = 1.0

    fun MapFragment.setMultiGeoPoint(data: List<Place>, viewModel: CourseInfoViewModel) {
        val markers = mutableListOf<Marker>()
        for ((index, list) in data.withIndex()) {
            list.let {
                getMapAsync { naverMap ->
                    naverMap.addOnCameraChangeListener { i, b ->
                        southEast = LatLng(
                            naverMap.coveringBounds.southWest.latitude,
                            naverMap.coveringBounds.northEast.longitude
                        )
                        rad = (naverMap.coveringBounds.northEast.distanceTo(southEast) / 1000 / 2)
                    }
                    val naverLatLng = LatLng(it.geometry.lat, it.geometry.lng)
                    val marker = Marker().apply {
                        this.width = 50
                        this.height = 50

                        position = naverLatLng
                        map = naverMap
                        markers.add(this)

                        val imageResource = when (index) {
                            0 -> com.ddd.ansayo.core_design.R.drawable.img_pin_no1
                            1 -> com.ddd.ansayo.core_design.R.drawable.img_pin_no2
                            2 -> com.ddd.ansayo.core_design.R.drawable.img_pin_no3
                            else -> com.ddd.ansayo.core_design.R.drawable.img_pin_no4
                        }
                        val icon = OverlayImage.fromResource(imageResource)
                        this.icon = icon
                    }
                    marker.map = naverMap
                    val polyCoords = mutableListOf<LatLng>()

                    data.map { place ->
                        val naverLatLng = LatLng(place.geometry.lat, place.geometry.lng)
                        polyCoords.add(naverLatLng)
                    }

                    if (polyCoords.size >= 2) {
                        val polyline = PolylineOverlay()
                        polyline.apply {
                            coords = polyCoords
                            setPattern(10, 4)
                            color = ContextCompat.getColor(requireContext(), com.ddd.ansayo.core_design.R.color.N70)
                        }
                        polyline.map = naverMap
                    }
                    if (markers.isNotEmpty()) {
                        val bounds = LatLngBounds.Builder()
                        for (marker in markers) {
                            bounds.include(marker.position)
                        }
                        val padding = resources.getDimensionPixelSize(R.dimen.map_padding)
                        val cameraUpdate = CameraUpdate.fitBounds(bounds.build(), padding)
                        naverMap.moveCamera(cameraUpdate)
                    }
                }
            }
        }
    }
}
