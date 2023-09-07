package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.search.RecentKeyword

interface SearchRepository {
     fun getRecentSearchKeyword() : List<RecentKeyword>
     fun removeLatestSearchKeyword(recentKeyword: RecentKeyword)

}