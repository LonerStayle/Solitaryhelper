package com.example.solitaryhelper.view.fragment.fake_kakao

import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoTalkBinding
import com.example.solitaryhelper.localdb.data.KaKaoTalkData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoTalk
import com.example.solitaryhelper.view.contents.Contents

class FragmentFakeKakaoTalk :
    BaseFragment<FragmentFakeKakaoTalkBinding>(R.layout.fragment_fake_kakao_talk) {

    private val kaKaoNameList by lazy { resources.getStringArray(R.array.sample_list) }
    private val kaKaoTextList by lazy {
        arrayOf( resources.getStringArray(R.array.sample_list),
                resources.getStringArray(R.array.sample_list2),
                resources.getStringArray(R.array.sample_list3),
                resources.getStringArray(R.array.sample_list4),
                resources.getStringArray(R.array.sample_list5))

    }



    override fun FragmentFakeKakaoTalkBinding.setEventListener() {

    }

    override fun FragmentFakeKakaoTalkBinding.setCreateView() {
        setRecyclerVIew()
    }

    private fun setRecyclerVIew() {

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
                KaKaoTalkData(
                    kaKaoNameList[i],
                    kaKaoImageProfileList[i],
                    kaKaoTextList
                )
            )
        }

        val shuffledList = kakaoDataList.shuffled()

        binding.recyclerViewKaKaoChatList.adapter = AdapterRecyclerViewKaKaoTalk(shuffledList)
        {
            findNavController().navigate(
                FragmentFakeKakaoTalkDirections.actionFragmentFakeKakaoTalkToFragmentFakeKakaoChat(
                    profileImage = shuffledList[it].image, name = shuffledList[it].name
                ,ListBox = shuffledList[it].textBoxList[it]
                )
            )
        }
        }
}