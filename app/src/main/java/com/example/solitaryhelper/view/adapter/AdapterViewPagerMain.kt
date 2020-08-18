package com.example.solitaryhelper.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.dest.main.tapfragments.*

class AdapterViewPagerMain(fr: Fragment) : FragmentStateAdapter(fr) {


    override fun getItemCount() = Contents.MAIN_TAB_LAYOUT_TOTAL_ICON_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            Contents.MAIN_FIRST_ITEM -> FragmentSkill()
            Contents.MAIN_SECOND_ITEM -> FragmentTopic()
            Contents.MAIN_THIRD_ITEM -> FragmentEatingAlone()
            Contents.MAIN_FORCE_ITEM -> FragmentWiseSaying()
            Contents.MAIN_FIFTH_ITEM -> FragmentChatBot()
            else -> FragmentSkill()
        }
    }
}