package com.example.solitaryhelper.view.dest.fake_kakao

import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoTalkBinding
import com.example.solitaryhelper.view.dataclass.KaKaoTalkData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoTalk
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.viewmodel.SharedViewModel
import java.util.Collections.swap

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {
    private var noticeitemPositionChange = false
    private var roomSeletCount = 0

    companion object {
        var itemOrderList = arrayOf(0, 1, 2, 3, 4)
    }

    override fun FragmentFakeKakaoTalkBinding.setEventListener() {
        setBackButtonListener()
    }

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {

        setSendToAdapterToData()
        setNewMessageResponse()
    }

    override fun FragmentFakeKakaoTalkBinding.setLiveDataInObserver() {
        viewModelShared.firstRunKaKaoTalkClass.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {

                if (!noticeitemPositionChange) {


                    binding.recyclerViewKaKaoChatList.adapter =
                        AdapterRecyclerViewKaKaoTalk(it)
                        { position ->
                            (binding.recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {
                                swap(this.kaKaoDataList, 0, position)
                                ++roomSeletCount
                            }

                            findNavController().navigate(
                                FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                                    profileImage = it[0].image,
                                    name = it[0].name,
                                    ListBox = it[0].textBoxList[it[0].id.toInt()],
                                    itemIdPosition = it[0].id,
                                    selectChatRoomCount = roomSeletCount
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
            val kaKaoTextList = arrayOf(

                resources.getStringArray(R.array.sample_list1),
                resources.getStringArray(R.array.sample_list2),
                resources.getStringArray(R.array.sample_list3),
                resources.getStringArray(R.array.sample_list4),
                resources.getStringArray(R.array.sample_list5)
            )


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
                        kaKaoTextList
                    )
                )
            }

           val shuffleMode =  kakaoDataList.shuffled()
            shuffleMode.sortedBy{it.id}
            PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck = true



            return shuffleMode.toMutableList()
        }
        if (!PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck)
            viewModelShared.firstRunKaKaoTalkSetting(setCreateAnItemToSendToTheAdapter())


    }


    private fun setBackButtonListener() {

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_fragmentFakeKakaoTalk_to_mainFragment)
        }

    }

    private fun FragmentFakeKakaoTalkBinding.setNewMessageResponse() {


        viewModelShared.sendToPosition.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { position ->

                (recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {

                    if (!FragmentFakeKakaoChat.positionCheckList!![position].positionZeroCheck &&
                        FragmentFakeKakaoChat.positionSendRunCheck!!
                    ) {
                        notifyItemMoved(position, 0)

                        val index = itemOrderList.indexOf(position)
                        this.kaKaoDataList.add(0, this.kaKaoDataList[index])
                        this.kaKaoDataList.removeAt(index + 1)

                        notifyItemRangeChanged(0, 5)

                        for (i in FragmentFakeKakaoChat.positionCheckList!!.indices) {
                            FragmentFakeKakaoChat.positionCheckList!![i] =
                                SharedViewModel.ZeroPositionCheck(i, false)
                            if (i == index)
                                FragmentFakeKakaoChat.positionCheckList?.set(
                                    index,
                                    SharedViewModel.ZeroPositionCheck(index, true)
                                )
                        }

                        viewModelShared.firstRunKaKaoTalkSetting(this.kaKaoDataList)
                        noticeitemPositionChange = true
                        FragmentFakeKakaoChat.positionSendRunCheck = false

                        for (i in itemOrderList.indices) {
                            itemOrderList[i] = kaKaoDataList[i].id.toInt()
                        }


                    }

                }

            })
    }


}

