package com.example.solitaryhelper.view.fragment.main

import androidx.annotation.DrawableRes
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentMainBinding
import com.example.solitaryhelper.view.Contents
import com.example.solitaryhelper.view.adapter.AdapterViewPagerMain
import com.example.solitaryhelper.view.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
   private val iconImage by lazy {
        mutableListOf(
            R.drawable.ic_baseline_skill_24,
            R.drawable.ic_baseline_topic_24,
            R.drawable.ic_outline_settings_eating_alone_24,
            R.drawable.ic_outline_wise_saying_24,
            R.drawable.ic_baseline_chatbot_24
        )
    }
   private val iconSelectImage by lazy {
        mutableListOf(
            R.drawable.ic_baseline_skill_select_24,
            R.drawable.ic_baseline_topic_select_24,
            R.drawable.ic_outline_settings_eating_alone_select_24,
            R.drawable.ic_outline_wise_saying_select_24,
            R.drawable.ic_baseline_chatbot_select_24
        )
    }
    private val iconText by lazy {resources.getStringArray(R.array.icon_text)}

    override fun FragmentMainBinding.setEventListener() {
        tabIconClickListener()
    }

    override fun FragmentMainBinding.setCreateView() {
        viewPagerAndTabLayoutSetting()
    }

    private fun FragmentMainBinding.viewPagerAndTabLayoutSetting() {
        viewPagerMain.adapter = AdapterViewPagerMain(this@MainFragment)

        TabLayoutMediator(tabLayoutMain, viewPagerMain) { tab, position ->

            for (i in 0..4) {
                when (position) {
                    i ->{tab.text = iconText[i]; tab.setIcon(iconImage[i]) }
                }
            }
        }.attach()
    }

    private fun FragmentMainBinding.tabIconClickListener() {
        tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                for (i in 0..4) {
                    when (tab?.position) {
                        i -> tab.setIcon(iconSelectImage[i])
                    }
                }
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
                for (i in 0..4) {
                    when (tab?.position) {
                        i -> tab.setIcon(iconImage[i])
                    }
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}