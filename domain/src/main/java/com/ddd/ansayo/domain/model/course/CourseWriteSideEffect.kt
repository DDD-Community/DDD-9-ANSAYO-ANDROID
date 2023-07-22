package com.ddd.ansayo.domain.model.course

sealed class CourseWriteSideEffect {
    object None : CourseWriteSideEffect()
    object ShowLoading : CourseWriteSideEffect()
    object HideLoading : CourseWriteSideEffect()
    object ShowDatePickerDialog : CourseWriteSideEffect()
    data class ShowPhotoPicker(val remainCount: Int) : CourseWriteSideEffect()
    data class ShowSnackBar(val message: String) : CourseWriteSideEffect()
    object GoToAddPlace : CourseWriteSideEffect()
    object Finish : CourseWriteSideEffect()
}
