package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.CourseUploadEntity
import com.ddd.ansayo.core_model.course.PlacePhoto
import com.ddd.ansayo.core_model.course.RequestPlaceReview
import com.ddd.ansayo.domain.model.course.CourseWriteState
import com.ddd.ansayo.domain.repository.CourseRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CreateCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    suspend operator fun invoke(
        places: List<CourseWriteState.Place>,
        request: CourseUploadEntity.Request
    ): Response<CourseUploadEntity.Response> {
        return coroutineScope {
            val requestPlaces = places.map {
                val uploadImages = it.images.map { image -> async { courseRepository.uploadImage(image.origin) } }.awaitAll()
                RequestPlaceReview(
                    photos = uploadImages.map { uploadImage ->
                        val data = (uploadImage as? Response.Success)?.data ?: run {
                            return@coroutineScope Response.Fail("이미지 업로드 실패")
                        }
                        PlacePhoto(data.originUrl, data.thumbnailUrl)
                    },
                    placeId = it.id,
                    review = it.review
                )
            }
            val newRequest = request.copy(placeReviews = requestPlaces)
            courseRepository.postCourse(newRequest)
        }
    }
}
