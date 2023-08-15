package com.ddd.ansayo.domain.handler.search

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.search.SearchCourseAction
import com.ddd.ansayo.domain.model.search.SearchCourseMutation
import com.ddd.ansayo.domain.usecase.search.GetSearchCoursesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchCourseMutationHandler @Inject constructor(
    private val getSearchCoursesUseCase: GetSearchCoursesUseCase
) {

    suspend fun mutate(
        state: SearchCourseMutation,
        action: SearchCourseAction
    ): Flow<SearchCourseMutation> {
        return flow {
            when(action) {
                is SearchCourseAction.ClickCourse -> {
                    emit(SearchCourseMutation.SideEffect.StartCourseDetail(action.id))
                }
                is SearchCourseAction.ClickRecordCourse -> {
                    emit(SearchCourseMutation.SideEffect.StartCourseRecord)

                }
                is SearchCourseAction.SelectSearchListTab -> {
                    when(val result = getSearchCoursesUseCase()) {
                        is Response.Fail -> {

                        }
                        is Response.Success -> {
                            emit(
                                SearchCourseMutation.Mutation.UpdateCourse(
                                    course = result.data.courses.orEmpty()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}