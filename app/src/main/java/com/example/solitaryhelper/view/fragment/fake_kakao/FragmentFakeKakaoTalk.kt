package com.example.solitaryhelper.view.fragment.fake_kakao

import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoTalkBinding
import com.example.solitaryhelper.localdb.data.KaKaoTalkData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoTalk
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import java.util.*
import java.util.Collections.swap

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {


    override fun FragmentFakeKakaoTalkBinding.setEventListener() {
        setBackButtonListener()
    }

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {



        setRecyclerVIew()
        setNewMessageResponse()
    }

    private fun setRecyclerVIew() {

        fun setData(): MutableList<KaKaoTalkData> {
            val kaKaoNameList by lazy { resources.getStringArray(R.array.sample_list1) }
            val kaKaoImageProfileList: Array<String>?
            val kakaoDataList = mutableListOf<KaKaoTalkData>()
            val kaKaoTextList = arrayOf(

              resources.getStringArray(R.array.sample_list1)  ,
              resources.getStringArray(R.array.sample_list2)  ,
              resources.getStringArray(R.array.sample_list3)  ,
              resources.getStringArray(R.array.sample_list4)  ,
              resources.getStringArray(R.array.sample_list5)
            )

            kaKaoImageProfileList = Array(kaKaoNameList.size) { "" }
            for (i in kaKaoImageProfileList.indices) {
                kaKaoImageProfileList[i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                    "sample${1+i}",
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
            viewModelShared.firstRunKaKaoTalkSetting(setData())

        viewModelShared.firstRunKaKaoTalkClass.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                binding.recyclerViewKaKaoChatList.adapter = AdapterRecyclerViewKaKaoTalk(it)
                { position ->
                    (binding.recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {
                    swap(this.kaKaoDataList, 0, position) }

                    findNavController().navigate(
                        FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                            profileImage = it[0].image,
                            name = it[0].name,
                            ListBox = it[0].textBoxList[it[0].id.toInt()],
                            position = it[0].id.toInt())
                        )

                    (binding.recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {
                        swap(this.kaKaoDataList, 0, position) }
                }
            })
    }

    private fun setBackButtonListener() {

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_fragmentFakeKakaoTalk_to_mainFragment)
        }

    }

    private fun setNewMessageResponse() {
        viewModelShared.autoChatPosition.observe(viewLifecycleOwner, androidx.lifecycle.Observer {


                (binding.recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).apply {
                    swap(this.kaKaoDataList, 0, it)
                    notifyItemMoved(it, 0)
                    viewModelShared.firstRunKaKaoTalkSetting(this.kaKaoDataList as MutableList<KaKaoTalkData>)
                }

        })
    }

}
