package com.example.solitaryhelper.view.fragment.main

import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentMainBinding
import com.example.solitaryhelper.view.Contents
import com.example.solitaryhelper.view.adapter.AdapterViewPagerMain
import com.example.solitaryhelper.view.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment:BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun FragmentMainBinding.setEventListener() {

        viewPagerMain.adapter = AdapterViewPagerMain(this@MainFragment)
        
        TabLayoutMediator(tabLayoutMain,viewPagerMain){ tab, position ->

            when(position) {
                Contents.MAIN_FIRST_ITEM   -> tab.setIcon(R.drawable.ic_baseline_skill_24)
                Contents.MAIN_SECOND_ITEM  -> tab.setIcon(R.drawable.ic_baseline_topic_24)
                Contents.MAIN_THIRD_ITEM   -> tab.setIcon(R.drawable.ic_outline_settings_eating_alone_24)
                Contents.MAIN_FORCE_ITEM   -> tab.setIcon(R.drawable.ic_outline_wise_saying_24)
                Contents.MAIN_FIFTH_ITEM   -> tab.setIcon(R.drawable.ic_baseline_chatbot_24)


            }
        }.attach()
    }


}