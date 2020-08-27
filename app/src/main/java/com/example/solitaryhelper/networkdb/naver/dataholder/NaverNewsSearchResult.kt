package com.example.solitaryhelper.networkdb.naver.dataholder

data class NaverNewsSearchResult(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverNews>
)