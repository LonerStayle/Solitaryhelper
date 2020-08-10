package com.example.solitaryhelper.view.fragment.main


import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.addCallback

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentMainBinding
import com.example.solitaryhelper.databinding.HeaderNavigationMainBinding
import com.example.solitaryhelper.db.SolitaryHelperDatabase
import com.example.solitaryhelper.view.adapter.AdapterViewPagerMain
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.dialog.DialogCustom
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.example.solitaryhelper.view.utill.toastShort
import com.example.solitaryhelper.viewmodel.MainViewModel
import com.example.solitaryhelper.viewmodel.MainViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.dialog_main_id_create.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.header_navigation_main.*
import kotlinx.android.synthetic.main.header_navigation_main.view.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    val viewModel by viewModels<MainViewModel> {
        val database = SolitaryHelperDatabase.getInstance(requireContext())
        val factory = MainViewModelFactory(database.dataSource)
        factory
    }

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
    private val iconText by lazy { resources.getStringArray(R.array.icon_text) }
    private var fabStartEventInClickCheck = false
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val naviViewTextViewId by lazy {
        binding.navigationViewMain.inflateHeaderView(R.layout.header_navigation_main).textView_id
    }

    override fun FragmentMainBinding.setCreateView() {
        setIdCreate()
        setViewPagerAndTabLayoutSetting()
    }

    override fun FragmentMainBinding.setEventListener() {
        setTabIconClickListener()
        setFabCallClickListener()
        setFabKakaoTalkClickListener()
        setFabSmsClickListener()
        setFabStartClickListener()
        setButtonMenuClickListener()
        setNavigationViewClickListener()
        backButtonClickListener()
    }

    override fun FragmentMainBinding.setLiveDataInObserver() {
        viewModel.userProfile.observe(viewLifecycleOwner, Observer {
            naviViewTextViewId.text = it.id
        })
    }


    private fun FragmentMainBinding.setViewPagerAndTabLayoutSetting() {
        viewPagerMain.adapter = AdapterViewPagerMain(this@MainFragment)

        TabLayoutMediator(tabLayoutMain, viewPagerMain) { tab, position ->

            for (i in 0..4) {
                when (position) {
                    i -> {
                        tab.text = iconText[i]; tab.setIcon(iconImage[i])
                    }
                }
            }
        }.attach()
    }

    private fun FragmentMainBinding.setTabIconClickListener() {
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

    private fun FragmentMainBinding.setFabStartClickListener() {

        fabStart.setOnClickListener {
            fabStartEventInClickCheck = !fabStartEventInClickCheck
            if (fabStartEventInClickCheck) {
                fabStart.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)

                uiScope.launch {
                    fabSms.show()
                    delay(100)
                    fabCall.show()
                    delay(200)
                    fabKakaoTalk.show()
                }

            } else {
                fabStart.setImageResource(R.drawable.ic_round_play_arrow_24)
                uiScope.launch {
                    fabKakaoTalk.hide()
                    delay(100)
                    fabCall.hide()
                    delay(200)
                    fabSms.hide()
                }
            }
        }
    }

    private fun FragmentMainBinding.setButtonMenuClickListener() {
        buttonMenu.setOnClickListener {
            drawerLayoutMain.open()
        }
    }


    private fun setNavigationViewClickListener() = with(binding.navigationViewMain) {
        setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_wiseSayingList -> {
                }
                R.id.item_ranking -> {
                }
                R.id.item_helperInWishList -> {
                }
                R.id.item_contactTheDeveloper -> {
                }
                R.id.item_setting -> {
                }
            }
            return@setNavigationItemSelectedListener false
        }
    }

    private fun FragmentMainBinding.setFabSmsClickListener() {
        fabSms.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeSms)
        }
    }

    private fun FragmentMainBinding.setFabKakaoTalkClickListener() {
        fabKakaoTalk.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeKakaoTalk)
        }
    }

    private fun FragmentMainBinding.setFabCallClickListener() {
        fabCall.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeCall)
        }
    }

    private fun backButtonClickListener() {
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback(this@MainFragment) {
                if (drawerLayoutMain.isOpen)
                    drawerLayoutMain.closeDrawers()
                else
                    requireActivity().finish()
            }
        }
    }

    private fun setIdCreate() {
        val dialog by lazy { DialogCustom(requireContext()) }

        if (TextUtils.isEmpty(naviViewTextViewId.text)) {
            dialog.dialogMainIdCreate()

            dialog.dialogCreate.button_createId.setOnClickListener {
                val textId = dialog.dialogCreate.editText_createId.text
                if (TextUtils.isEmpty(textId)) {
                    context?.toastDebugTest("닉네임을 설정해주세요.")
                    return@setOnClickListener
                }

                viewModel.idCreate(textId.toString())
                dialog.dialogCreate.dismiss()
            }
        }
        dialog.dialogCreate.show()
    }
}
