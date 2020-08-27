package com.example.solitaryhelper.view.dest.fake_sms

import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeSmsSettingBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerSmsSetting
import com.example.solitaryhelper.view.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class FragmentFakeSmsSetting :
    BaseFragment<FragmentFakeSmsSettingBinding>(R.layout.fragment_fake_sms_setting) {
    override fun FragmentFakeSmsSettingBinding.setEventListener() {}

    override fun FragmentFakeSmsSettingBinding.setCreateView() {
        setViewPagerAndTabLayoutSetting()
    }

    private fun FragmentFakeSmsSettingBinding.setViewPagerAndTabLayoutSetting() {
        viewPagerSmsSettings.adapter = AdapterViewPagerSmsSetting(this@FragmentFakeSmsSetting)
        TabLayoutMediator(tabLayoutSmsSetting, viewPagerSmsSettings) { tab, position ->

            when (position) {
                0 -> tab.text = "저장 목록"
                1 -> tab.text = "문자 리스트 만들기"

            }
        }.attach()
    }
}

