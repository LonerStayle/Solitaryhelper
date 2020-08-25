package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.*
import com.example.solitaryhelper.networkdb.naver.NaverApiClient
import com.example.solitaryhelper.networkdb.naver.dataholder.NaverBlog
import com.example.solitaryhelper.networkdb.naver.dataholder.NaverNews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopicViewModel : ViewModel() {

    private val _blogMovie = MutableLiveData<List<NaverBlog>>()
    val blogMovie: LiveData<List<NaverBlog>>
        get() = _blogMovie

    private val _blogMusic = MutableLiveData<List<NaverBlog>>()
    val blogMusic: LiveData<List<NaverBlog>>
        get() = _blogMusic

    private val _blogFashion = MutableLiveData<List<NaverBlog>>()
    val blogFashion: LiveData<List<NaverBlog>>
        get() = _blogFashion

    private val _blogYoungManFashion = MutableLiveData<List<NaverBlog>>()
    val blogYoungManFashion: LiveData<List<NaverBlog>>
        get() = _blogYoungManFashion

    private val _blogYoungGirlFashion = MutableLiveData<List<NaverBlog>>()
    val blogYoungGirlFashion: LiveData<List<NaverBlog>>
        get() = _blogYoungGirlFashion

    private val _blogYoungManHair = MutableLiveData<List<NaverBlog>>()
    val blogYoungManHair: LiveData<List<NaverBlog>>
        get() = _blogYoungManHair

    private val _blogYoungGirlHair = MutableLiveData<List<NaverBlog>>()
    val blogYoungGirlHair: LiveData<List<NaverBlog>>
        get() = _blogYoungGirlHair

    private val _blogOldManFashion = MutableLiveData<List<NaverBlog>>()
    val blogYoungOldManFashion: LiveData<List<NaverBlog>>
        get() = _blogOldManFashion

    private val _blogOldGirlFashion = MutableLiveData<List<NaverBlog>>()
    val blogOldGirlFashion: LiveData<List<NaverBlog>>
        get() = _blogOldGirlFashion

    private val _blogStudentManFashion = MutableLiveData<List<NaverBlog>>()
    val blogStudentManFashion: LiveData<List<NaverBlog>>
        get() = _blogStudentManFashion

    private val _blogStudentGirlFashion = MutableLiveData<List<NaverBlog>>()
    val blogStudentGirlFashion: LiveData<List<NaverBlog>>
        get() = _blogStudentGirlFashion

    private val _newsTrend = MutableLiveData<List<NaverNews>>()
    val newsTrend: LiveData<List<NaverNews>>
        get() = _newsTrend


    fun insertBlogMovie(movie: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = movie).item
            _blogMovie.postValue(movie)
            //2020년 8월 최신 영화 상영작
        }
    }

    fun insertBlogMusic(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music).item
            _blogMusic.postValue(movie)

//            멜론 2020년 8월 노래 듣기
        }
    }

    fun insertBlogFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music).item
            _blogFashion.postValue(movie)

//    2020년 8월 패션 트렌드
        }
    }


    fun insertBlogYoungManFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music, sort = "date").item
            _blogYoungManFashion.postValue(movie)

//      2020년 남자 옷 추천
        }
    }

    fun insertBlogYoungGirlFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music, sort = "date").item
            _blogYoungManFashion.postValue(movie)

//      2020년 여자 옷 추천
        }
    }


    fun insertBlogYoungManHair(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music, sort = "sim").item
            _blogYoungManHair.postValue(movie)

//       2020년 8월 남자 헤어스타일 추천
        }
    }

    fun insertBlogYoungGirlHair(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music, sort = "sim").item
            _blogYoungGirlFashion.postValue(movie)

//     2020년 8월 여자 헤어스타일 추천
        }
    }

    fun insertBlogOldMANFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music, sort = "sim").item
            _blogOldManFashion.postValue(movie)

//     중년 남성 옷
        }
    }

    fun insertBlogOldGirlFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = NaverApiClient.api.searchBlog(query = music, sort = "sim").item
            _blogOldGirlFashion.postValue(movie)

//     중년 여성 옷
        }
    }

    fun insertBlogStudentManFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie =
                NaverApiClient.api.searchBlog(query = music, display = 100, sort = "sim").item
            _blogStudentManFashion.postValue(movie)

//     10대 남성 옷
        }
    }

    fun insertBlogStudentGirlFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie =
                NaverApiClient.api.searchBlog(query = music, display = 100, sort = "sim").item
            _blogStudentGirlFashion.postValue(movie)

            // 10대 여성 옷
        }
    }

    fun insertNaverNewsSearch(news:String) {

        CoroutineScope(Dispatchers.IO).launch {
            val movie =
                NaverApiClient.api.searchNews(query = news, display = 100, sort = "sim").items
            _newsTrend.postValue(movie)

            //     재미 유행 인기 예능

        }

    }

}