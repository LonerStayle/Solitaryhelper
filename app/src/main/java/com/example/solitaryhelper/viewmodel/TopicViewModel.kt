package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.database.api.naver.dataholder.NaverBlog
import com.example.solitaryhelper.database.api.naver.dataholder.NaverNews
import com.example.solitaryhelper.repository.NaverApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopicViewModel(private val repository: NaverApiRepository) : ViewModel() {

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
    val blogOldManFashion: LiveData<List<NaverBlog>>
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

            val movie = repository.searchBlog(query = "$movie 최신 영화 상영작")
            _blogMovie.postValue(movie)

        }
    }

    fun insertBlogMusic(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val music = repository.searchBlog(query = "멜론 $music 노래 듣기")
            _blogMusic.postValue(music)


        }
    }

    fun insertBlogFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val fashionTrend = repository.searchBlog(query = "$music 패션 트렌드")
            _blogFashion.postValue(fashionTrend)

//    2020년 8월 패션 트렌드
        }
    }


    fun insertBlogYoungManFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val youngManFashion = repository.searchBlog(query = "$music 남자 옷 추천")
            _blogYoungManFashion.postValue(youngManFashion)

//      2020년 남자 옷 추천
        }
    }

    fun insertBlogYoungGirlFashion(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val youngGirlFashion = repository.searchBlog(query = "$music 여자 옷 추천")
            _blogYoungManFashion.postValue(youngGirlFashion)

//      2020년 여자 옷 추천
        }
    }


    fun insertBlogYoungManHair(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val youngManHair = repository.searchBlog(query = "$music 남자 헤어스타일 추천")
            _blogYoungManHair.postValue(youngManHair)

//       2020년 8월 남자 헤어스타일 추천
        }
    }

    fun insertBlogYoungGirlHair(music: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val youngGirlHair =
                repository.searchBlog(query = "$music 여자 헤어스타일 추천")
            _blogYoungGirlFashion.postValue(youngGirlHair)

//     2020년 8월 여자 헤어스타일 추천
        }
    }

    fun insertBlogOldMANFashion() {
        CoroutineScope(Dispatchers.IO).launch {
            val oldMANFashion = repository.searchBlog(query = "중년 남성옷")
            _blogOldManFashion.postValue(oldMANFashion)

//     중년 남성 옷
        }
    }

    fun insertBlogOldGirlFashion() {
        CoroutineScope(Dispatchers.IO).launch {
            val oldGirlFashion = repository.searchBlog(query = "중년 여성옷")
            _blogOldGirlFashion.postValue(oldGirlFashion)

//     중년 여성 옷
        }
    }

    fun insertBlogStudentManFashion() {
        CoroutineScope(Dispatchers.IO).launch {
            val studentManFashion =
                repository.searchBlog(query = "10대 남성 옷", display = 100)
            _blogStudentManFashion.postValue(studentManFashion)

//     10대 남성 옷
        }
    }

    fun insertBlogStudentGirlFashion() {
        CoroutineScope(Dispatchers.IO).launch {
            val studentGirlFashion =
                repository.searchBlog(query = "10대 여성 옷", display = 100)
            _blogStudentGirlFashion.postValue(studentGirlFashion)

            // 10대 여성 옷
        }
    }

    fun insertNaverNewsSearch() {
        CoroutineScope(Dispatchers.IO).launch {
            val naverNewsSearch =
                repository.searchNews(
                    query = "재미 유행 인기 예능",
                    display = 100
                )
            _newsTrend.postValue(naverNewsSearch)

            //     재미 유행 인기 예능
        }

    }

}
