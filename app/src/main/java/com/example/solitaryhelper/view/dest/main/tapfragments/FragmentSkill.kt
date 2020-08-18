package com.example.solitaryhelper.view.dest.main.tapfragments


import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R

import com.example.solitaryhelper.databinding.FragmentSkillBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerSkill

import com.example.solitaryhelper.view.base.BaseFragment

class FragmentSkill : BaseFragment<FragmentSkillBinding>(R.layout.fragment_skill) {


    //    data class ViewPagerItem(
//        var image: String,
//        var text: String
//    )

    private val textList by lazy {
        resources.getStringArray(R.array.sample_list1)
    }

//    private var imageList: Array<String>? = null


    override fun FragmentSkillBinding.setEventListener() {
        setLayoutFakeKaKaoTalkInfoClickListener()
        setLayoutSkillInfoCallIntro()
        setLayoutSkillInfoSmsIntro()
    }

    override fun FragmentSkillBinding.setCreateView() {
        setAdapter()
        viewModelSkill.setViewPagerSetting(viewPagerFakekakaoTalkInfo,requireContext())
        viewModelSkill.setViewPagerSetting(viewPagerFakeSmsInfo,requireContext())
        viewModelSkill.setViewPagerSetting(viewPagerFakeCallInfo,requireContext())
    }

    private fun FragmentSkillBinding.setAdapter() {
//      val itemList = mutableListOf<ViewPagerItem>()
//        imageList = Array(5) { "" }
//        for (i in imageList!!.indices) {
//            imageList!![i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH+resources.getIdentifier(
//                "sample$i",
//                "drawable",
//                requireActivity().packageName
//            ).toString())
//            itemList.add(ViewPagerItem(imageList!![i], textList[i]))

        viewPagerFakekakaoTalkInfo.adapter = AdapterViewPagerSkill(textList.toList())
        viewPagerFakeCallInfo.adapter = AdapterViewPagerSkill(textList.toList())
        viewPagerFakeSmsInfo.adapter = AdapterViewPagerSkill(textList.toList())
    }



    private fun FragmentSkillBinding.setLayoutFakeKaKaoTalkInfoClickListener(){
        layoutSkillInfoKaKaoTalkIntro.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeKakaoTalk)
        }
    }
    private fun FragmentSkillBinding.setLayoutSkillInfoCallIntro(){
        layoutSkillInfoCallIntro.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeCallSetting)
        }
    }
    private fun FragmentSkillBinding.setLayoutSkillInfoSmsIntro(){
        layoutSkillInfoSmsIntro.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeSmsSetting)
        }
    }
}


