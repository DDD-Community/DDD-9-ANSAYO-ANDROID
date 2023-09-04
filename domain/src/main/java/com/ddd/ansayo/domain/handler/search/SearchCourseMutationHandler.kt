package com.ddd.ansayo.domain.handler.search

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.search.SearchCourseAction
import com.ddd.ansayo.domain.model.search.SearchCourseMutation
import com.ddd.ansayo.domain.model.search.SearchCourseState
import com.ddd.ansayo.domain.usecase.search.GetSearchCoursesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchCourseMutationHandler @Inject constructor(
    private val getSearchCoursesUseCase: GetSearchCoursesUseCase
) {
    suspend fun mutate(
        state: SearchCourseState,
        action: SearchCourseAction
    ): Flow<SearchCourseMutation> {
        return flow {
            when(action) {
                is SearchCourseAction.ClickCourseList -> {
                    emit(SearchCourseMutation.SideEffect.StartCourseDetail(action.id))
                }
                is SearchCourseAction.ClickRecordCourse -> {
                    emit(SearchCourseMutation.SideEffect.StartCourseRecord)
                }
                is SearchCourseAction.SearchKeyword -> {
                    when(val result = getSearchCoursesUseCase(action.searchKeyword)) {
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
                is SearchCourseAction.SelectSearchListTab -> {
                    flowOf(SearchCourseMutation.SideEffect.NavToPlace)
                }
                is SearchCourseAction.ClickBackButton -> {
                    flowOf(SearchCourseMutation.SideEffect.BackScreen)
                }
                SearchCourseAction.ClickSeachBar -> {
                    emit(SearchCourseMutation.SideEffect.SerachScreen)
                }
            }
        }
    }
}