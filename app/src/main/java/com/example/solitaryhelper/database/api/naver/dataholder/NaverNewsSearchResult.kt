package com.example.solitaryhelper.database.api.naver.dataholder

data class NaverNewsSearchResult(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverNews>
)