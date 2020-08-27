package com.example.solitaryhelper.view.dest.topics

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentTopicGuideBinding
import com.example.solitaryhelper.databinding.FragmentTopicResultBinding
import com.example.solitaryhelper.networkdb.naver.dataholder.NaverBlog
import com.example.solitaryhelper.networkdb.naver.dataholder.NaverNews
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewTopicResult
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import kotlin.random.Random.Default.nextInt

/**
 *
10대 -> 최신영화, 최신 패션 트렌드, 최신음악, 최신 뉴스,최신 머리추천,10대 복
20~30대 -> 최신영화, 최신 패션 트렌드, 최신음악, 최신 뉴스,최신 머리추천, 최신 옷 추천
50대 -> 최신영화, 최신 패션 트렌드, 최신음악, 최신 뉴스,중년 남성 옷추천
 */


@SuppressLint("SimpleDateFormat")
class FragmentTopicResult :
    BaseFragment<FragmentTopicResultBinding>(R.layout.fragment_topic_result) {

    companion object {
        const val YOUNG_STUDENT_MAN = 10001
        const val YOUNG_STUDENT_GIRL = 10002
        const val YOUTH_MAN = 20001
        const val YOUTH_GIRL = 20002
        const val MIDDLE_AGE_MAN = 30001
        const val MIDDLE_AGE_GIRL = 30002
    }

    private var currentAgeGroup by Delegates.notNull<Int>()
    private var newsData: NaverNews? = null
    private val topicArgs by lazy {
        FragmentTopicResultArgs.fromBundle(
            requireArguments()
        )
    }

    private var blogList: MutableList<NaverBlog> = mutableListOf()
    private var imageList: Array<String>? = null


    private val timeYearMonth by lazy { SimpleDateFormat("yyyy년mm월").format(Date(System.currentTimeMillis())) }

    //영 패션만

    private val timeOnlyYear by lazy { SimpleDateFormat("yyyy년").format(Date(System.currentTimeMillis())) }

    override fun FragmentTopicResultBinding.setEventListener() {
        imageViewNewsClickListener()
    }

    override fun FragmentTopicResultBinding.setCreateView() {
        binding.imageUrl = (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
            "sample6", "drawable",
            requireActivity().packageName
        ).toString())

        setCurrentAgeGroups()
        setData()
    }


    override fun FragmentTopicResultBinding.setLiveDataInObserver() {
        setObserver()

    }


    private fun FragmentTopicResultBinding.setObserver() {


        viewModelTopic.newsTrend.observe(viewLifecycleOwner, Observer { list ->
            val shuffled = list.shuffled()
            newsData = shuffled[0]
            newsTitle = newsData!!.title
            newsText = newsData!!.description

        })
        viewModelTopic.blogMovie.observe(viewLifecycleOwner, Observer {

            blogList.add(it.shuffled()[0])


        })
        viewModelTopic.blogMusic.observe(viewLifecycleOwner, Observer {

            blogList.add(it.shuffled()[0])

        })
        viewModelTopic.blogFashion.observe(viewLifecycleOwner, Observer {

            blogList.add(it.shuffled()[0])


        })


        viewModelTopic.blogStudentManFashion.observe(viewLifecycleOwner, Observer {


            if (currentAgeGroup == YOUNG_STUDENT_MAN)
                blogList.add(it.shuffled()[0])
            else
                return@Observer


        })
        viewModelTopic.blogStudentGirlFashion.observe(viewLifecycleOwner, Observer {


            if (currentAgeGroup == YOUNG_STUDENT_GIRL)
                blogList.add(it.shuffled()[0])
            else
                return@Observer

        })

        viewModelTopic.blogYoungManHair.observe(viewLifecycleOwner, Observer {


            if (currentAgeGroup == YOUNG_STUDENT_MAN ||
                currentAgeGroup == YOUTH_MAN
            )
                blogList.add(it.shuffled()[0])
            else
                return@Observer


        })
        viewModelTopic.blogYoungGirlHair.observe(viewLifecycleOwner, Observer {

            if (currentAgeGroup == YOUNG_STUDENT_GIRL ||
                currentAgeGroup == YOUTH_GIRL
            )
                blogList.add(it.shuffled()[0])
            else
                return@Observer

        })

        viewModelTopic.blogYoungManFashion.observe(viewLifecycleOwner, Observer {

            if (currentAgeGroup == YOUTH_MAN)
                blogList.add(it.shuffled()[0])
            else
                return@Observer
        })

        viewModelTopic.blogYoungGirlFashion.observe(viewLifecycleOwner, Observer {

            if (currentAgeGroup == YOUTH_GIRL)
                blogList.add(it.shuffled()[0])
            else
                return@Observer
        })
        viewModelTopic.blogOldManFashion.observe(viewLifecycleOwner, Observer {

            if (currentAgeGroup == MIDDLE_AGE_MAN)
                blogList.add(it.shuffled()[0])
            else
                return@Observer
        })
        viewModelTopic.blogOldGirlFashion.observe(viewLifecycleOwner, Observer {

            if (currentAgeGroup == MIDDLE_AGE_GIRL)
                blogList.add(it.shuffled()[0])
            else
                return@Observer
        })
    }

    private fun FragmentTopicResultBinding.imageViewNewsClickListener() {

        imageViewNews.setOnClickListener {
            val url = newsData!!.originallink
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun setCurrentAgeGroups() {
        when {
            topicArgs.ages == "10대" && topicArgs.gender == "남성" -> currentAgeGroup =
                YOUNG_STUDENT_MAN
            topicArgs.ages == "10대" && topicArgs.gender == "여성" -> currentAgeGroup =
                YOUNG_STUDENT_GIRL
            topicArgs.ages == "20~30대" && topicArgs.gender == "남성" -> currentAgeGroup =
                YOUTH_MAN
            topicArgs.ages == "20~30대" && topicArgs.gender == "여성" -> currentAgeGroup =
                YOUTH_GIRL
            topicArgs.ages == "40~50대" && topicArgs.gender == "남성" -> currentAgeGroup =
                MIDDLE_AGE_MAN
            topicArgs.ages == "40~50대" && topicArgs.gender == "여성" -> currentAgeGroup =
                MIDDLE_AGE_GIRL
        }

        viewModelTopic.insertNaverNewsSearch()
        viewModelTopic.insertBlogFashion(timeYearMonth)
        viewModelTopic.insertBlogMusic(timeYearMonth)
        viewModelTopic.insertBlogMovie(timeYearMonth)
        when (currentAgeGroup) {
            YOUNG_STUDENT_MAN -> {
                viewModelTopic.insertBlogStudentManFashion()
                viewModelTopic.insertBlogYoungManHair(timeYearMonth)
            }
            YOUNG_STUDENT_GIRL -> {
                viewModelTopic.insertBlogStudentGirlFashion()
                viewModelTopic.insertBlogYoungGirlHair(timeYearMonth)
            }
            YOUTH_MAN -> {
                viewModelTopic.insertBlogYoungManHair(timeYearMonth)
                viewModelTopic.insertBlogYoungManFashion(timeOnlyYear)
            }
            YOUTH_GIRL -> {
                viewModelTopic.insertBlogYoungGirlHair(timeYearMonth)
                viewModelTopic.insertBlogYoungGirlFashion(timeOnlyYear)
            }
            MIDDLE_AGE_MAN -> viewModelTopic.insertBlogOldMANFashion()

            MIDDLE_AGE_GIRL -> viewModelTopic.insertBlogOldGirlFashion()

        }

    }

    private fun setData() {
        CoroutineScope(Dispatchers.IO).launch {

            delay(5000L)

            imageList = Array(blogList.size) { "" }

            for (i in blogList.indices) {
                imageList!![i] = (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                    "sample$i", "drawable",
                    requireActivity().packageName
                ).toString())
            }
            binding.recyclerViewTopicResult.adapter =
                AdapterRecyclerViewTopicResult(blogList, imageList!!)
        }
    }
}