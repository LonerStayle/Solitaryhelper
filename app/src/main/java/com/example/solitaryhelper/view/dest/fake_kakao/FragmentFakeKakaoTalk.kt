package com.example.solitaryhelper.view.dest.fake_kakao

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
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

    companion object {
        var itemOrderList =
            arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
    }


    override fun FragmentFakeKakaoTalkBinding.setEventListener() {}

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {

        setWindowUIControl()
        setImageViewAd()
        setProgrssControl()
        setRecyclerViewSetting()
    }

    override fun FragmentFakeKakaoTalkBinding.setLiveDataInObserver() {
        setAdapterDataUpdate()
        setNewMessageResponse()
    }

    private fun setWindowUIControl() {
        requireActivity().window.statusBarColor = Color.BLACK
        requireActivity().window.navigationBarColor = Color.BLACK
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

                for(i in 0 until rootViewKaKaoTalk.childCount-1){
                    rootViewKaKaoTalk.getChildAt(i).visibility = View.GONE
                    progressbarLayout.visibility = View.VISIBLE
                }

                delay(3500)
                for(i in 0 until rootViewKaKaoTalk.childCount-1){
                    rootViewKaKaoTalk.getChildAt(i).visibility = View.VISIBLE
                    progressbarLayout.visibility = View.GONE
                }

                PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck = true
            }
        }
    }

    private fun FragmentFakeKakaoTalkBinding.setRecyclerViewSetting() {
        recyclerViewKaKaoChatList.disableItemAnimator()
        recyclerViewKaKaoChatList.setHasFixedSize(true)
    }


    private fun setSendToAdapterToData() {

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
                    chatNotification[i] = 0;View.GONE
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
        viewModelKaKaoTalk.allListInsert(kakaoDataList.toMutableList())


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
    }


    private fun FragmentFakeKakaoTalkBinding.setAdapterDataUpdate() {

        viewModelKaKaoTalk.kakaoData.observe(
            requireActivity(),
            Observer {

                if (it.isNullOrEmpty()) {
                    setSendToAdapterToData()
                    return@Observer
                }

                //채팅 발생시 다른 옵저버에서 리싸 상태 변경하고 livedata 업데이트 할때
                if(isResumed && recyclerViewKaKaoChatList.adapter != null)
                    return@Observer



                recyclerViewKaKaoChatList.adapter = AdapterRecyclerViewKaKaoTalk(it.toMutableList())
                { position ->

                    val adapter = recyclerViewKaKaoChatList.adapter as
                            AdapterRecyclerViewKaKaoTalk

                    adapter.apply {

                        this.kaKaoDataList[position].apply {
                            visibleSettingList = View.GONE
                            chatNotification = 0
                        }
                        viewModelKaKaoTalk.allListInsert(kaKaoDataList)
                    }
                    with(adapter) {

                        findNavController().navigate(
                            FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                                profileImage = kaKaoDataList[position].image,
                                name = kaKaoDataList[position].name,
                                ListBox = kaKaoDataList[kaKaoDataList[position].id.toInt()].textBoxList.toTypedArray(),
                                itemIdPosition = kaKaoDataList[position].id,
                                timeList = kaKaoDataList[kaKaoDataList[position].id.toInt()].messageArrivalTime!!.toTypedArray()
                            )
                        )
                    }
                }


            })

    }

    private fun FragmentFakeKakaoTalkBinding.setNewMessageResponse() {

        viewModelShared.sendToChanges.observe(requireActivity(),
            androidx.lifecycle.Observer { changed ->
                //adapter down casting 오류 방지
                if (recyclerViewKaKaoChatList.adapter == null)
                    return@Observer
                (recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {

                    for (i in itemOrderList.indices) {
                        itemOrderList[i] = kaKaoDataList[i].id.toInt()
                    }
                    val index = itemOrderList.indexOf(changed.sendToPosition)

//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                            this.kaKaoDataList.removeIf { i -> i == deleteIndex }
//                        } else {

                    val deleteIndex = kaKaoDataList[index]
                    val iterator = kaKaoDataList.iterator()
                    while (iterator.hasNext()) {
                        val item = iterator.next()
                        if (item == deleteIndex) {
                            iterator.remove()
                        }
                }
                    this.kaKaoDataList.add(0, deleteIndex)

                    this.kaKaoDataList[0].apply {
                        itemLastText = changed.sendToLastText
                        itemTimeLast = changed.sendToLastTime
                        chatNotification += 1
                        visibleSettingList = View.VISIBLE
                    }

                    notifyItemMoved(index, 0)

//                        }


//                    this.kaKaoDataList.removeAt(index + 1)

                    notifyItemRangeChanged(0, 5)
                    viewModelShared.kaKaoChatTotalNotificationScore(this.kaKaoDataList.sumBy
                    { list -> list.chatNotification })

                    Log.d("opop989898", changed.sendToLastTime)


                    viewModelKaKaoTalk.allListInsert(kaKaoDataList)
                }

            })

    }

    override fun onPause() {
        viewModelKaKaoTalk.allListInsert(
            (binding.recyclerViewKaKaoChatList.adapter
                    as AdapterRecyclerViewKaKaoTalk).kaKaoDataList
        )
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("test","온데스토리로 죽는건가? ")
    }

}
