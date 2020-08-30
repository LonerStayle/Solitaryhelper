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
    private val callTextList by lazy {
        resources.getStringArray(R.array.callExampleList)
    }
    private val smsTextList by lazy {
        resources.getStringArray(R.array.smsExampleList)
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
        val kakaoitemList = mutableListOf<ViewPagerItem>()
        val callitemList = mutableListOf<ViewPagerItem>()
        val smsitemList = mutableListOf<ViewPagerItem>()
        kakaoImageList = Array(6) { "" }
        for (i in 5 downTo 0) {
            kakaoImageList!![i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "guide_kakao${i}",
                "drawable",
                requireActivity().packageName
            ).toString())
            kakaoitemList.add(ViewPagerItem(kakaoImageList!![i], kakaoTextList[i]))
        }
            callImageList = Array(4) {""}
            for (i in 0..3) {
                callImageList!![i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                    "guide_call${i}",
                    "drawable",
                    requireActivity().packageName
                ).toString())
                callitemList.add(ViewPagerItem(kakaoImageList!![i], callTextList[i]))
            }
                smsImageList = Array(1) {(Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                    "sample0",
                    "drawable",
                    requireActivity().packageName
                ).toString())}
            smsitemList.add(ViewPagerItem(smsImageList!![0],smsTextList[0]))


                viewPagerFakekakaoTalkInfo.adapter = AdapterViewPagerSkill(kakaoitemList)
            viewPagerFakeCallInfo.adapter = AdapterViewPagerSkill(callitemList)
            viewPagerFakeSmsInfo.adapter = AdapterViewPagerSkill(smsitemList)

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



