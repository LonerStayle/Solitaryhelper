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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.solitaryhelper.R
import com.example.solitaryhelper.database.localdb.SolitaryHelperDatabase
import com.example.solitaryhelper.viewmodel.*
import com.example.solitaryhelper.viewmodel.factory.*


abstract class BaseFragment<VDB : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    protected lateinit var binding: VDB
    protected val viewModelFactory by lazy {
        val dataBase = SolitaryHelperDatabase.getInstance(requireContext())
        ViewModelFactory(dataBase.kakaoChatDataSource,dataBase.kaKaoDataSource,
            dataBase.smsDataSource,dataBase.userDataSource)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<VDB>(inflater, layoutId, container, false).run {
        lifecycleOwner = this@BaseFragment
        binding = this

        setCreateView()
        setLiveDataInObserver()
        setEventListener()
        root

    }

    fun RecyclerView.disableItemAnimator() {
        (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
    }

    abstract fun VDB.setCreateView()
    open fun VDB.setEventListener() = Unit
    open fun VDB.setLiveDataInObserver() = Unit


}

