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
import com.example.solitaryhelper.viewmodel.MainViewModel
import com.example.solitaryhelper.viewmodel.MainViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.viewmodel.SharedViewModel

abstract class BaseFragment<VDB : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    protected lateinit var binding: VDB
    protected val viewModelMain by viewModels<MainViewModel> { MainViewModelFactory() }
    protected val viewModelShared by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
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