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
    protected val viewModelSkill by viewModels<SkillViewModel> { SkillViewModelFactory() }
    protected val viewModelMain by viewModels<MainViewModel> {
        val dataBase = SolitaryHelperDatabase.getInstance(requireContext())
        val factory = MainViewModelFactory(dataBase.userDataSource)
        factory
    }
    protected val viewModelShared by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    protected val viewModelKaKaoTalk by viewModels<KaKaoTalkViewModel> {
        val dataBase = SolitaryHelperDatabase.getInstance(requireContext())
        val factory = KaKaoTalkViewModelFactory(dataBase.kaKaoDataSource)
        factory
    }
    protected val viewModelKaKaoChat by lazy {
        val database = SolitaryHelperDatabase.getInstance(requireContext())
        val factory = KaKaoChatViewModelFactory(database.kakaoChatDataSource)
        ViewModelProvider(this@BaseFragment, factory).get(KaKaoChatViewModel::class.java)
    }
    protected val viewModelCall by viewModels<CallViewModel>()

    protected val viewModelSms by viewModels<SmsViewModel> {
        val database = SolitaryHelperDatabase.getInstance(requireContext())
        val factory = SmsViewModelFactory(database.smsDataSource)
        factory
    }
    protected val viewModelTopic by viewModels<TopicViewModel> { TopicViewModelFactory() }


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

    abstract fun VDB.setEventListener()
    abstract fun VDB.setCreateView()
    open fun VDB.setLiveDataInObserver() = Unit


}

