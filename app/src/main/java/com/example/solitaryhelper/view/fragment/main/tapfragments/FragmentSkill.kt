package com.example.solitaryhelper.view.fragment.main.tapfragments

import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentSkillBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerSkill
import com.example.solitaryhelper.view.base.BaseFragment

class FragmentSkill : BaseFragment<FragmentSkillBinding>(R.layout.fragment_skill) {

    data class ViewPagerItem(
        var image: String,
        var text: String
    )
    private val textList by lazy {
        resources.getStringArray(R.array.skillToItem_list)
    }
    private var imageList: Array<String>? = null


    override fun FragmentSkillBinding.setEventListener() {

    }

    override fun FragmentSkillBinding.setCreateView() {
        setAdapter()
    }

    private fun FragmentSkillBinding.setAdapter() {
      val itemList = mutableListOf<ViewPagerItem>()
        imageList = Array(5) { "" }
        for (i in imageList!!.indices) {
            imageList!![i] += (resources.getIdentifier(
                "sample$i",
                "drawable",
                requireActivity().packageName
            ).toString())
            itemList.add(ViewPagerItem(imageList!![i], textList[i]))
        }

        viewPagerFakekakaoTalkInfo.adapter =
            AdapterViewPagerSkill(itemList)

        viewPagerFakeCallInfo.adapter=
            AdapterViewPagerSkill(itemList)
        viewPagerFakeSmsInfo.adapter =
            AdapterViewPagerSkill(itemList)
    }


}