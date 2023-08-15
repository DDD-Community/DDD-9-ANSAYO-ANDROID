package com.ddd.ansayo.domain.handler.search

import com.ddd.ansayo.domain.usecase.search.GetSearchCoursesUseCase
import javax.inject.Inject

class SearchCourseMutationHandler @Inject constructor(
    private val getSearchCoursesUseCase: GetSearchCoursesUseCase
) {
}