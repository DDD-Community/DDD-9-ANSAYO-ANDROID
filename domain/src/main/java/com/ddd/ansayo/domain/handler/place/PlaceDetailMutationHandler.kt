package com.ddd.ansayo.domain.handler.place

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.place.PlaceDetail
import com.ddd.ansayo.domain.model.place.PlaceDetailAction
import com.ddd.ansayo.domain.model.place.PlaceDetailMutation
import com.ddd.ansayo.domain.model.place.PlaceDetailState
import com.ddd.ansayo.domain.usecase.favorite.DeleteFavoritePlaceUseCase
import com.ddd.ansayo.domain.usecase.favorite.PostFavoritePlaceUseCase
import com.ddd.ansayo.domain.usecase.place.GetPlaceInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaceDetailMutationHandler @Inject constructor(
    private val getPlaceInfoUseCase: GetPlaceInfoUseCase,
    private val postFavoritePlaceUseCase: PostFavoritePlaceUseCase,
    private val deleteFavoritePlaceUseCase: DeleteFavoritePlaceUseCase
) {

    suspend fun mutate(
        state: PlaceDetailState,
        action: PlaceDetailAction
    ): Flow<PlaceDetailMutation> {
        return flow {
            when (action) {
                PlaceDetailAction.ClickBackButton -> {
                    emit(PlaceDetailMutation.SideEffect.Finish)
                }

                is PlaceDetailAction.ClickCourse -> {
                    emit(PlaceDetailMutation.SideEffect.NaviToCourseDetail(action.courseId))
                }

                is PlaceDetailAction.ClickFavorite -> {
                    when (val result =
                        if (action.like) {
                            deleteFavoritePlaceUseCase(state.placeDetail.place.placeId)
                        } else {
                            postFavoritePlaceUseCase(state.placeDetail.place.placeId)
                        }
                    ) {
                        is Response.Fail -> {
                            emit(PlaceDetailMutation.SideEffect.ShowSnackBar(result.message))
                        }

                        is Response.Success -> {
                            val favoriteCount = if (action.like) {
                                state.placeDetail.favoriteCount - 1
                            } else {
                                state.placeDetail.favoriteCount + 1
                            }
                            emit(
                                PlaceDetailMutation.Reduce.UpdateFavoriteCount(
                                    state.placeDetail.copy(
                                        favoriteCount = favoriteCount,
                                        isFavorite = state.placeDetail.isFavorite.not()
                                    )
                                )
                            )
                        }
                    }
                }

                is PlaceDetailAction.EnteredScreen -> {
                    when (val result = getPlaceInfoUseCase(action.placeId)) {
                        is Response.Fail -> {
                            emit(PlaceDetailMutation.SideEffect.ShowSnackBar(result.message))
                        }

                        is Response.Success -> {
                            emit(
                                PlaceDetailMutation.Reduce.UpdatePlaceInfo(
                                    placeDetail = PlaceDetail(
                                        place = result.data.data,
                                        favoriteCount = result.data.favoriteCount,
                                        isFavorite = result.data.data.isFavorite
                                    ),
                                    courses = result.data.course.orEmpty()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
