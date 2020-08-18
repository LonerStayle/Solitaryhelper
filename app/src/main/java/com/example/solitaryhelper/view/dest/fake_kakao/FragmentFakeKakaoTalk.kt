package com.example.solitaryhelper.view.dest.fake_kakao

import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoTalkBinding
import com.example.solitaryhelper.localdb.data.KaKaoTalkData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoTalk
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.example.solitaryhelper.viewmodel.SharedViewModel
import java.util.Collections.swap

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {
    private var noticeitemPositionChange = false

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

                            }

                            findNavController().navigate(
                                FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                                    profileImage = it[0].image,
                                    name = it[0].name,
                                    ListBox = it[0].textBoxList[it[0].id.toInt()],
                                    positionInChatList = position,
                                    itemIdPosition = it[0].id
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
            val kakaoDataList = mutableListOf<KaKaoTalkData>()
            val kaKaoTextList = arrayOf(

                resources.getStringArray(R.array.sample_list1),
                resources.getStringArray(R.array.sample_list2),
                resources.getStringArray(R.array.sample_list3),
                resources.getStringArray(R.array.sample_list4),
                resources.getStringArray(R.array.sample_list5)
            )

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
                PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck = true
            }
            return kakaoDataList
        }
        if (!PrefCheckRun.getInstance(requireContext()).kaKaoTalkFirstRunCheck)
            viewModelShared.firstRunKaKaoTalkSetting(setCreateAnItemToSendToTheAdapter())
    }


    private fun setBackButtonListener() {

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_fragmentFakeKakaoTalk_to_mainFragment)
        }

    }

    private fun setNewMessageResponse() {
        viewModelShared.sendToPosition.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            (binding.recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {


                if (!FragmentFakeKakaoChat.positionCheckList!![it].positionZeroCheck &&
                    FragmentFakeKakaoChat.positionSendRunCheck!!
                ) {
                    val cloneList = this.kaKaoDataList
                    this.kaKaoDataList.add(0,cloneList[it])
                    this.kaKaoDataList.removeAt(it+1)



                    context?.toastDebugTest("이거 되는지 확인한다.")

                    for (i in FragmentFakeKakaoChat.positionCheckList!!.indices) {
                        FragmentFakeKakaoChat.positionCheckList!![i] =
                            SharedViewModel.ZeroPositionCheck(i, false)
                        if (i == it)
                            FragmentFakeKakaoChat.positionCheckList?.set(
                                it,
                                SharedViewModel.ZeroPositionCheck(it, true)
                            )
                    }




                    notifyItemMoved(it, 0)

                    viewModelShared.firstRunKaKaoTalkSetting(this.kaKaoDataList)
                    noticeitemPositionChange = true
                    FragmentFakeKakaoChat.positionSendRunCheck = false

                }


            }

        })
    }
}

