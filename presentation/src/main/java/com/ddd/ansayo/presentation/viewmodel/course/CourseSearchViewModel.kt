package com.ddd.ansayo.presentation.viewmodel.course

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.search.SearchCourseMutationHandler
import com.ddd.ansayo.domain.model.search.SearchCourseAction
import com.ddd.ansayo.domain.model.search.SearchCourseMutation
import com.ddd.ansayo.domain.model.search.SearchCourseState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CourseSearchViewModel @Inject constructor(
    private val mutationHandler: SearchCourseMutationHandler,
): ContainerHost<SearchCourseState, SearchCourseMutation.SideEffect>, ViewModel() {

    override val container: Container<SearchCourseState, SearchCourseMutation.SideEffect> =
        container(SearchCourseState.EMPTY)
   fun onAction(action: SearchCourseAction) = intent {
       mutationHandler.mutate(state,action)
           .collect() { muatation ->
               when(muatation) {
                    is SearchCourseMutation.Mutation -> reduce(muatation)
                    is SearchCourseMutation.SideEffect -> postSideEffect(muatation)
               }
           }
   }
   private fun reduce(mutation: SearchCourseMutation.Mutation) {
       intent {
           reduce {
               when(mutation) {
                   is SearchCourseMutation.Mutation.UpdateCourse -> {
                       state.copy(
                           courses = mutation.course
                       )
                   }
               }
           }
       }
   }

}