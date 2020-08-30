package com.example.solitaryhelper.view.dest.topics

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.example.solitaryhelper.R

import com.example.solitaryhelper.databinding.FragmentTopicResultBinding
import com.example.solitaryhelper.networkdb.naver.dataholder.NaverBlog
import com.example.solitaryhelper.networkdb.naver.dataholder.NaverNews
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewTopicResult
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents

import com.example.solitaryhelper.view.contents.Contents.topicTimeOnlyYear
import com.example.solitaryhelper.view.contents.Contents.topicTimeYearMonth
import com.example.solitaryhelper.view.utill.toPicTextControl
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.example.solitaryhelper.view.utill.topicTimeDisplay
import kotlinx.android.synthetic.main.fragment_topic_result.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

import java.util.*
import kotlin.properties.Delegates


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

    private val dp by lazy { resources.displayMetrics.density }
    private val recyclerViewAnim by lazy {
        AnimationUtils.loadAnimation(
            requireContext()
            , R.anim.topic_recyclerview
        )
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
    private var apiSetComplete = false

    override fun FragmentTopicResultBinding.setEventListener() {
        imageViewNewsClickListener()
    }

    override fun FragmentTopicResultBinding.setCreateView() {
        context?.toastDebugTest("UI는 아직 준비중 입니다.")
        setCurrentAgeGroups()
        setApi {
            setData(it)
        }
        setTime()
    }


    override fun FragmentTopicResultBinding.setLiveDataInObserver() {

        setNewsTrendObserver()
        setBlogObserver()
    }

    private fun FragmentTopicResultBinding.setTime() {
        textViewCurrentTime.text =
            SimpleDateFormat("yyyy년 MM월 dd일 기준", Locale.KOREAN).format(Date())
    }

    private fun FragmentTopicResultBinding.setNewsTrendObserver() {

        viewModelTopic.newsTrend.observe(viewLifecycleOwner, Observer { list ->
            val shuffled = list.shuffled()
            newsData = shuffled[0]
            newsTitle = toPicTextControl(newsData!!.title)
            newsText = toPicTextControl(newsData!!.description)

        })
    }


    private fun setBlogObserver() {

        viewModelTopic.blogMovie.observe(viewLifecycleOwner, Observer {
            val shuffled = it.shuffled()
            blogList.add(shuffled[0])


        })
        viewModelTopic.blogMusic.observe(viewLifecycleOwner, Observer {
            val shuffled = it.shuffled()
            blogList.add(shuffled[0])

        })
        viewModelTopic.blogFashion.observe(viewLifecycleOwner, Observer {
            blogList.add(it.shuffled()[0])
            blogList.add(it.shuffled()[1])
            blogList.add(it.shuffled()[2])
        })


        viewModelTopic.blogStudentManFashion.observe(viewLifecycleOwner, Observer {

            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == YOUNG_STUDENT_MAN) {
                blogList.add(it.shuffled()[0])
            } else
                return@Observer
        })
        viewModelTopic.blogStudentGirlFashion.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == YOUNG_STUDENT_GIRL) {
                blogList.add(it.shuffled()[0])
            } else
                return@Observer

        })

        viewModelTopic.blogYoungManHair.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == YOUNG_STUDENT_MAN ||
                currentAgeGroup == YOUTH_MAN
            ) {
                blogList.add(it.shuffled()[0])
            } else
                return@Observer


        })
        viewModelTopic.blogYoungGirlHair.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == YOUNG_STUDENT_GIRL ||
                currentAgeGroup == YOUTH_GIRL
            ) {
                blogList.add(it.shuffled()[0])
            } else
                return@Observer

        })

        viewModelTopic.blogYoungManFashion.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == YOUTH_MAN) {
                blogList.add(it.shuffled()[0])

            } else
                return@Observer
        })

        viewModelTopic.blogYoungGirlFashion.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == YOUTH_GIRL) {
                blogList.add(it.shuffled()[0])

            } else
                return@Observer
        })
        viewModelTopic.blogOldManFashion.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == MIDDLE_AGE_MAN) {
                blogList.add(it.shuffled()[0])
                blogList.add(it.shuffled()[1])
            } else
                return@Observer
        })
        viewModelTopic.blogOldGirlFashion.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            if (currentAgeGroup == MIDDLE_AGE_GIRL) {
                blogList.add(it.shuffled()[0])
                blogList.add(it.shuffled()[1])
            } else
                return@Observer
        })
    }

    private fun FragmentTopicResultBinding.imageViewNewsClickListener() {

        imageViewNews.setOnClickListener {
            imageClickEvnetLogic(newsData!!.originallink)
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

    }

    private fun setApi(setApiCompleteAfter: (Boolean) -> Unit) {


        viewModelTopic.insertNaverNewsSearch()
        viewModelTopic.insertBlogFashion(topicTimeDisplay(topicTimeYearMonth.format(Date())))
        viewModelTopic.insertBlogMusic(topicTimeDisplay(topicTimeYearMonth.format(Date())))
        viewModelTopic.insertBlogMovie(topicTimeDisplay(topicTimeYearMonth.format(Date())))

        when (currentAgeGroup) {
            YOUNG_STUDENT_MAN -> {
                viewModelTopic.insertBlogStudentManFashion()
                viewModelTopic.insertBlogYoungManHair(
                    topicTimeDisplay(
                        topicTimeYearMonth.format(
                            Date()
                        )
                    )
                )
            }
            YOUNG_STUDENT_GIRL -> {
                viewModelTopic.insertBlogStudentGirlFashion()
                viewModelTopic.insertBlogYoungGirlHair(
                    topicTimeDisplay(
                        topicTimeYearMonth.format(
                            Date()
                        )
                    )
                )
            }
            YOUTH_MAN -> {
                viewModelTopic.insertBlogYoungManHair(
                    topicTimeDisplay(
                        topicTimeYearMonth.format(
                            Date()
                        )
                    )
                )
                viewModelTopic.insertBlogYoungManFashion(topicTimeOnlyYear.format(Date()))
            }
            YOUTH_GIRL -> {
                viewModelTopic.insertBlogYoungGirlHair(
                    topicTimeDisplay(
                        topicTimeYearMonth.format(
                            Date()
                        )
                    )
                )
                viewModelTopic.insertBlogYoungGirlFashion(topicTimeOnlyYear.format(Date()))
            }
            MIDDLE_AGE_MAN -> viewModelTopic.insertBlogOldMANFashion()

            MIDDLE_AGE_GIRL -> viewModelTopic.insertBlogOldGirlFashion()

        }

        apiSetComplete = true
        setApiCompleteAfter(apiSetComplete)

    }

    private fun FragmentTopicResultBinding.setData(apiSetComplete: Boolean) {
        imageUrl = (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
            "topicnews", "drawable",
            requireActivity().packageName
        ).toString())
        if (apiSetComplete) {
            CoroutineScope(Dispatchers.Main).launch {


                delay(2600L)
                root.setBackgroundColor(Color.parseColor("#000000"))
                imageList = Array(blogList.size) { "" }

                var i = 0
                while (i < blogList.size) {
                    imageList!![i] =
                        (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                            "topic$i", "drawable",
                            requireActivity().packageName
                        ).toString())
                    i++
                }

                binding.recyclerViewTopicResult.adapter =
                    AdapterRecyclerViewTopicResult(blogList, imageList!!) {
                        for (i in blogList.indices) {
                            when (it) {
                                i -> {
                                    imageClickEvnetLogic(blogList[i].link)
                                }
                            }
                        }
                    }
                setVisible()
                imageViewNews.animate().translationY(1000 * dp).setDuration(1L).withEndAction {
                    imageViewNews.animate().translationY(26 * dp).setDuration(3000).withEndAction {
                        recyclerViewTopicResult.visibility = View.VISIBLE

                    }
                }
            }

        }
    }

    private fun FragmentTopicResultBinding.setVisible() {
        spinKit.visibility = View.GONE
        imageViewNews.visibility = View.VISIBLE
        textViewCurrentTime.visibility = View.VISIBLE
        textViewData.visibility = View.VISIBLE
        textViewNewsText.visibility = View.VISIBLE
        textViewNewsTitle.visibility = View.VISIBLE
    }

    private fun imageClickEvnetLogic(link: String) {
        val url = link
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}