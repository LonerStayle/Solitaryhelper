package com.example.solitaryhelper.view.dest.fake_kakao

import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoTalkBinding
import com.example.solitaryhelper.view.dataclass.KaKaoTalkData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoTalk
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import java.util.Collections.swap
import java.util.logging.Handler
import kotlin.random.Random

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {
    private var noticeitemPositionChange = false
    private var roomSeletCount = 0

    companion object {
        var itemOrderList =
            arrayOf(0, 1, 2, 3, 4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)
    }

    override fun FragmentFakeKakaoTalkBinding.setEventListener() {
        setBackButtonListener()
    }

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {
        setImageViewAd()
        setProgrssControl()
        setSendToAdapterToData()
        setNewMessageResponse()
        setRecyclerViewSetting()
    }



    override fun FragmentFakeKakaoTalkBinding.setLiveDataInObserver() {
        setAdapterDataUpdate()

    }

    //랜덤 광고사진 가져오기
    private fun setImageViewAd() {
        val imageList = Array<String> (3){""}
        for (i in 0..2) {
            imageList[i] = (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "kakao_ad${i}",
                "drawable",
                requireActivity().packageName
            ).toString())
        }
        binding.adImage = imageList.toList().shuffled()[0]
    }

    private fun FragmentFakeKakaoTalkBinding.setProgrssControl() {
        if (!PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck) {
            CoroutineScope(Dispatchers.Main).launch {

                progressbarLayout.visibility = View.VISIBLE
                imageViewKaKao.visibility = View.VISIBLE

                bottomLayout.visibility = View.GONE
                viewLine.visibility = View.GONE
                recyclerViewKaKaoChatList.visibility = View.GONE
                imageViewAd.visibility = View.GONE
                textView2.visibility = View.GONE
                linearLayoutButtonList.visibility = View.GONE

                delay(2000)
                progressbarLayout.visibility = View.GONE
                imageViewKaKao.visibility = View.GONE
                bottomLayout.visibility = View.VISIBLE
                viewLine.visibility = View.VISIBLE
                recyclerViewKaKaoChatList.visibility = View.VISIBLE
                imageViewAd.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
                linearLayoutButtonList.visibility = View.VISIBLE
            }
        }
    }

    private fun FragmentFakeKakaoTalkBinding.setRecyclerViewSetting() {
        recyclerViewKaKaoChatList.disableItemAnimator()
        recyclerViewKaKaoChatList.setHasFixedSize(true)
    }

    private fun setSendToAdapterToData() {


        fun setCreateAnItemToSendToTheAdapter(): MutableList<KaKaoTalkData> {
            val kaKaoNameList by lazy { resources.getStringArray(R.array.sample_name) }
            val kaKaoImageProfileList: Array<String>?
            val itemLastText: Array<String>?
            val chatNotification: Array<Int>?
            val lastTime: Array<String>?
            val visibleSettingList: Array<Int>
            val timeList: Array<Array<String>>?
            itemLastText = Array(kaKaoNameList.size) { "" }
            chatNotification = Array(kaKaoNameList.size) { 0 }
            lastTime = Array(kaKaoNameList.size) { "" }
            visibleSettingList = Array(kaKaoNameList.size) { 0 }

            val kaKaoTextList = arrayOf(
                resources.getStringArray(R.array.sample_list1),
                resources.getStringArray(R.array.sample_list2),
                resources.getStringArray(R.array.sample_list3),
                resources.getStringArray(R.array.sample_list4),
                resources.getStringArray(R.array.sample_list5),

                resources.getStringArray(R.array.sample_list6),
                resources.getStringArray(R.array.sample_list7),
                resources.getStringArray(R.array.sample_list8),
                resources.getStringArray(R.array.sample_list9),
                resources.getStringArray(R.array.sample_list10),

                resources.getStringArray(R.array.sample_list11),
                resources.getStringArray(R.array.sample_list12),
                resources.getStringArray(R.array.sample_list13),
                resources.getStringArray(R.array.sample_list14),
                resources.getStringArray(R.array.sample_list15),

                resources.getStringArray(R.array.sample_list16),
                resources.getStringArray(R.array.sample_list17),
                resources.getStringArray(R.array.sample_list18),
                resources.getStringArray(R.array.sample_list19),
                resources.getStringArray(R.array.sample_list20)
            )

            fun visibleRandomCrate(i: Int): Int {
                return when (Random.nextInt(2)) {
                    0 -> {
                        chatNotification[i] = 0
                        View.GONE
                    }
                    1 -> View.VISIBLE
                    else -> View.GONE
                }
            }

            for (i in kaKaoTextList.indices) {
                itemLastText[i] = kaKaoTextList[i].last()
                chatNotification[i] = kaKaoTextList[i].size
                visibleSettingList[i] = visibleRandomCrate(i)
            }


            val kakaoDataList = mutableListOf<KaKaoTalkData>()
            kaKaoImageProfileList = Array(kaKaoTextList.size) { "" }
            for (i in kaKaoTextList.indices) {
                kaKaoImageProfileList[i] = (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                    "sample$i",
                    "drawable",
                    requireActivity().packageName
                ).toString())

                kakaoDataList.add(
                    KaKaoTalkData(
                        i.toLong(),
                        kaKaoNameList[i],
                        kaKaoImageProfileList[i],
                        kaKaoTextList,
                        itemLastText[i],
                        chatNotification[i],
                        null,
                        visibleSettingList[i],
                        null
                    )
                )
            }

            timeList = viewModelKaKaoTalk.setTimeList(kakaoDataList.toList())

            for (i in timeList.indices) {
                kakaoDataList[i].messageArrivalTime = timeList
            }


            for (i in kakaoDataList.indices) {
                lastTime[i] = kakaoDataList[i].messageArrivalTime?.get(i)?.last()!!
                kakaoDataList[i].itemTimeLast = lastTime[i]
            }
            PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck = true

            return kakaoDataList.toMutableList()

//            timeList = viewModelKaKaoTalk.setTimeList(kakaoDataList.toList())
//
//            for (i in timeList.indices) {
//                kakaoDataList[i].messageArrivalTime = timeList
//            }
//            val shuffleMode = kakaoDataList.shuffled()
//            shuffleMode.sortedBy { it.id }
//            PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck = true
//
//            for (i in kakaoDataList.indices) {
//                lastTime[i] = shuffleMode[i].messageArrivalTime?.get(i)?.last()!!
//                shuffleMode[i].itemTimeLast = lastTime[i]
//            }
//
//            return shuffleMode.toMutableList()
        }
        if (!PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck) {
            val dataList = setCreateAnItemToSendToTheAdapter()

            viewModelShared.firstRunKaKaoTalkSetting(dataList)
        }
    }

    private fun setAdapterDataUpdate() {
        viewModelShared.firstRunKaKaoTalkClass.observe(
            viewLifecycleOwner,
            Observer {

                if (!noticeitemPositionChange) {


                    binding.recyclerViewKaKaoChatList.adapter =
                        AdapterRecyclerViewKaKaoTalk(it)
                        { position ->

                            (binding.recyclerViewKaKaoChatList.adapter as
                                    AdapterRecyclerViewKaKaoTalk).apply {
                                swap(this.kaKaoDataList, 0, position)
                                ++roomSeletCount

                                this.kaKaoDataList[0].visibleSettingList = View.GONE
                                this.kaKaoDataList[0].chatNotification = 0

                                viewModelShared.kaKaoChatTotalNotificationScore(this.kaKaoDataList.sumBy
                                { list -> list.chatNotification })

                            }

                            findNavController().navigate(
                                FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                                    profileImage = it[0].image,
                                    name = it[0].name,
                                    ListBox = it[0].textBoxList[it[0].id.toInt()],
                                    itemIdPosition = it[0].id,
                                    selectChatRoomCount = roomSeletCount,
                                    timeList = it[0].messageArrivalTime?.get(position)!!
                                )
                            )

                            (binding.recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {
                                swap(this.kaKaoDataList, 0, position)

                            }
                        }


                } else {

                    noticeitemPositionChange = false
                    return@Observer
                }
            })

    }


    private fun setBackButtonListener() {

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_fragmentFakeKakaoTalk_to_mainFragment)
        }

    }


    private fun FragmentFakeKakaoTalkBinding.setNewMessageResponse() {


        viewModelShared.sendToChanges.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { changed ->

                (recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {

                    if (FragmentFakeKakaoChat.positionSendRunCheck!!) {

                        for (i in itemOrderList.indices) {
                            itemOrderList[i] = kaKaoDataList[i].id.toInt()
                        }

                        if (changed.sendToPosition != 0)
                            notifyItemMoved(changed.sendToPosition, 0)

                        val index = itemOrderList.indexOf(changed.sendToPosition)

                        this.kaKaoDataList.add(0, this.kaKaoDataList[index])

                        this.kaKaoDataList[0].itemLastText = changed.sendToLastText
                        this.kaKaoDataList[0].itemTimeLast = changed.sendToLastTime
                        this.kaKaoDataList[0].chatNotification += 1
                        this.kaKaoDataList[0].visibleSettingList = View.VISIBLE

                        this.kaKaoDataList.removeAt(index + 1)

                        notifyItemRangeChanged(0, 5)
                        viewModelShared.kaKaoChatTotalNotificationScore(this.kaKaoDataList.sumBy
                        { list -> list.chatNotification })

                        viewModelShared.firstRunKaKaoTalkSetting(this.kaKaoDataList)

                        noticeitemPositionChange = true
                        FragmentFakeKakaoChat.positionSendRunCheck = false

                    }

                }

            })
    }


}

