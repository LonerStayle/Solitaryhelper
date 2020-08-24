package com.example.solitaryhelper.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.dest.fake_sms.settingfragments.FragmentFakeSmsSettingCustom
import com.example.solitaryhelper.view.dest.fake_sms.settingfragments.FragmentFakeSmsSettingPamphlet
import com.example.solitaryhelper.view.dest.main.tapfragments.*

class AdapterViewPagerSmsSetting(fr: Fragment) : FragmentStateAdapter(fr) {

    override fun getItemCount() = Contents.SMS_SETTING_TAB_LAYOUT_TOTAL_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0-> FragmentFakeSmsSettingPamphlet()
            1-> FragmentFakeSmsSettingCustom()
            else -> FragmentFakeSmsSettingPamphlet()
        }
    }
}