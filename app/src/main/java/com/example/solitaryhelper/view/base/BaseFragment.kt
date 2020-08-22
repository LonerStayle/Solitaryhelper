package com.example.solitaryhelper.view.base

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Build.VERSION_CODES.Q

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkBuilder
import com.example.solitaryhelper.R
import com.example.solitaryhelper.localdb.SolitaryHelperDatabase
import com.example.solitaryhelper.viewmodel.*


import com.example.solitaryhelper.viewmodel.factory.KaKaoChatViewModelFactory
import com.example.solitaryhelper.viewmodel.factory.MainViewModelFactory

import com.example.solitaryhelper.viewmodel.factory.SkillViewModelFactory


abstract class BaseFragment<VDB : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    protected lateinit var binding: VDB
    protected val viewModelSkill by viewModels<SkillViewModel> { SkillViewModelFactory() }
    protected val viewModelMain by viewModels<MainViewModel> {
        val dataBase = SolitaryHelperDatabase.getInstance(requireContext())
        val factory = MainViewModelFactory(dataBase.dataSource)
        factory
    }
    protected val viewModelShared by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    protected val viewModelKaKaoTalk by viewModels<KaKaoTalkViewModel>()
    protected val viewModelKaKaoChat by lazy {
        ViewModelProvider(
            requireActivity(),
            KaKaoChatViewModelFactory()
        ).get(KaKaoChatViewModel::class.java)
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

