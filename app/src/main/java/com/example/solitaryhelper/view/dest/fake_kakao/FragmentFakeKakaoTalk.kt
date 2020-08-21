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
import com.example.solitaryhelper.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections.swap
import kotlin.random.Random

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {
    private var noticeitemPositionChange = false
    private var roomSeletCount = 0

    companion object {
        var itemOrderList =
            arrayOf(0, 1, 2, 3, 4)
    }

    override fun FragmentFakeKakaoTalkBinding.setEventListener() {
        setBackButtonListener()
    }

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {
        setSendToAdapterToData()
        setNewMessageResponse()
    }

    override fun FragmentFakeKakaoTalkBinding.setLiveDataInObserver() {
        setAdapterDataUpdate()
    }

    private fun setAdapterDataUpdate() {
        viewModelShared.firstRunKaKaoTalkClass.observe(
            viewLifecycleOwner,
            Observer {

                if (!noticeitemPositionChange) {


                    binding.recyclerViewKaKaoChatList.adapter =
                        AdapterRecyclerViewKaKaoTalk(it)
                        { position ->

                            (binding.recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {
                                swap(this.kaKaoDataList, 0, position)
                                ++roomSeletCount

                                this.kaKaoDataList[0].visibleSettingList = View.GONE
                                this.kaKaoDataList[0].chatNotification = 0
                            }

                            findNavController().navigate(
                                FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                                    profileImage = it[0].image,
                                    name = it[0].name,
                                    ListBox = it[0].textBoxList[it[0].id.toInt()],
                                    itemIdPosition = it[0].id,
                                    selectChatRoomCount = roomSeletCount,
                                    timeList = it[0].messageArrivalTime?.get(it[0].id.toInt())!!
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


    private fun setSendToAdapterToData() {


        fun setCreateAnItemToSendToTheAdapter(): MutableList<KaKaoTalkData> {
            val kaKaoNameList by lazy { resources.getStringArray(R.array.sample_list1) }
            val kaKaoImageProfileList: Array<String>?
            val itemLastText: Array<String>?
            val chatNotification: Array<Int>?
            val lastTime: Array<String>?
            val visibleSettingList: Array<Int>
            var timeList: Array<Array<String>>?
            itemLastText = Array(kaKaoNameList.size) { "" }
            chatNotification = Array(kaKaoNameList.size) { 0 }
            lastTime = Array(kaKaoNameList.size) { "" }
            visibleSettingList = Array(kaKaoNameList.size) { 0 }

            val kaKaoTextList = arrayOf(

                resources.getStringArray(R.array.sample_list1),
                resources.getStringArray(R.array.sample_list2),
                resources.getStringArray(R.array.sample_list3),
                resources.getStringArray(R.array.sample_list4),
                resources.getStringArray(R.array.sample_list5)
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

            for (i in kaKaoNameList.indices) {
                itemLastText[i] = kaKaoTextList[i].last()
                chatNotification[i] = kaKaoTextList[i].size
                visibleSettingList[i] = visibleRandomCrate(i)
            }


            val kakaoDataList = mutableListOf<KaKaoTalkData>()
            kaKaoImageProfileList = Array(kaKaoNameList.size) { "" }
            for (i in kaKaoImageProfileList.indices) {
                kaKaoImageProfileList[i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                    "sample${1 + i}",
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

            val shuffleMode = kakaoDataList.shuffled()
            shuffleMode.sortedBy { it.id }
            PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck = true

            timeList = viewModelKaKaoTalk.setTimeList(shuffleMode.toList())
            for (i in timeList.indices)
                lastTime[i] = timeList[i].last().toString()

            for (i in shuffleMode.indices) {
                shuffleMode[i].messageArrivalTime = timeList
                shuffleMode[i].itemTimeLast = lastTime[i]

            }
            return shuffleMode.toMutableList()
        }
        if (!PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck) {
            val dataList = setCreateAnItemToSendToTheAdapter()

            viewModelShared.firstRunKaKaoTalkSetting(dataList)
        }
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

                    if (!FragmentFakeKakaoChat.positionCheckList!![changed.sendToPosition].positionZeroCheck &&
                        FragmentFakeKakaoChat.positionSendRunCheck!!
                    ) {
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

                        CoroutineScope(Dispatchers.IO).launch {
                            for (i in FragmentFakeKakaoChat.positionCheckList!!.indices) {
                                FragmentFakeKakaoChat.positionCheckList!![i] =
                                    SharedViewModel.ZeroPositionCheck(i, false)
                                if (i == index)
                                    FragmentFakeKakaoChat.positionCheckList?.set(
                                        index,
                                        SharedViewModel.ZeroPositionCheck(index, true)
                                    )
                            }

                        }

                        notifyItemRangeChanged(0, 5)

                        viewModelShared.firstRunKaKaoTalkSetting(this.kaKaoDataList)
                        noticeitemPositionChange = true
                        FragmentFakeKakaoChat.positionSendRunCheck = false

                    }

                }

            })
    }


}

