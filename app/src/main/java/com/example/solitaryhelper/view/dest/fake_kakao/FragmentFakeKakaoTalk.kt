package com.example.solitaryhelper.view.dest.fake_kakao

import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoTalkBinding
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoTalk
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {
    private var roomSeletCount = 0

    companion object {
        var itemOrderList =
            arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)

    }

    private var newMessgeCheck = false
    override fun FragmentFakeKakaoTalkBinding.setEventListener() {}

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {

        setImageViewAd()
        setProgrssControl()
        setRecyclerViewSetting()
    }


    override fun FragmentFakeKakaoTalkBinding.setLiveDataInObserver() {
        setAdapterDataUpdate()
        setNewMessageResponse()
    }

    //랜덤 광고사진 가져오기
    private fun setImageViewAd() {
        val imageList = Array(3) { "" }
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

            val visibleSettingList: Array<Int>
            var timeList: Array<Array<String>>?
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


            itemLastText = Array(kaKaoTextList.size) { "" }
            chatNotification = Array(kaKaoTextList.size) { 0 }

            visibleSettingList = Array(kaKaoTextList.size) { 0 }


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
                kaKaoImageProfileList[i] =
                    (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                        "newsample$i",
                        "drawable",
                        requireActivity().packageName
                    ).toString())

                Log.d("opop111111", "${kakaoDataList.size}")
                timeList = viewModelKaKaoTalk.setTimeList(kaKaoTextList)


                kakaoDataList.add(
                    KaKaoTalkData(
                        i.toLong(),
                        kaKaoNameList[i],
                        kaKaoImageProfileList[i],
                        kaKaoTextList[i].toMutableList(),
                        itemLastText[i],
                        chatNotification[i],
                        timeList[i].toList(),
                        visibleSettingList[i],
                        timeList[i].last()
                    )
                )
            }

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

        viewModelKaKaoTalk.allListInsert(setCreateAnItemToSendToTheAdapter())
    }


//    private fun setBackButtonListener() {
//
//        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            findNavController().navigate(R.id.action_fragmentFakeKakaoTalk_to_mainFragment)
//        }
//
//    }

    private fun FragmentFakeKakaoTalkBinding.setAdapterDataUpdate() {

        viewModelKaKaoTalk.kakaoData.observe(
            requireActivity(),
            Observer {

                if (it.isNullOrEmpty()) {
                    setSendToAdapterToData()
                    return@Observer
                }
//                if(newMessgeCheck) return@Observer

                recyclerViewKaKaoChatList.adapter = AdapterRecyclerViewKaKaoTalk(it.toMutableList())
                { position ->
                    (recyclerViewKaKaoChatList.adapter as
                            AdapterRecyclerViewKaKaoTalk).apply {
                        ++roomSeletCount
                        this.kaKaoDataList[position].apply {
                            visibleSettingList = View.GONE
                            chatNotification = 0

                        }
                    }
                    val id = it[position].id.toInt()
                    findNavController().navigate(
                        FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                            profileImage = it[id].image,
                            name = it[id].name,
                            ListBox = it[id].textBoxList.toTypedArray(),
                            itemIdPosition = id.toLong(),
                            selectChatRoomCount = roomSeletCount,
                            timeList = it[id].messageArrivalTime!!.toTypedArray()
                        )
                    )
                }
                PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck = true

            })

    }

    private fun FragmentFakeKakaoTalkBinding.setNewMessageResponse() {

        viewModelShared.sendToChanges.observe(requireActivity(),
            androidx.lifecycle.Observer { changed ->
                if (recyclerViewKaKaoChatList.adapter == null)
                    return@Observer
                (recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {

                    for (i in itemOrderList.indices) {
                        itemOrderList[i] = kaKaoDataList[i].id.toInt()
                    }

                    if (changed.sendToPosition != 0)
                        notifyItemMoved(changed.sendToPosition, 0)

                    val index = itemOrderList.indexOf(changed.sendToPosition)

                    this.kaKaoDataList.add(0, this.kaKaoDataList[index])

                    this.kaKaoDataList[0].apply {
                        itemLastText = changed.sendToLastText
                        itemTimeLast = changed.sendToLastTime
                        chatNotification += 1
                        visibleSettingList = View.VISIBLE
                    }
                    this.kaKaoDataList.removeAt(index + 1)

                    notifyItemRangeChanged(0, 5)
                    viewModelShared.kaKaoChatTotalNotificationScore(this.kaKaoDataList.sumBy
                    { list -> list.chatNotification })

                    Log.d("opop989898", changed.sendToLastTime)

                    newMessgeCheck = true

                }

            })
    }

    override fun onPause() {
        viewModelKaKaoTalk.allListInsert((binding.recyclerViewKaKaoChatList.adapter as
                AdapterRecyclerViewKaKaoTalk).kaKaoDataList)
        super.onPause()
    }


}
