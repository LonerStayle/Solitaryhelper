package com.example.solitaryhelper.database.api.naver.api


import com.example.solitaryhelper.database.api.naver.dataholder.NaverBlogSearchResult
import com.example.solitaryhelper.database.api.naver.dataholder.NaverNewsSearchResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object NaverApiClient {



    private const val BASE_URL= " https://openapi.naver.com/"
    val api: NaverBlogApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())

        .build()
        .create(NaverBlogApi::class.java)

}


    interface NaverBlogApi {

        @Headers(
            "X-Naver-Client-Id:pub4rM_15_MjWrXxTr2G",
            "X-Naver-Client-Secret:A33PjCevlz"
        )


        @GET("v1/search/blog.json")
        suspend fun searchBlog(
            @Query("query", encoded = true) query: String,
            @Query("display") display: Int = 10,
            @Query("sort") sort:String = "sim"
        ): NaverBlogSearchResult

        @Headers(
            "X-Naver-Client-Id:pub4rM_15_MjWrXxTr2G",
            "X-Naver-Client-Secret:A33PjCevlz"
        )

        @GET("v1/search/news.json")
        suspend fun searchNews(
            @Query("query", encoded = true) query: String,
            @Query("display") display: Int = 20,
            @Query("sort") sort:String = "sim"
        ): NaverNewsSearchResult
    }


