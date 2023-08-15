package com.ddd.ansayo.presentation.viewmodel.place

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.place.PlaceDetailMutationHandler
import com.ddd.ansayo.domain.model.place.PlaceDetailAction
import com.ddd.ansayo.domain.model.place.PlaceDetailMutation
import com.ddd.ansayo.domain.model.place.PlaceDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val mutationHandler: PlaceDetailMutationHandler
) : ContainerHost<PlaceDetailState, PlaceDetailMutation.SideEffect>, ViewModel() {

    override val container: Container<PlaceDetailState, PlaceDetailMutation.SideEffect> =
        container(PlaceDetailState.EMPTY)

    fun onAction(action: PlaceDetailAction) = intent {
        mutationHandler.mutate(state, action)
            .collect {
                when (it) {
                    is PlaceDetailMutation.Reduce -> reduce(it)
                    is PlaceDetailMutation.SideEffect -> postSideEffect(it)
                }
            }
    }

    private fun reduce(mutation: PlaceDetailMutation.Reduce) {
        intent {
            reduce {
                when (mutation) {
                    is PlaceDetailMutation.Reduce.UpdateFavoriteCount -> {
                        state.copy(placeDetail = mutation.placeDetail)
                    }
                    is PlaceDetailMutation.Reduce.UpdatePlaceInfo -> {
                        state.copy(
                            placeDetail = mutation.placeDetail,
                            courses = mutation.courses
                        )
                    }
                }
            }
        }
    }
}
