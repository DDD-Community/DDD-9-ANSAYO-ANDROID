package com.ddd.ansayo.domain.model.home

sealed class HomeAction {
    object SearchableBadgeList : HomeAction()
    object GetRecommandCourseBadge : HomeAction() //최하단 그냥 추천 코스
    object ClickRecordButton : HomeAction()
    object ClickSearchButton : HomeAction()
    data class ClickBadgeCategory(val badgeId: String) : HomeAction() //최상단 배지 클릭
    data class GetBasicPopularCourse(val badgeId: String) : HomeAction() //첫번째 배지의 코스 추천들
    data class ClickBadgeCourse(val courseId: String) : HomeAction() // 코스 클릭시
}
