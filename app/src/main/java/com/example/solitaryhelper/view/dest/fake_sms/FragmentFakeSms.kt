package com.example.solitaryhelper.view.dest.fake_sms

import androidx.lifecycle.Observer
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeSmsBinding
import com.example.solitaryhelper.localdb.entitiy.Sms
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewSmsCustom
import com.example.solitaryhelper.view.base.BaseFragment

class FragmentFakeSms:BaseFragment<FragmentFakeSmsBinding>(R.layout.fragment_fake_sms){
    override fun FragmentFakeSmsBinding.setEventListener() {

    }

    override fun FragmentFakeSmsBinding.setCreateView() {
    }

    override fun FragmentFakeSmsBinding.setLiveDataInObserver() {
        viewModelSms.SmsList.observe(viewLifecycleOwner, Observer {
            recyclerViewSms.adapter = AdapterRecyclerViewSmsCustom(it as MutableList<Sms>)

        })
    }
}