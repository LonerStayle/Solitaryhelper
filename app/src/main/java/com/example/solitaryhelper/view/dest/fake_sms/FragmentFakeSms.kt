package com.example.solitaryhelper.view.dest.fake_sms

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeSmsBinding
import com.example.solitaryhelper.database.localdb.entitiy.Sms
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewSmsCustom
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.viewmodel.SmsViewModel

class FragmentFakeSms:BaseFragment<FragmentFakeSmsBinding>(R.layout.fragment_fake_sms){
    private val viewModelSms by viewModels<SmsViewModel> { viewModelFactory }


    override fun FragmentFakeSmsBinding.setEventListener() {}

    override fun FragmentFakeSmsBinding.setCreateView() {}

    override fun FragmentFakeSmsBinding.setLiveDataInObserver() {
        viewModelSms.SmsList.observe(viewLifecycleOwner, Observer {
            recyclerViewSms.adapter = AdapterRecyclerViewSmsCustom(it as MutableList<Sms>)

        })
    }
}