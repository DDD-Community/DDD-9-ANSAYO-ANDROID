package com.ddd.ansayo.core_model.course

import com.ddd.ansayo.core_model.place.Geometry
import com.ddd.ansayo.core_model.place.Place
import com.google.gson.annotations.SerializedName

data class CourseInfo(
    val course: Course,
    @SerializedName("place_photos")
    val placePhotos: List<List<PlacePhoto>>,
    @SerializedName("place_reviews")
    val placeReviews: List<PlaceReview>,
    val places: List<Place>
) {
    companion object {
        val p1 = Place(
            businessStatus= "",
            formattedAddress= "",
            geometry = Geometry(lat = 37.4982893, lng = 126.9213215),
            name = "파세로",
            openingHours = emptyList(),
            phone = "",
            photos = emptyList(),
            placeId = "",
            rating = 0f,
            reviews = emptyList(),
            types = emptyList(),
            isFavorite = false
        )
        val p2 = Place(
            businessStatus= "",
            formattedAddress= "",
            geometry = Geometry(lat = 37.4932234, lng = 126.9244612),
            name = "보라매병원",
            openingHours = emptyList(),
            phone = "",
            photos = emptyList(),
            placeId = "",
            rating = 0f,
            reviews = emptyList(),
            types = emptyList(),
            isFavorite = true
        )
        val p3 = Place(
            businessStatus= "",
            formattedAddress= "",
            geometry = Geometry(lat = 37.4990700, lng =  126.9303927),
            name = "투썸 신삼점",
            openingHours = emptyList(),
            phone = "",
            photos = emptyList(),
            placeId = "",
            rating = 0f,
            reviews = emptyList(),
            types = emptyList(),
            isFavorite = true
        )

        val placeList = mutableListOf(p1, p2, p3)


        val TEMPDATA = CourseInfo(
            course = Course(
                authorId = "",
                favorites = 3,
                id = "",
                isFavorite = true,
                name = "샤로수길 빵지순례",
                regDate = "2023년 7월 17일",
                review = "오늘은 샤로수길에 가서 빵을 먹었다! 빵복한 하루어쩌구 신선 기억"
            ),
            placePhotos = emptyList(),
            placeReviews = emptyList(),
            places = placeList
        )

    }
}
