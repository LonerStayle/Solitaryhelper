package com.example.solitaryhelper.database.api.naver.dataholder


data class NaverBlogSearchResult(
    val lastBuildDate: String,
    val postdate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverBlog>

)