package com.example.solitaryhelper.view.dest.main

import android.content.Intent
import android.graphics.Color
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.DialogMainIdCreateBinding
import com.example.solitaryhelper.databinding.FragmentMainBinding
import com.example.solitaryhelper.databinding.HeaderNavigationMainBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerMain
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.dialog.DialogCustom
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.example.solitaryhelper.viewmodel.MainViewModel
import com.example.solitaryhelper.viewmodel.SharedViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModelMain by viewModels<MainViewModel> { viewModelFactory }
    private val sharedViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel::class.java)
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

    private val toolbarTitleList =
        arrayOf("아싸를 위한 스킬", "블로그로 최신 트렌드 읽기", "집 주변 혼밥 찾기", "감성 변태", "고민 상담")
    private val iconText by lazy { resources.getStringArray(R.array.icon_text) }
    private var fabStartEventInClickCheck = false
    private val naviIconBackground by lazy { R.drawable.ic_baseline_menu_24 }
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val naviHeaderBinding by lazy {
        DataBindingUtil.inflate<HeaderNavigationMainBinding>(
            layoutInflater, R.layout.header_navigation_main,
            binding.navigationViewMain, false
        )
    }

    private val dialog by lazy {
        DialogCustom<DialogMainIdCreateBinding>(
            requireContext(), R.layout.dialog_main_id_create,
            R.style.Theme_AppCompat_Light_Dialog_Alert
        )
    }


    override fun FragmentMainBinding.setCreateView() {
        thisFragment = this@MainFragment
        setWindowUI()
        setToolbarSetting()
        setNavigationViewAndHeaderViewSetting()
        setViewPagerAndTabLayoutSetting()
        setIdCreate()

    }

    override fun FragmentMainBinding.setEventListener() {
        setTabIconClickListener()
        setNavigationViewClickListener()
        setbackButtonClickListener()
    }

    override fun FragmentMainBinding.setLiveDataInObserver() {
        profileIdSetting()
        setMainTabVisible()
    }



    private fun setWindowUI() {
        requireActivity().window.navigationBarColor = Color.GRAY
        requireActivity().window.statusBarColor = Color.TRANSPARENT
    }



    private fun profileIdSetting() {
        viewModelMain.userProfile.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                PrefCheckRun.getInstance(requireContext()).idEmptyCheck = false
                return@Observer
            } else {
                naviHeaderBinding.textViewId.text = it.userId
                PrefCheckRun.getInstance(requireContext()).idEmptyCheck = true
            }
        })
    }
    private fun FragmentMainBinding.setMainTabVisible() {
        sharedViewModel.mainBottomVisible.observe(requireActivity(), Observer{
            tabVisible = it
        })
    }

    private fun FragmentMainBinding.setNavigationViewAndHeaderViewSetting() {
        navigationViewMain.addHeaderView(naviHeaderBinding.root)
    }

    private fun FragmentMainBinding.setViewPagerAndTabLayoutSetting() {

        viewPagerMain.adapter = AdapterViewPagerMain(this@MainFragment)
        TabLayoutMediator(tabLayoutMain, viewPagerMain) { tab, position ->

            for (i in 0..4) {
                when (position) {
                    i -> {
                        tab.text = iconText[i]
                        //앱 첫 시작시 포지션이 1 일때 색감이 안바뀌던것을 조절함
                        if (i == 0) {
                            tab.setIcon(iconSelectImage[i])
                            fabStart.hide()
                        } else
                            tab.setIcon(iconImage[i])
                    }
                }
            }
        }.attach()
        viewPagerMain.isUserInputEnabled = false
    }

    private fun FragmentMainBinding.setTabIconClickListener() {

        tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                for (i in 0..4) {
                    when (tab?.position) {

                        i -> {
                            if (i == 0 || i == 4)
                                fabStart.hide()

                            tab.setIcon(iconSelectImage[i])
                            titleText = toolbarTitleList[i]
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                for (i in 0..4) {
                    when (tab?.position) {
                        i -> {
                            if (i == 0 || i == 4)
                                fabStart.show()
                            tab.setIcon(iconImage[i])
                        }

                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    fun setFabStartClickListener(v: View) {
        binding.apply {
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

    fun setToolbarButtonClickListener(v: View) {
        binding.drawerLayoutMain.open()
    }

    private fun FragmentMainBinding.setToolbarSetting() {
        with(toolBar) {
            setNavigationIcon(naviIconBackground)
            titleText = toolbarTitleList[0]
        }
    }

    private fun setNavigationViewClickListener() = with(binding.navigationViewMain) {
        setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.item_wiseSayingList -> {
                    findNavController().navigate(R.id.action_mainFragment_to_fragmentWiseSaying)
                }
                R.id.item_contactTheDeveloper -> {

                    Intent(Intent.ACTION_SEND).apply {
                        type = "plain/Text";
                        putExtra(Intent.EXTRA_EMAIL, getString(R.string.email))
                        putExtra(Intent.EXTRA_SUBJECT, "<" + getString(R.string.app_name) +"앱 문의 하기>")
                        putExtra(Intent.EXTRA_TEXT, "앱 버전 (AppVersion):" + pInfo +
                                "\n기기명 (Device):\n안드로이드 OS (Android OS):\n내용 (Content):\n")
                        startActivity(this)
                    }
                }
                R.id.item_setting -> {
                    requireContext().startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }
            }
            return@setNavigationItemSelectedListener false
        }
    }

    fun setFabSmsClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeSms)
    }

    fun setFabKakaoTalkClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeKakaoTalk)
    }

    fun setFabCallClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeCall)
    }


    private fun setbackButtonClickListener() {
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback(this@MainFragment) {
                if (drawerLayoutMain.isOpen)
                    drawerLayoutMain.closeDrawers()
                else {
                    requireActivity().finish()
                }

            }
        }
    }

    private fun setIdCreate() {
        dialog.dialogViewCreate()

        val textId = dialog.dialogCustomCreateBinding.editTextCreateId.text
        fun logicInMakeId() {
            if (TextUtils.isEmpty(textId))
                context?.toastDebugTest("닉네임을 설정해주세요.")
            else {
                viewModelMain.insertUserProfileId(textId.toString())
                dialog.dialogCreate.dismiss()
            }
        }

        fun setButtonIdChangeClickListener(logic: () -> Unit) {
            naviHeaderBinding.buttonIdChange.setOnClickListener {
                dialog.dialogCreate.show()
                dialog.dialogCustomCreateBinding.buttonCreateId.setOnClickListener {
                    logic()
                }
            }
        }
        setButtonIdChangeClickListener { logicInMakeId() }

        if (!PrefCheckRun.getInstance(requireContext()).idEmptyCheck) {
            dialog.dialogCustomCreateBinding.buttonCreateId.setOnClickListener {
                logicInMakeId()
            }
            dialog.dialogCreate.show()
        }
    }

    override fun onResume() {
        if (binding.navigationViewMain.getHeaderView(0) == null)
            binding.navigationViewMain.addHeaderView(naviHeaderBinding.root)
        super.onResume()
    }

    override fun onPause() {
        binding.navigationViewMain.removeHeaderView(naviHeaderBinding.root)
        super.onPause()
    }
}
