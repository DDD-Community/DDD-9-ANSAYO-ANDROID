package com.ddd.ansayo.domain.model.course

data class CourseWriteState(
    val header: Header,
    val places: List<Place>,
    val footer: Footer
) {

    data class Header(
        val title: String,
        val description: String,
        val isCourseTitleMaxInputted: Boolean,
        val isCourseDescriptionMaxInputted: Boolean,
        val date: Long
    ) {

        companion object {
            val EMPTY = Header(
                title = "",
                description = "",
                isCourseTitleMaxInputted = false,
                isCourseDescriptionMaxInputted = false,
                date = 0L
            )
        }
    }

    data class Place(
        val id: String,
        val order: Int,
        val title: String,
        val address: String,
        val category: List<String>?,
        val isPlaceReviewMaxInputted: Boolean,
        val review: String,
        val images: List<CoursePlaceImage>
    )

    data class Footer(
        val isPrivate: Boolean,
        val isConfirmButtonEnabled: Boolean
    ) {
        companion object {
            val EMPTY = Footer(
                isPrivate = false,
                isConfirmButtonEnabled = false
            )
        }
    }

    companion object {
        val EMPTY = CourseWriteState(
            header = Header.EMPTY,
            places = emptyList(),
            footer = Footer.EMPTY
        )
    }
}
