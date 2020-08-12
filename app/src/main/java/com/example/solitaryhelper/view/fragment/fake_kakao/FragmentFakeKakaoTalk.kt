package com.example.solitaryhelper.view.fragment.fake_kakao

import android.graphics.Insets.add
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoTalkBinding
import com.example.solitaryhelper.localdb.data.KaKaoTalkData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoTalk
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.utill.toastDebugTest

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {

    private val kaKaoNameList by lazy { resources.getStringArray(R.array.sample_list) }
    private val kaKaoTextList by lazy {
        resources.getStringArray(R.array.sample_list)
    }


    override fun FragmentFakeKakaoTalkBinding.setEventListener() {

    }

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {
        setRecyclerVIew()
    }

    private fun FragmentFakeKakaoTalkBinding.setRecyclerVIew() {

        val kaKaoImageProfileList: Array<String>?
        val kakaoDataList = mutableListOf<KaKaoTalkData>()

        kaKaoImageProfileList = Array(kaKaoNameList.size) { "" }
        for (i in kaKaoImageProfileList.indices) {
            kaKaoImageProfileList[i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "sample$i",
                "drawable",
                requireActivity().packageName
            ).toString())
            kakaoDataList.add(
                KaKaoTalkData(   kaKaoImageProfileList[i],kaKaoNameList[i],kaKaoTextList[i])
            )
        }

        recyclerViewKaKaoChatList.adapter = AdapterRecyclerViewKaKaoTalk(kakaoDataList.toList())
                    {
                findNavController().navigate(
                    FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                        profileImage = kakaoDataList[it].image, name = kakaoDataList[it].name
                    )
                )
            }

        context?.toastDebugTest("${(recyclerViewKaKaoChatList.adapter as AdapterRecyclerViewKaKaoTalk).kaKaoDataList.size}")
    }
}