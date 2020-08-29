package com.example.solitaryhelper.view.dest.main.tapfragments


import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R

import com.example.solitaryhelper.databinding.FragmentSkillBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerSkill

import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.utill.toastDebugTest

class FragmentSkill : BaseFragment<FragmentSkillBinding>(R.layout.fragment_skill) {


        data class ViewPagerItem(
        var image: String,
        var text: String
    )

    private val kakaoTextList by lazy {
        resources.getStringArray(R.array.kaKaoExampleList)
    }

    private var kakaoImageList: Array<String>? = null
    private var callImageList: Array<String>? = null
    private var smsImageList: Array<String>? = null


    override fun FragmentSkillBinding.setEventListener() {
        setLayoutFakeKaKaoTalkInfoClickListener()
        setLayoutSkillInfoCallIntro()
        setLayoutSkillInfoSmsIntro()
    }

    override fun FragmentSkillBinding.setCreateView() {
        setAdapter()
        viewModelSkill.setViewPagerSetting(viewPagerFakekakaoTalkInfo, requireContext())
        viewModelSkill.setViewPagerSetting(viewPagerFakeSmsInfo, requireContext())
        viewModelSkill.setViewPagerSetting(viewPagerFakeCallInfo, requireContext())
    }

    private fun FragmentSkillBinding.setAdapter() {
        val itemList = mutableListOf<ViewPagerItem>()
        kakaoImageList = Array(4) { "" }
        for (i in kakaoImageList!!.indices) {
            kakaoImageList!![i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "example_imagekakao${i + 1}",
                "drawable",
                requireActivity().packageName
            ).toString())
            itemList.add(ViewPagerItem(kakaoImageList!![i], kakaoTextList[i]))

            viewPagerFakekakaoTalkInfo.adapter = AdapterViewPagerSkill(itemList)
            viewPagerFakeCallInfo.adapter = AdapterViewPagerSkill(itemList)
            viewPagerFakeSmsInfo.adapter = AdapterViewPagerSkill(itemList)
        }
    }


    private fun FragmentSkillBinding.setLayoutFakeKaKaoTalkInfoClickListener() {
        layoutSkillInfoKaKaoTalkIntro.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeKakaoTalk)
        }
    }

    private fun FragmentSkillBinding.setLayoutSkillInfoCallIntro() {

        layoutSkillInfoCallIntro.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeCallSetting)
//            context?.toastDebugTest("아직 준비중인 기능입니다. \n UI만 완성후 바로 공개 예정.")
        }
    }

    private fun FragmentSkillBinding.setLayoutSkillInfoSmsIntro() {
        layoutSkillInfoSmsIntro.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeSmsSetting)
//         context?.toastDebugTest("아직 준비중인 기능입니다. \n UI만 완성후 바로 공개 예정.")
        }
    }
}



