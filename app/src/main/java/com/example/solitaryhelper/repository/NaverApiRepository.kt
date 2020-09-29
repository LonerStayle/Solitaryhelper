package com.example.solitaryhelper.repository

import com.example.solitaryhelper.database.api.naver.api.NaverApiClient
import com.example.solitaryhelper.database.api.naver.dataholder.NaverBlog
import com.example.solitaryhelper.database.api.naver.dataholder.NaverBlogSearchResult
import com.example.solitaryhelper.database.api.naver.dataholder.NaverNews
import com.example.solitaryhelper.database.api.naver.dataholder.NaverNewsSearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query

interface NaverApiDataSource {
    suspend fun searchBlog(
        query: String, display: Int = 10, sort: String = "sim"): List<NaverBlog>

    suspend fun searchNews(
        query: String, display: Int = 20, sort:String = "sim"): List<NaverNews>

}

class NaverApiRepository: NaverApiDataSource {
    override suspend fun searchBlog(
        query: String,
        display: Int,
        sort: String
    ): List<NaverBlog> {
        return NaverApiClient.api.searchBlog(query,display, sort).items
    }

    override suspend fun searchNews(
        query: String,
        display: Int,
        sort: String
    ): List<NaverNews> {
        return NaverApiClient.api.searchNews(query,display, sort).items
    }
}