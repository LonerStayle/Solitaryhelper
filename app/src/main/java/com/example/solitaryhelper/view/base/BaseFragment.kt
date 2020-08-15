package com.example.solitaryhelper.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.localdb.data.KaKaoTalkChatData
import com.example.solitaryhelper.viewmodel.KaKaoChatViewModel


import com.example.solitaryhelper.viewmodel.SharedViewModel
import com.example.solitaryhelper.viewmodel.SkillViewModel
import com.example.solitaryhelper.viewmodel.factory.KaKaoChatViewModelFactory

import com.example.solitaryhelper.viewmodel.factory.SkillViewModelFactory

abstract class BaseFragment<VDB : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    protected lateinit var binding: VDB
    protected val viewModelSkill by viewModels<SkillViewModel> { SkillViewModelFactory() }
    protected val viewModelShared by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    protected val viewModelKaKaoChat by lazy{
        ViewModelProvider(requireActivity(),KaKaoChatViewModelFactory()).get(KaKaoChatViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<VDB>(inflater, layoutId, container, false).run {
        lifecycleOwner = this@BaseFragment
        binding = this

        setEventListener()
        setLiveDataInObserver()
        setCreateView()
        root
    }

    abstract fun VDB.setEventListener()
    abstract fun VDB.setCreateView()
    open fun VDB.setLiveDataInObserver() = Unit
}